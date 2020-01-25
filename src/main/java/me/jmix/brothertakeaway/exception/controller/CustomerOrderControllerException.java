package me.jmix.brothertakeaway.exception.controller;

import me.jmix.brothertakeaway.enums.controller.CustomerOrderControllerStateEnum;

public class CustomerOrderControllerException extends RuntimeException {
    private static final long serialVersionUID = 7041929752847179080L;

    private Integer stateCode;

    public CustomerOrderControllerException(CustomerOrderControllerStateEnum customerOrderControllerStateEnum) {
        super(customerOrderControllerStateEnum.getStateInfo());
        this.stateCode = customerOrderControllerStateEnum.getStateCode();
    }

    public CustomerOrderControllerException(Integer stateCode, String errMsg) {
        super(errMsg);
        this.stateCode = stateCode;
    }
}
