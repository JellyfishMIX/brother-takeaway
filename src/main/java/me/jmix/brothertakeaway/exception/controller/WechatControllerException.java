package me.jmix.brothertakeaway.exception.controller;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.controller.WechatControllerEnum;
import me.jmix.brothertakeaway.exception.SellException;

@Getter
public class WechatControllerException extends SellException {
    private static final long serialVersionUID = -4451085753984161422L;

    private String exceptionClassName = this.getClass().getName();

    private Integer stateCode;
    private String stateInfo;

    public WechatControllerException(WechatControllerEnum wechatControllerEnum) {
        super(wechatControllerEnum.getStateInfo());
        this.stateCode = wechatControllerEnum.getStateCode();
        this.stateInfo = wechatControllerEnum.getStateInfo();
    }

    public WechatControllerException(Integer stateCode, String errMsg) {
        super(errMsg);
        this.stateCode = stateCode;
        this.stateInfo = errMsg;
    }
}
