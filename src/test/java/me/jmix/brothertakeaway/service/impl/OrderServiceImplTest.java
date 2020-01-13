package me.jmix.brothertakeaway.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.entity.OrderDetail;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private final String CUSTOMER_OPENID = "101010";
    private final String ORDER_ID = "1578751170026180631";

    @Test
    @Disabled
    void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerName("廖师兄");
        orderDTO.setCustomerAddress("中关村03号");
        orderDTO.setCustomerPhone("12345678901");
        orderDTO.setCustomerOpenid(CUSTOMER_OPENID);

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        // 第一件商品
        OrderDetail orderDetail0 = new OrderDetail();
        orderDetail0.setProductId("123458");
        orderDetail0.setProductQuantity(2);
        orderDetailList.add(orderDetail0);

        // 第二件商品
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123456");
        orderDetail1.setProductQuantity(3);
        orderDetailList.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO orderDTOResult = orderService.createOrder(orderDTO);
        log.info("[创建订单] orderDTOResult={}", orderDTOResult);
        assertNotNull(orderDTOResult);
    }

    @Test
    @Disabled
    void getOrderByOrderId() {
        OrderDTO orderDTOResult = orderService.getOrderByOrderId(ORDER_ID);
        log.info("[查询单个订单]result = {}", orderDTOResult);
        assertEquals(ORDER_ID, orderDTOResult.getOrderId());
    }

    @Test
    @Disabled
    void getOrderByCustomerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.getOrderByCustomerOpenid(CUSTOMER_OPENID, pageRequest);
        assertNotNull(orderDTOPage);
    }

    @Test
    @Disabled
    void cancelOrder() {
        OrderDTO orderDTO = orderService.getOrderByOrderId(ORDER_ID);
        OrderDTO orderDTOResult = orderService.cancelOrder(orderDTO);
        assertEquals(ORDER_ID, orderDTOResult.getOrderId());
    }

    @Test
    @Disabled
    void finishOrder() {
    }

    @Test
    @Disabled
    void payOrder() {
    }
}