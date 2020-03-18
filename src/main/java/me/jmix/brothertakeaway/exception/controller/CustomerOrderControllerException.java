package me.jmix.brothertakeaway.exception.controller;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.controller.CustomerOrderControllerStateEnum;
import me.jmix.brothertakeaway.exception.SellException;

@Getter
public class CustomerOrderControllerException extends SellException {
    private static final long serialVersionUID = 7041929752847179080L;

    private String exceptionClassName = this.getClass().getName();

    private Integer stateCode;
    private String stateInfo;

    public CustomerOrderControllerException(CustomerOrderControllerStateEnum customerOrderControllerStateEnum) {
        super(customerOrderControllerStateEnum.getStateInfo());
        this.stateCode = customerOrderControllerStateEnum.getStateCode();
        this.stateInfo = customerOrderControllerStateEnum.getStateInfo();
    }

    public CustomerOrderControllerException(Integer stateCode, String errMsg) {
        super(errMsg);
        this.stateCode = stateCode;
        this.stateInfo = errMsg;
    }
}
