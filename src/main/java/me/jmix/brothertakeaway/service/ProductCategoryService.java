package me.jmix.brothertakeaway.service;

import me.jmix.brothertakeaway.entity.ProductCategory;

import java.util.List;

/**
 * @author JellyfishMIX
 */

public interface ProductCategoryService {
    /**
     * 通过商品类别Id获取对应商品类别
     * @param categoryId
     * @return
     */
    ProductCategory getOneByCategoryId(Integer categoryId);

    /**
     * 获取所有商品类别列表
     * @return
     */
    List<ProductCategory> getAllProductCategory();

    /**
     * 传入商品类别代号列表，获取商品类别列表
     * @param categoryTypeList 商品类别代号列表
     * @return
     */
    List<ProductCategory> getListByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增商品类别
     * @param productCategory
     * @return
     */
    ProductCategory addProductCategory(ProductCategory productCategory);

    /**
     * 修改商品类别
     * @param productCategory
     * @return
     */
    ProductCategory updateProductCategory(ProductCategory productCategory);
}
