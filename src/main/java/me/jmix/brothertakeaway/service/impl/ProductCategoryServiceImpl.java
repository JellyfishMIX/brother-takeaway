package me.jmix.brothertakeaway.service.impl;

import me.jmix.brothertakeaway.dao.ProductCategoryRepository;
import me.jmix.brothertakeaway.entity.ProductCategory;
import me.jmix.brothertakeaway.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JellyfishMIX
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 通过商品类别Id获取对应商品类别
     * @param categoryId
     * @return
     */
    @Override
    public ProductCategory getOneByCategoryId(Integer categoryId) {
        return productCategoryRepository.findById(categoryId).orElse(null);
    }

    /**
     * 获取所有商品类别列表
     * @return
     */
    @Override
    public List<ProductCategory> getAllProductCategory() {
        return productCategoryRepository.findAll();
    }

    /**
     * 传入商品类别代号列表，获取商品类别列表
     * @param categoryTypeList 商品类别代号列表
     * @return
     */
    @Override
    public List<ProductCategory> getListByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    /**
     * 新增商品类别
     * @param productCategory
     * @return
     */
    @Override
    public ProductCategory addProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    /**
     * 修改商品类别
     * @param productCategory
     * @return
     */
    @Override
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
