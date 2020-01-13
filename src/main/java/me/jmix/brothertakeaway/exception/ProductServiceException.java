package me.jmix.brothertakeaway.exception;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.ProductServiceStateEnum;

@Getter
public class ProductServiceException extends RuntimeException {
    private static final long serialVersionUID = 9036890328274442133L;

    private Integer stateCode;

    public ProductServiceException(ProductServiceStateEnum productServiceStateEnum) {
        super(productServiceStateEnum.getStateInfo());
        this.stateCode = productServiceStateEnum.getStateCode();
    }
}
