/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.crypto.MD5;
import com.tv.xeeng.reporttool.dao.UserDAO;
import com.tv.xeeng.reporttool.forms.ChangePasswordForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PageNotFoundAction extends org.apache.struts.action.Action {

    private final static String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward(SUCCESS);
    }
}
