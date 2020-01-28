package me.jmix.brothertakeaway.exception.controller;

import me.jmix.brothertakeaway.enums.controller.PayControllerEnum;

public class PayControllerException extends RuntimeException {
    private static final long serialVersionUID = 4890346900249558631L;

    private Integer stateCode;

    public PayControllerException(PayControllerEnum payControllerEnum) {
        super(payControllerEnum.getStateInfo());
        this.stateCode = payControllerEnum.getStateCode();
    }
}
