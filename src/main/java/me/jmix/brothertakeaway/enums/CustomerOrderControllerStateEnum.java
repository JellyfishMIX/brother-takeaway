package me.jmix.brothertakeaway.enums;

import lombok.Getter;

@Getter
public enum CustomerOrderControllerStateEnum {
    FORM_PARAM_ERROR(-1001, "表单参数不正确");

    private Integer stateCode;
    private String stateInfo;

    CustomerOrderControllerStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
