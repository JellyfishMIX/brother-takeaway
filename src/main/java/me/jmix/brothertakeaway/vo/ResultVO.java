package me.jmix.brothertakeaway.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JellyfishMIX
 */
@Data
public class ResultVO<T> implements Serializable {
    /**
     * 供序列化/反序列化使用，读写redis
     */
    private static final long serialVersionUID = -1976264085475773430L;

    /**
     * 表示异常的ResultVO需要使用此成员变量
     */
    private String exceptionClassName;

    private Integer code;
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
