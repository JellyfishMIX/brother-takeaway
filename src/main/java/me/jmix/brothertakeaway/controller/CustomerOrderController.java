package me.jmix.brothertakeaway.controller;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.enums.CustomerOrderControllerStateEnum;
import me.jmix.brothertakeaway.exception.CustomerOrderControllerException;
import me.jmix.brothertakeaway.form.CustomerOrderForm;
import me.jmix.brothertakeaway.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/customer/order")
@Slf4j
public class CustomerOrderController {
    // 创建订单
    // public ResultVO<Map<String, String>> createOrder(@Valid CustomerOrderForm customerOrderForm, BindingResult bindingResult) {
    //     if (bindingResult.hasErrors()) {
    //         log.error("[创建订单]参数不正确，customerOrderForm = {}", customerOrderForm);
    //         throw new CustomerOrderControllerException(CustomerOrderControllerStateEnum.FORM_PARAM_ERROR.getStateCode(), bindingResult.getFieldError().getDefaultMessage());
    //     }
    //
    //
    // }

    // 订单列表

    // 订单详情

    // 取消订单
}
