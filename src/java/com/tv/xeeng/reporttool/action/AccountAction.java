/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.crypto.MD5;
import com.tv.xeeng.reporttool.dao.UserDAO;
import com.tv.xeeng.reporttool.forms.ChangePasswordForm;


public class AccountAction extends org.apache.struts.action.Action {

    private static Logger logger = Logger.getLogger(AccountAction.class);
    private final static String SUCCESS = "success";
    private final static String ERROR = "error";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        if (session != null) {
            if (session.getAttribute("loggedInUser") != null) {
                session.setAttribute("funcMenu", "account");
                String action = request.getParameter("action");
                if (action.equals("changePassword")) {
                    return changePassword(mapping, form, request, response);
                } else if (action.equals("prepare")) {
                    return prepare(mapping, form, request, response);
                }
            }
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward prepare(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward(SUCCESS);
    }

    public ActionForward changePassword(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("loggedInUser");
        if (session != null) {
            if (user != null) {
                ChangePasswordForm inputForm = (ChangePasswordForm) form;
//                logger.info("input Old pass ::::" + inputForm.getPassOld() + " <=>"+ MD5.md5(inputForm.getPassOld()).equalsIgnoreCase("47efcc35ae35ce6c9db0b081e12134a8"));
//                logger.info("input Old Pass MD5 ::::" + MD5.md5(inputForm.getPassOld()));
//                logger.info("input New pass ::::" + inputForm.getPassNew());
//                logger.info("input Retype new pass ::::" + inputForm.getPassNewRetype());
                try {
                    String oldPass = MD5.md5(inputForm.getPassOld());
                    String newPass = MD5.md5(inputForm.getPassNew());
                    if (!user.getPassword().equalsIgnoreCase(oldPass)) {
                        request.setAttribute("message", "incorrectpassword");
//                        logger.info("If 1: Mat khau ko chinh xac, userpass hien tai = \n" + user.getPassword() + " & oldPass = " + oldPass );
                        return mapping.findForward(ERROR);
                    }
                    
                    if (!inputForm.getPassNewRetype().equals(inputForm.getPassNew())) {
                        request.setAttribute("message", "invalidpassword");
//                        logger.info("If 2: Mat khau ko hop le");
                        return mapping.findForward("error");
                    }
                    int result = UserDAO.changePassword(user.getUsername(), MD5.md5(inputForm.getPassNew()));
                    
                    if (result == 0) {
                        request.setAttribute("message", "fail");
//                        logger.info("If 3: changePassword co van de");
                        return mapping.findForward(ERROR);
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage(),ex);
                    request.setAttribute("message", "fail");
                    return mapping.findForward(ERROR);
                }
            }
        }
        request.setAttribute("message", "success");
        return mapping.findForward(SUCCESS);
    }
}
