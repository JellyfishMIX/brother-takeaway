package me.jmix.brothertakeaway.enums.controller;

import lombok.Getter;

@Getter
public enum CustomerOrderControllerStateEnum {
    FORM_PARAM_ERROR(-1001, "表单参数不正确"),
    JSON_CONVERT_ERROR(-1002, "JSONToObject转换错误"),
    SHOPPING_CART_EMPTY(-1003, "购物车为空"),
    CUSTOMER_OPENID_INCONSISTENT(-1004, "订单openid不一致");

    private Integer stateCode;
    private String stateInfo;

    CustomerOrderControllerStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
