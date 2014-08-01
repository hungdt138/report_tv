/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author phuctv
 */
public class UTF8Filter implements Filter {

    private FilterConfig config = null;
    private String defaultEncode = "UTF-8";

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        if (config.getInitParameter("Charset") != null) {
            defaultEncode = config.getInitParameter("Charset");
        }
    }

    public void destroy() {
        this.config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        ServletRequest srequest = request;
        srequest.setCharacterEncoding(defaultEncode);
        chain.doFilter(request, response);
    }
}