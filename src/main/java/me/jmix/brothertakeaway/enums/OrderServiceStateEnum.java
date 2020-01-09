package me.jmix.brothertakeaway.enums;

import lombok.Getter;

@Getter
public enum OrderServiceStateEnum {
    PRODUCT_NOT_EXIT(-1001, "商品不存在");

    private Integer stateCode;
    private String message;

    OrderServiceStateEnum(Integer stateCode, String message) {
        this.stateCode = stateCode;
        this.message = message;
    }
}
