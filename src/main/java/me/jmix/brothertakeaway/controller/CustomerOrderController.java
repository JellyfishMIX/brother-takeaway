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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer/order")
@Slf4j
public class CustomerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param customerOrderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/createorder")
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

    /**
     * 查询订单列表，分页查询
     * @return
     */
    @GetMapping("/getorderlistbyopenid")
    public ResultVO<List<OrderDTO>> getOrderListByCustomerOpenid(@RequestParam("openid") String customerOpenid,
                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        if (StringUtils.isEmpty(customerOpenid)) {
            log.error("[查询订单列表]customerOpenid为空");
            throw new CustomerOrderControllerException(CustomerOrderControllerStateEnum.FORM_PARAM_ERROR);
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<OrderDTO> orderDTOPage = orderService.getOrderListByCustomerOpenid(customerOpenid, pageRequest);

        return ResultVOUtil.success("查询订单列表成功", orderDTOPage.getContent());
    }

    /**
     * 订单详情
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/getorderbyorderid")
    public ResultVO<OrderDTO> getOrderByOrderId(@RequestParam("openid") String openid,
                                                @RequestParam("orderId") String orderId) {
        // TODO 需要校验openid是否越权，一个用户不能查询别人的订单
        OrderDTO orderDTO = orderService.getOrderByOrderId(orderId);
        return ResultVOUtil.success("查询成功", orderDTO);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("cancelorderbyorderid")
    public ResultVO cancelOrderByOrderId(@RequestParam("openid") String openid,
                                         @RequestParam("orderId") String orderId) {
        // TODO 需要校验openid是否越权，一个用户不能取消别人的订单
        OrderDTO orderDTO = orderService.getOrderByOrderId(orderId);
        orderService.cancelOrder(orderDTO);
        return ResultVOUtil.success();
    }
}
