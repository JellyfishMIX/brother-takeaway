package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.OrderMaster;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String OPENID = "101012";

    @Test
    @Disabled
    void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123458");
        orderMaster.setCustomerName("小明");
        orderMaster.setCustomerPhone("12345678901");
        orderMaster.setCustomerAddress("中关村023号");
        orderMaster.setCustomerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(38.5));

        OrderMaster result = orderMasterRepository.save(orderMaster);
        assertNotNull(result);
    }

    @Test
    @Disabled
    void findByCustomerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<OrderMaster> result = orderMasterRepository.findByCustomerOpenid(OPENID, pageRequest);
        System.out.println(result.getTotalElements());
    }
}