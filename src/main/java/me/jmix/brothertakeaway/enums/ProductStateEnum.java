package me.jmix.brothertakeaway.enums;

import lombok.Getter;

@Getter
public enum ProductStateEnum implements CodeEnum {
    SHELVES(0, "上架"),
    DISCONTINUED(1, "下架");

    private Integer stateCode;
    private String stateInfo;

    ProductStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
