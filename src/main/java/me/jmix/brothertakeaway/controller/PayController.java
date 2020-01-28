package me.jmix.brothertakeaway.controller;

import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.enums.controller.PayControllerEnum;
import me.jmix.brothertakeaway.exception.controller.PayControllerException;
import me.jmix.brothertakeaway.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建支付订单
     * @param orderId
     * @param returnUrl
     */
    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl) {
        // 1. 查询订单
        OrderDTO orderDTO = orderService.getOrderByOrderId(orderId);
        if (orderDTO == null) {
            throw new PayControllerException(PayControllerEnum.ORDER_MASTER_NOT_EXIST);
        }

        // 2. 发起支付

    }
}
