package me.jmix.brothertakeaway.exception;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.OrderServiceStateEnum;

@Getter
public class OrderServiceException extends RuntimeException {
    private static final long serialVersionUID = 4962462662093451118L;

    private Integer stateCode;

    public OrderServiceException(OrderServiceStateEnum orderServiceStateEnum) {
        super(orderServiceStateEnum.getMessage());
        this.stateCode = orderServiceStateEnum.getStateCode();
    }
}
