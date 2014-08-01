package com.tv.xeeng.reporttool.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.util.AuthUtil;

/**
 *
 * @author tuanda
 */
public class CheckAccessFilter implements Filter {

    private Set<String> publicPages;
    private int USER_TYPE_ADMIN = 1;
    private String PAGE_TYPE_ADMIN = "/pages/admin/";
    private int USER_TYPE_OPERATOR = 2;
    private String PAGE_TYPE_OPERATOR = "/pages/operator/";
    private int USER_TYPE_GAMEMASTER = 3;
    private String PAGE_TYPE_GAMEMASTER = "/pages/gamemaster/";

    public void init(FilterConfig arg0) throws ServletException {
        publicPages = new HashSet<String>();
        publicPages.add("/index.jsp");
        publicPages.add("/login.jsp");
        publicPages.add("/login.html");
        publicPages.add("/login.html");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse respone = (HttpServletResponse) res;

        UserBean user = AuthUtil.getLoggedInUser(request);
        if (user != null) {
            request.getSession().setAttribute("loggedInUser", user);
        }


        String reqUrl = request.getServletPath();
        System.out.println("Request URL:" + reqUrl);
        if (reqUrl.indexOf(".") > 0) {
            String reqExt = reqUrl.substring(reqUrl.lastIndexOf(".") + 1);
            System.out.println("Extention:" + reqExt);
            if ("html".equals(reqExt) || "ser".equals(reqExt) || "jsp".equals(reqExt)) {
                if (!publicPages.contains(reqUrl)) {
                    if (user == null || !user.isActive()) {
                        respone.sendRedirect("login.html");

                        return;
                    }
                } else {
                	
	                if(reqUrl.contains(PAGE_TYPE_ADMIN) && user.getUserType() != USER_TYPE_ADMIN) {
	                    respone.sendRedirect("permit.html");                	
	                }
	
	                if(reqUrl.contains(PAGE_TYPE_OPERATOR) && user.getUserType() != USER_TYPE_OPERATOR) {
	                    respone.sendRedirect("permit.html");                	
	                }
	                
	                if(reqUrl.contains(PAGE_TYPE_GAMEMASTER) && user.getUserType() != USER_TYPE_GAMEMASTER) {
	                    respone.sendRedirect("permit.html");                	
	                }
                }
                
            }
        }
        
        try {
            filter.doFilter(req, res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void destroy() {
    }
    

    public static void main(String[] args) {
    	System.out.println("Test data");	
    	String admin = "/pages/gamemaster/";
    	
    	String url = "/pages/admin/listGiftCode.html?page=1&menuItem=giftL";
    	
    	if(url.contains(admin)) {
    		System.out.print("Cant not access");
    	} else {
    		System.out.print("OK");
    	}
    	
    }
    
    
}
