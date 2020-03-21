package me.jmix.brothertakeaway.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.converter.OrderMasterToOrderDTOConverter;
import me.jmix.brothertakeaway.dao.OrderDetailRepository;
import me.jmix.brothertakeaway.dao.OrderMasterRepository;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.dto.ShoppingCartDTO;
import me.jmix.brothertakeaway.entity.OrderDetail;
import me.jmix.brothertakeaway.entity.OrderMaster;
import me.jmix.brothertakeaway.entity.ProductInfo;
import me.jmix.brothertakeaway.enums.OrderMasterStateEnum;
import me.jmix.brothertakeaway.enums.service.OrderServiceStateEnum;
import me.jmix.brothertakeaway.enums.PayStateEnum;
import me.jmix.brothertakeaway.exception.service.OrderServiceException;
import me.jmix.brothertakeaway.service.OrderService;
import me.jmix.brothertakeaway.service.ProductService;
import me.jmix.brothertakeaway.service.PushMessageService;
import me.jmix.brothertakeaway.service.WebSocket;
import me.jmix.brothertakeaway.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PushMessageService pushMessageService;
    @Autowired
    private WebSocket webSocket;

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        // 在创建订单的最开始，生成此订单的Id
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);

        // 1. 查询商品（数量，价格）。因为涉及金额数据，较为敏感，为防止前端传来错误金额，这里需从数据库查询商品对应的金额
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.getProductInfoByProductId(orderDetail.getProductId());
            if (productInfo == null) {
                throw new OrderServiceException((OrderServiceStateEnum.PRODUCT_NOT_EXIST));
            }
            // 2. 计算总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        // 3. 写入订单数据库（orderMaster和orderDetail）
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        orderDTO.setOrderStatus(OrderMasterStateEnum.NEW.getStateCode());
        orderDTO.setPayStatus(PayStateEnum.WAIT.getStateCode());
        OrderMaster orderMaster = new OrderMaster();
        // 此步不可更换位置，否则会导致BeanUtils.copyProperties把空属性（orderId, orderAmount）拷贝进orderDetail中
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);

        // 4. 减库存
        // 需要再加入增减库存方法
        List<ShoppingCartDTO> shoppingCartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new ShoppingCartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(shoppingCartDTOList);

        // 发送websocket消息，同步推
        webSocket.sendMessage(orderDTO.getOrderId());

        return orderDTO;
    }

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO getOrderByOrderId(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if (orderMaster == null) {
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_MASTER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (orderDetailList == null) {
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 查询订单列表
     * @param customerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> getOrderListByCustomerOpenid(String customerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByCustomerOpenid(customerOpenid, pageable);

        // 把查询出来的Page<OrderMaster>转换为Page<OrderDTO>
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        for (OrderDTO orderDTO : orderDTOList) {
            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderDTO.getOrderId());
            if (orderDetailList == null) {
                throw new OrderServiceException(OrderServiceStateEnum.ORDER_DETAIL_NOT_EXIST);
            }
            orderDTO.setOrderDetailList(orderDetailList);
        }

        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    /**
     * 获取全部订单列表
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> getAllOrderList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert((orderMasterPage.getContent()));
        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        // 判断订单状态是否可修改
        if (!orderDTO.getOrderStatus().equals(OrderMasterStateEnum.NEW.getStateCode())) {
            log.info("[取消订单]订单状态不正确，orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单的状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderMasterStateEnum.CANCEL.getStateCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster orderMasterUpdateResult = orderMasterRepository.save(orderMaster);
        if (orderMasterUpdateResult == null) {
            log.info("[取消订单]订单状态更新失败，orderMaster = {}", orderMaster);
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_STATUS_UPDATE_FAILED);
        }

        // 返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[取消订单]，订单中商品详情为空，orderDTO = {}", orderDTO);
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_DETAIL_EMPTY);
        }
        List<ShoppingCartDTO> shoppingCartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new ShoppingCartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(shoppingCartDTOList);

        // 如果已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStateEnum.SUCCESS)) {
            // TODO [取消订单]，如果已支付，需要退款
        }

        return orderDTO;
    }

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        // 判断订单状态是否可修改
        if (!orderDTO.getPayStatus().equals(OrderMasterStateEnum.NEW.getStateCode())) {
            log.info("[完结订单]订单状态不正确, orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_STATUS_ERROR);
        }

        // 修改状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderMasterStateEnum.FINISHED.getStateCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster orderMasterResult = orderMasterRepository.save(orderMaster);
        if (orderMasterResult == null) {
            log.info("[完结订单]订单状态更新失败，orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_STATUS_UPDATE_FAILED);
        }

        // 推送微信模板消息
        pushMessageService.orderStatus(orderDTO);

        return orderDTO;
    }

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO payOrder(OrderDTO orderDTO) {
        // 判断订单状态是否可修改
        if (!orderDTO.getOrderStatus().equals(OrderMasterStateEnum.NEW.getStateCode())) {
            log.info("[支付订单]订单状态不正确, orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_STATUS_ERROR);
        }

        // 判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStateEnum.WAIT.getStateCode())) {
            log.info("[支付订单]订单支付状态不正确, orderId = {}, payStatus = {}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStateEnum.SUCCESS.getStateCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster orderMasterResult = orderMasterRepository.save(orderMaster);
        if (orderMasterResult == null) {
            log.info("[支付订单]订单支付状态更新失败，orderId = {}, payStatus = {}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new OrderServiceException(OrderServiceStateEnum.ORDER_PAY_STATUS_UPDATE_FAILED);
        }

        return orderDTO;
    }
}
