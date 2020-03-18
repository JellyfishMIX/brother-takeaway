package me.jmix.brothertakeaway.enums.service;

import lombok.Getter;

@Getter
public enum SecKillServiceStateEnum {
    NO_STOCK(-1001, "没有库存了");

    private Integer stateCode;
    private String stateInfo;

    SecKillServiceStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
