package me.jmix.brothertakeaway.aspect;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.constant.CookieConstant;
import me.jmix.brothertakeaway.constant.RedisConstant;
import me.jmix.brothertakeaway.exception.SellerAuthorizeException;
import me.jmix.brothertakeaway.utils.CookieUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * public表示任何公共方法
     * execution：用于匹配方法执行的连接点
     * 第一个*代表所有的返回值类型
     * 第二个*代表所有的类
     * 第三个*代表类所有方法 最后一个..代表所有的参数
     */
    @Pointcut("execution(public * me.jmix.brothertakeaway.controller.Seller*.*(..))" + "&& !execution(public * me.jmix.brothertakeaway.controller.SellerUserController.*(..))")
    public void verify() {}

    /**
     * 登录校验
     */
    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        // 去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
