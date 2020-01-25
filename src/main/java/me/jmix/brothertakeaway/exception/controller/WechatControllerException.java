package me.jmix.brothertakeaway.exception.controller;

import me.jmix.brothertakeaway.enums.controller.WechatControllerEnum;

public class WechatControllerException extends RuntimeException {
    private static final long serialVersionUID = -4451085753984161422L;

    private Integer stateCode;

    public WechatControllerException(WechatControllerEnum wechatControllerEnum) {
        super(wechatControllerEnum.getStateInfo());
        this.stateCode = wechatControllerEnum.getStateCode();
    }

    public WechatControllerException(Integer stateCode, String errMsg) {
        super(errMsg);
        this.stateCode = stateCode;
    }
}
