package me.jmix.brothertakeaway.service.impl;

import me.jmix.brothertakeaway.entity.ProductInfo;
import me.jmix.brothertakeaway.enums.ProductStateEnum;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @Test
    @Disabled
    void getProductInfoByProductId() {
        ProductInfo productInfo = productService.getProductInfoByProductId("123456");
        assertEquals("123456", productInfo.getProductId());
    }

    @Test
    @Disabled
    void getShelvesProductInfo() {
        List<ProductInfo> productInfoList = productService.getShelvesProductInfo();
        assertNotEquals(0, productInfoList.size());
    }

    @Test
    @Disabled
    void getAllProductInfo() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<ProductInfo> productInfoPage = productService.getAllProductInfo(pageRequest);
        // System.out.println(productInfoPage.getTotalElements());
        assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    @Disabled
    void addProduct() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(99);
        productInfo.setProductDescription("皮皮虾");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(ProductStateEnum.SHELVES.getStateCode());
        productInfo.setCategoryType(2);

        ProductInfo productInfoResult = productService.saveProduct(productInfo);
        assertNotNull(productInfoResult);
    }

    @Test
    @Disabled
    void onSale() {
        String productId = "123456";
        ProductInfo productInfoResult = productService.onSale(productId);
        assertEquals(ProductStateEnum.SHELVES.getStateCode(), productInfoResult.getProductStatus());
    }

    @Test
    @Disabled
    void offSale() {
        String productId = "123456";
        ProductInfo productInfoResult = productService.offSale(productId);
        assertEquals(ProductStateEnum.DISCONTINUED.getStateCode(), productInfoResult.getProductStatus());
    }
}