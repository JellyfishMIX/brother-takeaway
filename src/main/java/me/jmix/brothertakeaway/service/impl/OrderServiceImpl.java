package me.jmix.brothertakeaway.service.impl;

import me.jmix.brothertakeaway.dao.OrderDetailRepository;
import me.jmix.brothertakeaway.dao.OrderMasterRepository;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.entity.OrderDetail;
import me.jmix.brothertakeaway.entity.OrderMaster;
import me.jmix.brothertakeaway.entity.ProductInfo;
import me.jmix.brothertakeaway.enums.OrderServiceStateEnum;
import me.jmix.brothertakeaway.exception.OrderServiceException;
import me.jmix.brothertakeaway.service.OrderService;
import me.jmix.brothertakeaway.service.ProductService;
import me.jmix.brothertakeaway.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO CreateOrder(OrderDTO orderDTO) {
        // 在创建订单的最开始，生成此订单的Id
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);

        // 1. 查询商品（数量，价格）。因为涉及金额数据，较为敏感，为防止前端传来错误金额，这里需从数据库查询商品对应的金额
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.getProductInfoByProductId(orderDetail.getProductId());
            if (productInfo == null) {
                throw new OrderServiceException((OrderServiceStateEnum.PRODUCT_NOT_EXIT));
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
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);

        // 4. 扣库存

        return null;
    }

    @Override
    public OrderDTO getOrderByOrderId(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> getOrderByCustomerOpenid(String customerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO payOrder(OrderDTO orderDTO) {
        return null;
    }
}
