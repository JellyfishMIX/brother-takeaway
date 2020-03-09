package me.jmix.brothertakeaway.exception.service;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.service.OrderServiceStateEnum;
import me.jmix.brothertakeaway.exception.SellException;

@Getter
public class OrderServiceException extends SellException {
    private static final long serialVersionUID = 4962462662093451118L;

    private String exceptionClassName = this.getClass().getName();

    private Integer stateCode;
    private String stateInfo;

    public OrderServiceException(OrderServiceStateEnum orderServiceStateEnum) {
        super(orderServiceStateEnum.getStateInfo());
        this.stateCode = orderServiceStateEnum.getStateCode();
        this.stateInfo = orderServiceStateEnum.getStateInfo();
    }
}
