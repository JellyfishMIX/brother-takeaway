package me.jmix.brothertakeaway.controller;

import me.jmix.brothertakeaway.entity.ProductCategory;
import me.jmix.brothertakeaway.exception.service.ProductServiceException;
import me.jmix.brothertakeaway.form.CategoryForm;
import me.jmix.brothertakeaway.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    /**
     * 新增/修改（查询）某个类目
     * @param categoryId
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
        Map<String, Object> map = new HashMap<>();
        if (categoryId != null) {
            ProductCategory productCategory = productCategoryService.getOneByCategoryId(categoryId);
            map.put("productCategory", productCategory);
        }

        return new ModelAndView("product_category/index", map);
    }

    /**
     * 保存/更新类目
     * @param categoryForm
     * @param bindingResult
     * @return
     */
    @GetMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product_category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            // 如果categoryId为null，说明是新增类目
            if (categoryForm.getCategoryId() != null) {
                productCategory = productCategoryService.getOneByCategoryId(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            // 如果没有categoryId，则数据会进行自增操作
            productCategoryService.updateProductCategory(productCategory);
        } catch (ProductServiceException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product_category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product_category/list");
        return new ModelAndView("common/success");
    }
}
