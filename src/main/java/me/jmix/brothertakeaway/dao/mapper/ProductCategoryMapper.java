package me.jmix.brothertakeaway.dao.mapper;

import me.jmix.brothertakeaway.entity.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "productCategoryMapper")
public interface ProductCategoryMapper {
    /**
     * 通过Map插入
     * 返回值为插入的条数
     * @param map
     * @return
     */
    @Insert("insert into tb_product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    /**
     * 通过Object插入
     * 返回值为插入的条数
     * @param productCategory
     * @return
     */
    @Insert("insert into tb_product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    /**
     * 通过categoryType查询productCategory
     * 由于categoryType为constraint key, 所以结果返回单条
     * @param categoryType
     * @return
     */
    @Select("select * from tb_product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    /**
     * 通过categoryName查询productCategory
     * 由于categoryName可能出现重复，所以结果返回List
     * @param categoryName
     * @return
     */
    @Select("select * from tb_product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Update("update tb_product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(String categoryName, Integer categoryType);
}