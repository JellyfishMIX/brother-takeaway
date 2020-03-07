package me.jmix.brothertakeaway.exception.service;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.service.ProductServiceStateEnum;
import me.jmix.brothertakeaway.exception.SellException;

@Getter
public class ProductServiceException extends SellException {
    private static final long serialVersionUID = 9036890328274442133L;

    private static final String exceptionClassName = Thread.currentThread().getStackTrace()[1].getClassName();

    private Integer stateCode;
    private String stateInfo;

    public ProductServiceException(ProductServiceStateEnum productServiceStateEnum) {
        super(productServiceStateEnum.getStateInfo());
        this.stateCode = productServiceStateEnum.getStateCode();
        this.stateInfo = productServiceStateEnum.getStateInfo();
    }
}
