package me.jmix.brothertakeaway.enums.service;

import lombok.Getter;

@Getter
public enum  ProductServiceStateEnum {
    PRODUCT_NOT_EXIST(-1001, "商品不存在"),
    PRODUCT_STOCK_ERROR(-1002, "商品库存错误"),
    PRODUCT_STATUS_ERROR(-1003, "商品状态不正确");

    private Integer stateCode;
    private String stateInfo;

    ProductServiceStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
