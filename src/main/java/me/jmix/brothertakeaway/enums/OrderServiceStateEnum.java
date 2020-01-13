package me.jmix.brothertakeaway.enums;

import lombok.Getter;

@Getter
public enum OrderServiceStateEnum {
    PRODUCT_NOT_EXIST(-1001, "商品不存在"),
    ORDER_MASTER_NOT_EXIST(-1002, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(-1003, "订单详情不存在"),
    ORDER_STATUS_ERROR(-1004, "订单状态不正确"),
    ORDER_STATUS_UPDATE_FAILED(-1005, "订单状态更新失败"),
    ORDER_DETAIL_EMPTY(-1006, "订单中商品详情为空");

    private Integer stateCode;
    private String message;

    OrderServiceStateEnum(Integer stateCode, String message) {
        this.stateCode = stateCode;
        this.message = message;
    }
}
