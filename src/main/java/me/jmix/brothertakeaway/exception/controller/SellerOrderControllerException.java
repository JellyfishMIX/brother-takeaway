package me.jmix.brothertakeaway.exception.controller;

import me.jmix.brothertakeaway.enums.controller.SellerOrderControllerEnum;

public class SellerOrderControllerException extends RuntimeException {
    private static final long serialVersionUID = 9216277450989886105L;

    private Integer stateCode;

    public SellerOrderControllerException(SellerOrderControllerEnum sellerOrderControllerEnum) {
        super(sellerOrderControllerEnum.getStateInfo());
        this.stateCode = sellerOrderControllerEnum.getStateCode();
    }
}
