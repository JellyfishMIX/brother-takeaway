package me.jmix.brothertakeaway.controller;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.enums.controller.SellerOrderControllerEnum;
import me.jmix.brothertakeaway.exception.controller.SellerOrderControllerException;
import me.jmix.brothertakeaway.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 分页展示所有订单列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.getAllOrderList(pageRequest);

        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        ModelAndView modelAndView = new ModelAndView("order/list", map);
        return modelAndView;
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId) {
        Map<String, Object> map = new HashMap<>();
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.getOrderByOrderId(orderId);
        } catch (SellerOrderControllerException e) {
            log.error("[卖家端取消订单]查询不到订单");

            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }
        orderService.cancelOrder(orderDTO);

        map.put("msg", SellerOrderControllerEnum.SUCCESS.getStateInfo());
        map.put("url", "/sell/seller/order/list");

        return new ModelAndView("common/success", map);
    }
}
