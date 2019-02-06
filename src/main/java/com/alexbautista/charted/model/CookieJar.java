package com.alexbautista.charted.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieJar {
    public static String getCookieValue(String name, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String ret = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    ret = cookie.getValue();
                    break;
                }
            }
        }
        return ret;
    }
}
