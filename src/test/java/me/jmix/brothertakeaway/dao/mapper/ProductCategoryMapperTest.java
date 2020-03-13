package me.jmix.brothertakeaway.dao.mapper;

import me.jmix.brothertakeaway.entity.ProductCategory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductCategoryMapperTest {
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Test
    @Disabled
    void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "师兄最不爱");
        map.put("categoryType", 101);
        int result = productCategoryMapper.insertByMap(map);
        assertEquals(1, result);
    }

    @Test
    @Disabled
    void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("炸食");
        productCategory.setCategoryType(102);
        int result = productCategoryMapper.insertByObject(productCategory);
        assertEquals(1, result);
    }

    @Test
    @Disabled
    void findByCategoryType() {
        Integer categoryType = 101;
        ProductCategory productCategoryResult = productCategoryMapper.findByCategoryType(categoryType);
        assertNotNull(productCategoryResult);
    }

    @Test
    @Disabled
    void findByCategoryName() {
        String categoryName = "师兄最不爱";
        List<ProductCategory> productCategoryList = productCategoryMapper.findByCategoryName(categoryName);
        assertEquals(2, productCategoryList.size());
    }

    @Test
    @Disabled
    void updateByCategoryType() {
        String categoryName = "师兄最不爱";
        Integer categoryType = 102;
        int result = productCategoryMapper.updateByCategoryType(categoryName, categoryType);
        assertEquals(1, result);
    }

    @Test
    @Disabled
    void deleteByCategoryType() {
        Integer categoryType = 102;
        int result = productCategoryMapper.deleteByCategoryType(categoryType);
        assertEquals(1, result);
    }
}