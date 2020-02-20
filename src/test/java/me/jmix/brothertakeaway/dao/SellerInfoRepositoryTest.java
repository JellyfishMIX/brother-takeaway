package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.SellerInfo;
import me.jmix.brothertakeaway.utils.KeyUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");

        SellerInfo sellerInfoResult = sellerInfoRepository.save(sellerInfo);
        assertNotNull(sellerInfoResult);
    }

    @Test
    void findByOpenid() {
        SellerInfo sellerInfoResult = sellerInfoRepository.findByOpenid("abc");
        assertEquals("abc", sellerInfoResult.getOpenid());
    }
}