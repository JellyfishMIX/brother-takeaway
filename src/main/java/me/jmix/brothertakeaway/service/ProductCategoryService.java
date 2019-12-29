package me.jmix.brothertakeaway.service;

import me.jmix.brothertakeaway.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory getOneByCategoryId(Integer categoryId);

    List<ProductCategory> getAllProductCategory();

    List<ProductCategory> getListByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory addProductCategory(ProductCategory productCategory);
}
