package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.OrderDetail;
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
class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    @Disabled
    void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123459");
        orderDetail.setOrderId("123457");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("123457");
        orderDetail.setProductName("皮皮虾");
        orderDetail.setProductPrice(new BigDecimal(3.20));
        orderDetail.setProductQuantity(3);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        assertNotNull(result);
    }

    @Test
    @Disabled
    void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("123457");
        assertNotEquals(0, orderDetailList.size());
    }
}