package me.jmix.brothertakeaway.enums.controller;

import lombok.Getter;

@Getter
public enum  SellerOrderControllerEnum {
    FINISH_ORDER_SUCCESS(1001, "完结订单成功"),
    CANCEL_ORDER_SUCCESS(1002, "取消订单成功");

    private Integer stateCode;
    private String stateInfo;

    SellerOrderControllerEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
