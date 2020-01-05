package me.jmix.brothertakeaway.entity;

import lombok.Data;
import me.jmix.brothertakeaway.enums.OrderMasterStateEnum;
import me.jmix.brothertakeaway.enums.PayStateEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_order_master")
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class OrderMaster {
    // 订单Id
    @Id
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
    private String orderAmount;

    // 订单状态，默认为0: 新下单
    private Integer orderStatus = OrderMasterStateEnum.NEW.getStateCode();

    // 支付状态，默认为0: 未支付
    private Integer payStatus = PayStateEnum.WAIT.getStateCode();

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;
}
