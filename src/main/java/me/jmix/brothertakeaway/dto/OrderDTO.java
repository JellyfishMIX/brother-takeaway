package me.jmix.brothertakeaway.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lly835.bestpay.enums.OrderStatusEnum;
import lombok.Data;
import me.jmix.brothertakeaway.entity.OrderDetail;
import me.jmix.brothertakeaway.enums.OrderMasterStateEnum;
import me.jmix.brothertakeaway.enums.PayStateEnum;
import me.jmix.brothertakeaway.utils.EnumUtil;
import me.jmix.brothertakeaway.utils.serializer.DateToLongSerializer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
// @JsonInclude(JsonInclude.Include.NON_NULL)
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
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    // 创建时间
    // @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    // 修改时间
    // @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    @JsonIgnore
    public OrderMasterStateEnum getOrderMasterStateEnum() {
        return EnumUtil.getByCode(orderStatus, OrderMasterStateEnum.class);
    }

    @JsonIgnore
    public PayStateEnum getPayStateEnum() {
        return EnumUtil.getByCode(payStatus, PayStateEnum.class);
    }
}
