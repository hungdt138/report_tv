/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.util.AuthUtil;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.crypto.MD5;
import com.tv.xeeng.reporttool.dao.UserDAO;
import com.tv.xeeng.reporttool.forms.LoginForm;

public class LoginAction extends org.apache.struts.action.Action {

    private static Logger logger = Logger.getLogger(LoginAction.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
        logger.info("doLogin");
        LoginForm mForm = (LoginForm) form;
        logger.info("Parameter username=" + mForm.getUsername());
        logger.info("Parameter password=" + MD5.md5(mForm.getPassword()));

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberLogin = request.getParameter("rememberLogin");

        if (BlahBlahUtil.hasEmptyString(username, password)) {
            // hiện trang đăng nhập
            return mapping.findForward("show");
        }
     
        boolean success = AuthUtil.doUserLogin(username, password, response, rememberLogin != null);
        if (success) {
            logger.info("RETURN SUCCESS");

            response.sendRedirect("index.jsp");
            return null;
        }
        
        logger.info("RETURN ERROR");
        return mapping.findForward(FAIL);
    }
}
