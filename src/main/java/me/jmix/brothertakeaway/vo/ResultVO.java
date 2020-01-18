package me.jmix.brothertakeaway.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;

    // 具体内容
    private T data;
}
