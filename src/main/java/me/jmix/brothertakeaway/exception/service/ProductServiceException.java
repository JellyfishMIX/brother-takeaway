package me.jmix.brothertakeaway.exception.service;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.service.ProductServiceStateEnum;
import me.jmix.brothertakeaway.exception.SellException;

@Getter
public class ProductServiceException extends SellException {
    private static final long serialVersionUID = 9036890328274442133L;

    private String exceptionClassName = this.getClass().getName();

    private Integer stateCode;
    private String stateInfo;

    public ProductServiceException(ProductServiceStateEnum productServiceStateEnum) {
        super(productServiceStateEnum.getStateInfo());
        this.stateCode = productServiceStateEnum.getStateCode();
        this.stateInfo = productServiceStateEnum.getStateInfo();
    }
}
