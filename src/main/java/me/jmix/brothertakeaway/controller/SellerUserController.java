package me.jmix.brothertakeaway.controller;

import me.jmix.brothertakeaway.constant.CookieConstant;
import me.jmix.brothertakeaway.constant.RedisConstant;
import me.jmix.brothertakeaway.entity.SellerInfo;
import me.jmix.brothertakeaway.enums.controller.SellerUserControllerEnum;
import me.jmix.brothertakeaway.service.SellerService;
import me.jmix.brothertakeaway.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse httpServletResponse) {
        Map<String, Object> map = new HashMap<>();
        // 1. openid去和数据库中的数据匹配
        SellerInfo sellerInfo = sellerService.getSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", SellerUserControllerEnum.LOGIN_FAIL);
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        // 2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        // 3. 设置token至cookie
        CookieUtil.set(httpServletResponse, CookieConstant.TOKEN, openid, expire);

        return new ModelAndView("redirect:" + "/seller/order/list");
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) {
        Map<String, Object> modelAndViewMap = new HashMap<>();
        // 1. 从cookie中查询
        Cookie cookie = CookieUtil.get(httpServletRequest, CookieConstant.TOKEN);
        if (cookie != null) {
            // 2. 清除redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            // 3. 清除cookie
            CookieUtil.set(httpServletResponse, CookieConstant.TOKEN, null, 0);
        }  // 如果cookie为null， 正好省去了清除步骤，也是登出成功的


        modelAndViewMap.put("msg", SellerUserControllerEnum.LOGOUT_SUCCESS);
        modelAndViewMap.put("url", "sell/seller/order/list");

        return new ModelAndView("common/success", modelAndViewMap);
    }
}
