package me.jmix.brothertakeaway.controller;

import me.jmix.brothertakeaway.entity.ProductCategory;
import me.jmix.brothertakeaway.entity.ProductInfo;
import me.jmix.brothertakeaway.service.ProductCategoryService;
import me.jmix.brothertakeaway.service.ProductService;
import me.jmix.brothertakeaway.vo.ProductCategoryVO;
import me.jmix.brothertakeaway.vo.ProductInfoVO;
import me.jmix.brothertakeaway.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class CustomerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取商品列表
     * @return
     */
    // @GetMapping("/getproductlist")
    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123")
    public ResultVO getProductList() {
        // 1. 查询所有上架商品
        List<ProductInfo> productInfoList = productService.getShelvesProductInfo();

        // 2. 查询商品类目

        // 传统写法
        // List<Integer> productCategoryList = new ArrayList<>();
        // for (ProductInfo productInfo : productInfoList) {
        //     productCategoryList.add(productInfo.getCategoryType());
        // }

        // 精简方法(java8, lambda)
        List<Integer> productCategoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.getListByCategoryTypeIn(productCategoryTypeList);

        // 3. 数据拼装
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setCategoryType(productCategory.getCategoryType());
            productCategoryVO.setCategoryName(productCategory.getCategoryName());

            // 构建每个ProductCategoryVO中的ProductInfoVOList
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productCategoryVO.setProductInfoVOList(productInfoVOList);
            productCategoryVOList.add(productCategoryVO);
        }

        ResultVO resultVO = new ResultVO();
        resultVO.setData(productCategoryVOList);
        resultVO.setCode(0);
        resultVO.setMsg("查询成功");

        return resultVO;
    }
}
