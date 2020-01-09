package me.jmix.brothertakeaway.exception;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.OrderServiceStateEnum;

@Getter
public class OrderServiceException extends RuntimeException {
    private Integer stateCode;

    public OrderServiceException(OrderServiceStateEnum orderServiceStateEnum) {
        super(orderServiceStateEnum.getMessage());
        this.stateCode = orderServiceStateEnum.getStateCode();
    }
}
