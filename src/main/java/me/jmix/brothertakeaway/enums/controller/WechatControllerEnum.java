package me.jmix.brothertakeaway.enums.controller;

import lombok.Getter;

@Getter
public enum WechatControllerEnum {
    WECHAT_MP_ERROR(-1001, "微信公众号方面错误");

    private Integer stateCode;
    private String stateInfo;

    WechatControllerEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
