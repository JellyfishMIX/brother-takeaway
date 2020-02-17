package me.jmix.brothertakeaway.controller;

import me.jmix.brothertakeaway.entity.ProductCategory;
import me.jmix.brothertakeaway.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list() {
        Map<String, Object> map = new HashMap<>();
        List<ProductCategory> productCategoryList = productCategoryService.getAllProductCategory();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("product_category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
        Map<String, Object> map = new HashMap<>();
        if (categoryId != null) {
            ProductCategory productCategory = productCategoryService.getOneByCategoryId(categoryId);
            map.put("productCategory", productCategory);
        }

        return new ModelAndView("product_category/index", map);
    }
}
