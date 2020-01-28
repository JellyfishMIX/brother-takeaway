package me.jmix.brothertakeaway.service.impl;

import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.service.PayService;

public class PayServiceImpl implements PayService {
    /**
     * 创建支付订单
     * @param orderDTO
     */
    @Override
    public void create(OrderDTO orderDTO) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        // bestPayService.setWxPayConfig();
    }
}
