package me.jmix.brothertakeaway.enums;

import lombok.Getter;

@Getter
public enum PayStateEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer stateCode;
    private String stateInfo;

    PayStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
