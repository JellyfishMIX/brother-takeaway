package me.jmix.brothertakeaway.controller;

import me.jmix.brothertakeaway.entity.ProductCategory;
import me.jmix.brothertakeaway.entity.ProductInfo;
import me.jmix.brothertakeaway.exception.service.ProductServiceException;
import me.jmix.brothertakeaway.form.ProductForm;
import me.jmix.brothertakeaway.service.ProductCategoryService;
import me.jmix.brothertakeaway.service.ProductService;
import me.jmix.brothertakeaway.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.getAllProductInfo(pageRequest);

        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);

        ModelAndView modelAndView = new ModelAndView("product/list", map);
        return modelAndView;
    }

    @GetMapping("/onsale")
    public ModelAndView onSale(@RequestParam("productId") String productId) {
        Map<String, Object> map = new HashMap<>();
        try {
            productService.onSale(productId);
        } catch (ProductServiceException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");

        return new ModelAndView("common/success", map);
    }

    @GetMapping("/offsale")
    public ModelAndView offSale(@RequestParam("productId") String productId) {
        Map<String, Object> map = new HashMap<>();
        try {
            productService.offSale(productId);
        } catch (ProductServiceException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");

        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId) {
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.getProductInfoByProductId(productId);
            map.put("productInfo", productInfo);
        }

        // 查询所有商品的类目
        List<ProductCategory> productCategoryList = productCategoryService.getAllProductCategory();
        map.put("productCategoryList", productCategoryList);

        return new ModelAndView("product/index", map);
    }

    @PostMapping("/save")
    @CacheEvict(cacheNames = "prodcut", key = "123")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        if (!StringUtils.isEmpty(productForm.getProductId())) {
            productInfo = productService.getProductInfoByProductId(productForm.getProductId());
        } else {
            productForm.setProductId(KeyUtil.getUniqueKey());
        }
        BeanUtils.copyProperties(productForm, productInfo);

        try {
            productService.saveProduct(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");

        return new ModelAndView("common/success", map);
    }
}
