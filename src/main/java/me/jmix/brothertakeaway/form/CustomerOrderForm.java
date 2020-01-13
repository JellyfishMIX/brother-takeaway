package me.jmix.brothertakeaway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CustomerOrderForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "顾客姓名必填")
    private String customerName;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "顾客手机号")
    private String customerPhone;

    /**
     * 顾客地址
     */
    @NotEmpty(message = "顾客地址必填")
    private String customerAddress;

    /**
     * 顾客微信openid
     */
    @NotEmpty(message = "顾客微信openid必填")
    private String customerOpenid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
