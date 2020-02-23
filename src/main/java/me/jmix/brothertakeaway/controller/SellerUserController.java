package me.jmix.brothertakeaway.controller;

import me.jmix.brothertakeaway.entity.SellerInfo;
import me.jmix.brothertakeaway.enums.controller.SellerUserControllerEnum;
import me.jmix.brothertakeaway.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("login")
    public ModelAndView login(@RequestParam("openid") String openid) {
        Map<String, Object> map = new HashMap<>();
        // 1. openid去和数据库中的数据匹配
        SellerInfo sellerInfo = sellerService.getSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", SellerUserControllerEnum.LOGIN_FAIL);
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        // 2. 设置token至redis
        stringRedisTemplate.opsForValue().set("abc", "testValue");

        // 3. 设置token至cookie

        return null;
    }

    @GetMapping("logout")
    public void logout() {

    }
}
