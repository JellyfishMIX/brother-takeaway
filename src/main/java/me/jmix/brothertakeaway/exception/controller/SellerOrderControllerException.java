package me.jmix.brothertakeaway.exception.controller;

import lombok.Getter;
import me.jmix.brothertakeaway.enums.controller.SellerOrderControllerEnum;
import me.jmix.brothertakeaway.exception.SellException;

@Getter
public class SellerOrderControllerException extends SellException {
    private static final long serialVersionUID = 9216277450989886105L;

    private static final String exceptionClassName = Thread.currentThread().getStackTrace()[1].getClassName();

    private Integer stateCode;
    private String stateInfo;

    public SellerOrderControllerException(SellerOrderControllerEnum sellerOrderControllerEnum) {
        super(sellerOrderControllerEnum.getStateInfo());
        this.stateCode = sellerOrderControllerEnum.getStateCode();
        this.stateInfo = sellerOrderControllerEnum.getStateInfo();
    }
}
