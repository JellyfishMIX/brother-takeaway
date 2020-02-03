package me.jmix.brothertakeaway.enums;

import lombok.Getter;

@Getter
public enum OrderMasterStateEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");

    private Integer stateCode;
    private String stateInfo;

    OrderMasterStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
