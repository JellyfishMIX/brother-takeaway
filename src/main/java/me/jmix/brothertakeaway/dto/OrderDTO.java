package me.jmix.brothertakeaway.dto;

import lombok.Data;
import me.jmix.brothertakeaway.entity.OrderDetail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    // 订单Id
    private String orderId;

    // 顾客名字
    private String customerName;

    // 顾客手机号
    private String customerPhone;

    // 顾客地址
    private String customerAddress;

    // 顾客微信openid
    private String customerOpenid;

    // 订单总金额
    private BigDecimal orderAmount;

    // 订单状态，默认为0: 新下单
    private Integer orderStatus;

    // 支付状态，默认为0: 未支付
    private Integer payStatus;

    // 订单详情商品列表
    private List<OrderDetail> orderDetailList;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;
}