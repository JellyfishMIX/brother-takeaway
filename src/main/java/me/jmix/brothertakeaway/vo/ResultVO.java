package me.jmix.brothertakeaway.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer stateCode;
    private String stateInfo;

    // 具体内容
    private T data;
}
