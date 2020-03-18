package me.jmix.brothertakeaway.exception.controller;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.controller.PayControllerEnum;
import me.jmix.brothertakeaway.exception.SellException;

@Getter
public class PayControllerException extends SellException {
    private static final long serialVersionUID = 4890346900249558631L;

    private String exceptionClassName = this.getClass().getName();

    private Integer stateCode;
    private String stateInfo;

    public PayControllerException(PayControllerEnum payControllerEnum) {
        super(payControllerEnum.getStateInfo());
        this.stateCode = payControllerEnum.getStateCode();
        this.stateInfo = payControllerEnum.getStateInfo();
    }
}
