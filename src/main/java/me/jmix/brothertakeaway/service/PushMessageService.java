package me.jmix.brothertakeaway.service;

import me.jmix.brothertakeaway.dto.OrderDTO;

/**
 * 微信模版消息推送
 */
public interface PushMessageService {
    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
