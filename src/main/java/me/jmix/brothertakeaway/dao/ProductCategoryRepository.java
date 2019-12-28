package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    // 此方法的名字格式特定
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
