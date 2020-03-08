package me.jmix.brothertakeaway.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    // 表示异常的ResultVO需要使用此成员变量
    private String exceptionClassName;

    private Integer code;
    private String msg;

    // 具体内容
    private T data;
}
