package me.jmix.brothertakeaway.service.impl;

import me.jmix.brothertakeaway.entity.ProductCategory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    @Disabled
    void getOneByCategoryId() {
        ProductCategory productCategory = productCategoryService.getOneByCategoryId(1);
        assertEquals(Integer.valueOf(1), productCategory.getCategoryId());
    }

    @Test
    @Disabled
    void getAllProductCategory() {
        List<ProductCategory> productCategoryList = productCategoryService.getAllProductCategory();
        assertNotEquals(0, productCategoryList.size());
    }

    @Test
    @Disabled
    void getListByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = productCategoryService.getListByCategoryTypeIn(Arrays.asList(1, 59));
        assertNotEquals(0, productCategoryList);
    }

    @Test
    @Disabled
    void addProductCategory() {
        ProductCategory productCategory = new ProductCategory("男生喜欢", 8);
        ProductCategory result = productCategoryService.addProductCategory(productCategory);
        assertNotNull(result);
    }
}