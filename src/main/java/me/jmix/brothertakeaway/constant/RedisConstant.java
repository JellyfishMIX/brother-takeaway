package me.jmix.brothertakeaway.constant;

public interface RedisConstant {
    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 7200;  // token有效期，2小时
}
