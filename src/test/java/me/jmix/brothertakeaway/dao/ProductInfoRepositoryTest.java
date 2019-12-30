package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.ProductInfo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    @Disabled
    public void testSaveProduct() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(8);
        ProductInfo result = productInfoRepository.save(productInfo);
        assertNotNull(result);
    }

    @Test
    @Disabled
    public void testFindByProductStatus() {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(0);
        assertNotEquals(0, productInfoList.size());
    }
}
