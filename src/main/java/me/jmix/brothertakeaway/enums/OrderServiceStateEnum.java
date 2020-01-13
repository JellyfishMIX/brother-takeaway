package me.jmix.brothertakeaway.enums;

import lombok.Getter;

@Getter
public enum OrderServiceStateEnum {
    PRODUCT_NOT_EXIST(-1001, "商品不存在"),
    ORDER_MASTER_NOT_EXIST(-1002, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(-1003, "订单详情不存在"),
    ORDER_STATUS_ERROR(-1004, "订单状态不正确"),
    ORDER_STATUS_UPDATE_FAILED(-1005, "订单状态更新失败"),
    ORDER_DETAIL_EMPTY(-1006, "订单中商品详情为空"),
    ORDER_PAY_STATUS_ERROR(-1007, "订单支付状态不正确"),
    ORDER_PAY_STATUS_UPDATE_FAILED(-1008, "订单支付状态更新失败");

    private Integer stateCode;
    private String stateInfo;

    OrderServiceStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
