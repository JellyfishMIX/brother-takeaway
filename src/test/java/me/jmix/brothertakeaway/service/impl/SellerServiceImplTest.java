package me.jmix.brothertakeaway.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.entity.SellerInfo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class SellerServiceImplTest {
    @Autowired
    private SellerServiceImpl sellerService;

    private static final String openid = "abc";

    @Test
    @Disabled
    void getSellerInfoByOpenid() {
        SellerInfo sellerInfoResult = sellerService.getSellerInfoByOpenid(openid);
        assertEquals(openid, sellerInfoResult.getOpenid());
    }
}