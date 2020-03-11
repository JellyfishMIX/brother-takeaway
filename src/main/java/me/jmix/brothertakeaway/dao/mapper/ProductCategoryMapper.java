package me.jmix.brothertakeaway.dao.mapper;

import org.apache.ibatis.annotations.Insert;


import java.util.Map;

public interface ProductCategoryMapper {
    /**
     * 通过Map插入
     * 返回值为插入的条数
     * @param map
     * @return
     */
    @Insert("insert into tb_product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{category_type, categoryType, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);
}