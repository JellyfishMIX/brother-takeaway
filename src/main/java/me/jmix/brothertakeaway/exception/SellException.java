package me.jmix.brothertakeaway.exception;

import lombok.Getter;

@Getter
public class SellException extends RuntimeException {
    private static final long serialVersionUID = 2930168342534521500L;

    private String exceptionClassName;

    private Integer stateCode;
    private String stateInfo;

    public SellException(String msg) {
        super(msg);
    }
}
