package me.jmix.brothertakeaway.controller;

import me.jmix.brothertakeaway.vo.ProductCategoryVO;
import me.jmix.brothertakeaway.vo.ProductInfoVO;
import me.jmix.brothertakeaway.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/customer/product")
public class CustomerProductController {
    @GetMapping("/list")
    public ResultVO getProductList() {
        ResultVO resultVO = new ResultVO();
        ProductCategoryVO productCategoryVO = new ProductCategoryVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();

        productCategoryVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        resultVO.setData(Arrays.asList(productCategoryVO));
        resultVO.setStateCode(0);
        resultVO.setStateInfo("查询成功");

        return resultVO;
    }
}
