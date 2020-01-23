package me.jmix.brothertakeaway.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        WxMpService wxMpService = new WxMpServiceImpl();
        // 1.配置
        // 2.调用方法
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl("s", WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, StandardCharsets.UTF_8.toString()));
        return redirectUrl;
    }

    // @GetMapping("/userInfo")
    // public void userinfo() {
    //
    // }
}
