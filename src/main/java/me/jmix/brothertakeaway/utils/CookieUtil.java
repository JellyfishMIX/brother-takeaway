package me.jmix.brothertakeaway.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

    public static Cookie get(HttpServletRequest httpServletRequest,
                           String cookieName) {
        Map<String, Cookie> cookieMap = readCookieMap(httpServletRequest);
        if (cookieMap.containsKey(cookieName)) {
            return cookieMap.get(cookieName);
        } else {
            return null;
        }
    }

    public static Map<String, Cookie> readCookieMap(HttpServletRequest httpServletRequest) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies =  httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }
}
