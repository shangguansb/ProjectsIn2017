package com.quanr.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kingsley.zhang on 2017/3/9.
 */
public class QunarUtils {
    public  static void removeCookies(HttpServletRequest req, HttpServletResponse resp){
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                Cookie cookie1 = new Cookie(cookie.getName(), null);
                cookie1.setMaxAge(0);
                resp.addCookie(cookie1);
            }
        }
    }
}
