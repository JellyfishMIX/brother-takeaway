package me.jmix.brothertakeaway.controller;

import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.entity.ProductInfo;
import me.jmix.brothertakeaway.exception.service.ProductServiceException;
import me.jmix.brothertakeaway.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;

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
            map.put("url", "sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
