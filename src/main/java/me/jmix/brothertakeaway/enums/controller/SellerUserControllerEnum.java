package me.jmix.brothertakeaway.enums.controller;

import lombok.Getter;

@Getter
public enum  SellerUserControllerEnum {
    LOGIN_FAIL(-1001, "登录错误，登录信息不正确"),
    LOGOUT_SUCCESS(1, "登出成功");

    private Integer stateCode;
    private String stateInfo;

    SellerUserControllerEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
