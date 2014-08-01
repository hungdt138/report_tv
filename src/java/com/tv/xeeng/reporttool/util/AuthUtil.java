package com.tv.xeeng.reporttool.util;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.crypto.MD5;
import com.tv.xeeng.reporttool.dao.UserDAO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yeuchimse on 15/06/2014.
 */
public class AuthUtil {
    private final static int COOKIE_TIME = 30 * 24 * 3600; // thời gian lưu đăng nhập

    private final static String COOKIE_USERNAME = "auth_u";
    private final static String COOKIE_PASSWORD_MD5 = "auth_ep";

    public static UserBean getLoggedInUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = HttpUtil.getCookieValue(cookies, COOKIE_USERNAME);
        String passwordMd5 = HttpUtil.getCookieValue(cookies, COOKIE_PASSWORD_MD5);
        if (username == null || passwordMd5 == null) {
            return null;
        } else {
            UserBean user = UserDAO.getUserLogin(username, passwordMd5);
            if (user != null && user.isActive()) {
                return user;
            } else {
                return null;
            }
        }
    }

    public static void setAuthCookie(String username, String password,
                              HttpServletResponse response, boolean rememberLogin) {
        Cookie cUsername = new Cookie(COOKIE_USERNAME, username);
        Cookie cPassword = new Cookie(COOKIE_PASSWORD_MD5, MD5.md5(password));
        cUsername.setPath("/");
        cPassword.setPath("/");
        if (rememberLogin) {
            cUsername.setMaxAge(COOKIE_TIME);
            cPassword.setMaxAge(COOKIE_TIME);
        }
        response.addCookie(cUsername);
        response.addCookie(cPassword);
    }

    public static boolean doUserLogin(String username, String plainPassword,
                                      HttpServletResponse response, boolean rememberLogin) {
        String passwordMd5 = MD5.md5(plainPassword);
        UserBean user = UserDAO.getUserLogin(username, passwordMd5);
        if (user != null && user.isActive()) {
            setAuthCookie(username, plainPassword, response, rememberLogin);
            return true;
        } else {

            return false;
        }
    }

    public static void doUserLogout(HttpServletResponse response) {
        HttpUtil.deleteCookie(response, COOKIE_USERNAME);
        HttpUtil.deleteCookie(response, COOKIE_PASSWORD_MD5);
    }
}
