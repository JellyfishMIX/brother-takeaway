package me.jmix.brothertakeaway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "project-url")
@Component
public class ProjectUrlConfig {
    // 微信公众平台授权url  配置授权域即可
    public String wechatMpAuthorize;

    // 微信开放平台授权url 配置授权域即可
    public String wechatOpenAuthorize;

    // 本项目的地址
    public String project;
}
