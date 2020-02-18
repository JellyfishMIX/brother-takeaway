package me.jmix.brothertakeaway.service.impl;

import me.jmix.brothertakeaway.dao.ProductCategoryRepository;
import me.jmix.brothertakeaway.entity.ProductCategory;
import me.jmix.brothertakeaway.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory getOneByCategoryId(Integer categoryId) {
        return productCategoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> getAllProductCategory() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> getListByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory addProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
