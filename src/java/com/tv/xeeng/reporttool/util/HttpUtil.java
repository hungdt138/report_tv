/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thanhnvt
 */
public class HttpUtil {

    public static String getCookieValue(Cookie[] cookies, String name) {
        if (cookies == null) {
            return null;
        }
        
        for (Cookie c : cookies) {
            if (c.getName().equals(name)) {
                return c.getValue();
            }
        }

        return null;
    }

    public static void deleteCookie(HttpServletResponse response, String cookieName) {
        Cookie c = new Cookie(cookieName, "");
        c.setPath("/");
        c.setMaxAge(0);

        response.addCookie(c);
    }
}