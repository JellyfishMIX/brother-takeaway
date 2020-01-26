package me.jmix.brothertakeaway.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.jmix.brothertakeaway.enums.controller.WechatControllerEnum;
import me.jmix.brothertakeaway.exception.controller.WechatControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        // 1.配置 (见config包)
        // 2.调用方法
        String url = "http://sellx.nat300.top/sell/wechat/userinfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, StandardCharsets.UTF_8.toString()));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userinfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();

        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[微信网页授权]{}", e);
            throw new WechatControllerException(WechatControllerEnum.WECHAT_MP_ERROR.getStateCode(), e.getError().getErrorMsg());
        }

        String openid = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openid;
    }
}
