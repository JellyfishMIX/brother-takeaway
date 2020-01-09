// package me.jmix.brothertakeaway.service.impl;
//
// import me.jmix.brothertakeaway.dto.OrderDTO;
// import me.jmix.brothertakeaway.service.OrderService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;
//
// @Service
// public class OrderServiceImpl implements OrderService {
//     @Autowired
//
//
//     @Override
//     public OrderDTO CreateOrder(OrderDTO orderDTO) {
//         // 1. 查询商品（数量，价格）
//
//         // 2. 计算总价
//
//         // 3. 写入订单数据库（orderMaster和orderDetail）
//
//         // 4. 扣库存
//
//         return null;
//     }
//
//     @Override
//     public OrderDTO getOrderByOrderId(String orderId) {
//         return null;
//     }
//
//     @Override
//     public Page<OrderDTO> getOrderByCustomerOpenid(String customerOpenId, Pageable pageable) {
//         return null;
//     }
//
//     @Override
//     public OrderDTO cancelOrder(OrderDTO orderDTO) {
//         return null;
//     }
//
//     @Override
//     public OrderDTO finishOrder(OrderDTO orderDTO) {
//         return null;
//     }
//
//     @Override
//     public OrderDTO payOrder(OrderDTO orderDTO) {
//         return null;
//     }
// }
