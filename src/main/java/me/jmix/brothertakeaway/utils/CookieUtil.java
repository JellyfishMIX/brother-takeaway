package me.jmix.brothertakeaway.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    /**
     * 设置cookie
     * @param httpServletResponse
     * @param name
     * @param value
     * @param maxAge
     */
    public static void  set(HttpServletResponse httpServletResponse,
                            String name,
                            String value,
                            int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        httpServletResponse.addCookie(cookie);
    }
}
