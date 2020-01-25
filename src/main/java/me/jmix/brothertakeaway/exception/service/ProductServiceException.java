package me.jmix.brothertakeaway.exception.service;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.service.ProductServiceStateEnum;

@Getter
public class ProductServiceException extends RuntimeException {
    private static final long serialVersionUID = 9036890328274442133L;

    private Integer stateCode;

    public ProductServiceException(ProductServiceStateEnum productServiceStateEnum) {
        super(productServiceStateEnum.getStateInfo());
        this.stateCode = productServiceStateEnum.getStateCode();
    }
}
