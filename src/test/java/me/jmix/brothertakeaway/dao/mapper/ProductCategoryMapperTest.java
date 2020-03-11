package me.jmix.brothertakeaway.dao.mapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    @Disabled
    void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "师兄最不爱");
        map.put("categoryType", 101);
        int result = productCategoryMapper.insertByMap(map);
        assertEquals(1, result);
    }
}