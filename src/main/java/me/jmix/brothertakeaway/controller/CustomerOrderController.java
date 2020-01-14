package me.jmix.brothertakeaway.controller;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.converter.CustomerOrderFormToOrderDTOConverter;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.enums.CustomerOrderControllerStateEnum;
import me.jmix.brothertakeaway.exception.CustomerOrderControllerException;
import me.jmix.brothertakeaway.form.CustomerOrderForm;
import me.jmix.brothertakeaway.service.OrderService;
import me.jmix.brothertakeaway.utils.ResultVOUtil;
import me.jmix.brothertakeaway.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer/order")
@Slf4j
public class CustomerOrderController {
    @Autowired
    private OrderService orderService;

    // 创建订单
    public ResultVO<Map<String, String>> createOrder(@Valid CustomerOrderForm customerOrderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，customerOrderForm = {}", customerOrderForm);
            throw new CustomerOrderControllerException(CustomerOrderControllerStateEnum.FORM_PARAM_ERROR.getStateCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = CustomerOrderFormToOrderDTOConverter.convert(customerOrderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单]购物车不能为空，customerOrderForm = {}", customerOrderForm);
            throw new CustomerOrderControllerException(CustomerOrderControllerStateEnum.SHOPPING_CART_EMPTY);
        }
        OrderDTO orderDTOResult = orderService.createOrder(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDTOResult.getOrderId());

        // ResultVO resultVO = new ResultVO();
        // resultVO.setStateCode(0);
        // resultVO.setStateInfo("创建订单成功");
        // resultVO.setData(map);


        return ResultVOUtil.success("创建订单成功", map);
    }

    // 订单列表

    // 订单详情

    // 取消订单
}
