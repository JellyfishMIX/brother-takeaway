package me.jmix.brothertakeaway.enums.controller;

import lombok.Getter;

@Getter
public enum  PayControllerEnum {
    ORDER_MASTER_NOT_EXIST(-1001, "订单不存在");

    private Integer stateCode;
    private String stateInfo;

    PayControllerEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
