package me.jmix.brothertakeaway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    // 公众号Id
    private String mpAppId;

    // 公众号密钥
    private String mpAppSecret;

    // 商户号
    private java.lang.String mchId;

    // 商户密钥
    private java.lang.String mchKey;

    // 商户证书路径（存放位置，需提前下载后存放，并确保java程序对其有可操作权限）
    private java.lang.String keyPath;

    // 模板消息Id
    private Map<String, String> templateId;
}
