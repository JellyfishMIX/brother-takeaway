package me.jmix.brothertakeaway.enums;

import lombok.Getter;

@Getter
public enum SecKillStateEnum {
    NO_STOCK(-1001, "没有库存了"),
    THREAD_EXCESS(-1002, "秒杀逻辑上锁中");

    private Integer stateCode;
    private String stateInfo;

    SecKillStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
