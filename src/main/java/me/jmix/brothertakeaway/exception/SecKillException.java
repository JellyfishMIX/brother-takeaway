package me.jmix.brothertakeaway.exception;

import me.jmix.brothertakeaway.enums.SecKillStateEnum;

public class SecKillException extends SellException {
    private String exceptionClassName = this.getClass().getName();

    private Integer stateCode;
    private String stateInfo;

    public SecKillException(SecKillStateEnum secKillStateEnum) {
        super(secKillStateEnum.getStateInfo());
        this.stateCode = secKillStateEnum.getStateCode();
        this.stateInfo = secKillStateEnum.getStateInfo();
    }
}
