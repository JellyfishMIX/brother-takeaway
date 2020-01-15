package me.jmix.brothertakeaway.service;

import me.jmix.brothertakeaway.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO createOrder(OrderDTO orderDTO);

    /**
     * 查询单个订单
     * @return
     */
    OrderDTO getOrderByOrderId(String orderId);

    /**
     * 查询订单列表
     * @param customerOpenid
     * @param pageable
     * @return
     */
    Page<OrderDTO> getOrderListByCustomerOpenid(String customerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    OrderDTO cancelOrder(OrderDTO orderDTO);

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    OrderDTO finishOrder(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    OrderDTO payOrder(OrderDTO orderDTO);
}