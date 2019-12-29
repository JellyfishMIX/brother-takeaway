package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.ProductCategory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    @Disabled
    public void testSave() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(4);
        productCategoryRepository.save(productCategory);
    }

    @Test
    @Disabled
    public void testUpdate() {
        ProductCategory productCategory = productCategoryRepository.findById(2).orElse(null);
        productCategory.setCategoryType(6);
        productCategoryRepository.save(productCategory);
    }

    @Test
    @Transactional
    @Disabled
    public void testSave2() {
        ProductCategory productCategory = new ProductCategory("小孩最爱", 10);
        ProductCategory result = productCategoryRepository.save(productCategory);
        assertNotNull(result);
    }

    @Test
    @Disabled
    public void testFindByCategoryTypeIn() {
        List<Integer> integerList = Arrays.asList(9, 10, 11);
        List<ProductCategory> productCategoryList = productCategoryRepository.findByCategoryTypeIn(integerList);
        assertNotEquals(0, productCategoryList.size());
    }
}