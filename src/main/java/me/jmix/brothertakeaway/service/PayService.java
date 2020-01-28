package me.jmix.brothertakeaway.service;

import me.jmix.brothertakeaway.dto.OrderDTO;

public interface PayService {
    /**
     * 创建支付订单
     * @param orderDTO
     */
    void create(OrderDTO orderDTO);
}
