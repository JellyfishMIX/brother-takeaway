package me.jmix.brothertakeaway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth的方法");
        log.info("code = {}", code);

        // 拉取access_token和openid
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx9e44f99c46cc5fcc&secret=4e1434908df4e8486ebb786f49e88f94&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        // access_token和openid在response中
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}
