package me.jmix.brothertakeaway.exception.service;

import me.jmix.brothertakeaway.enums.service.SecKillServiceStateEnum;
import me.jmix.brothertakeaway.exception.SellException;

public class SecKillServiceException extends SellException {
    private String exceptionClassName = this.getClass().getName();

    private Integer stateCode;
    private String stateInfo;

    public SecKillServiceException(SecKillServiceStateEnum secKillServiceStateEnum) {
        super(secKillServiceStateEnum.getStateInfo());
        this.stateCode = secKillServiceStateEnum.getStateCode();
        this.stateInfo = secKillServiceStateEnum.getStateInfo();
    }
}
