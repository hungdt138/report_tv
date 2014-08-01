/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.UserDAO;

/**
 * @author PHUCTV
 */
public class SuperUsersAction extends org.apache.struts.action.Action {

    private static Logger logger = Logger.getLogger(SuperUsersAction.class);
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping  The ActionMapping used to select this instance.
     * @param form     The optional ActionForm bean for this request.
     * @param request  The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("superaccount");
        HttpSession session = request.getSession();
        if (request.getParameter("page") != null) {
            int pageC = Integer.parseInt(request.getParameter("page"));

            if (pageC > 0) {
                if (session != null) {

                    session.setAttribute("funcMenu", "superaccount");

                    try {
                        List<UserBean> data = UserDAO.getSuperUsers(pageC);
                        request.setAttribute("page", pageC);
                        request.setAttribute("totalPage", data.get(0).getTotalPage());
                        request.setAttribute("totalRecord", data.get(0).getTotalRecord());
                        request.setAttribute("userslist", data);
                    } catch (Exception ex) {
                        logger.info(ex.getMessage(), ex);
                        request.setAttribute("message", "Error in processing");
                        return mapping.findForward(ERROR);
                    }
                    return mapping.findForward(SUCCESS);
                }
            } else {
                try {
                    List<UserBean> data = UserDAO.getSuperUsers(1);
                    request.setAttribute("page", 1);
                    request.setAttribute("totalPage", data.get(0).getTotalPage());
                    request.setAttribute("totalRecord", data.get(0).getTotalRecord());
                    request.setAttribute("userslist", data);
                } catch (Exception ex) {
                    logger.info(ex.getMessage(), ex);
                    request.setAttribute("message", "Error in processing");
                    return mapping.findForward(ERROR);
                }
                return mapping.findForward(SUCCESS);
            }
        } else {
            try {
                List<UserBean> data = UserDAO.getSuperUsers(1);
                request.setAttribute("page", 1);
                request.setAttribute("totalPage", data.get(0).getTotalPage());
                request.setAttribute("totalRecord", data.get(0).getTotalRecord());
                request.setAttribute("userslist", data);
            } catch (Exception ex) {
                logger.info(ex.getMessage(), ex);
                request.setAttribute("message", "Error in processing");
                return mapping.findForward(ERROR);
            }
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);

    }
}
