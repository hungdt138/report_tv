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

import com.tv.xeeng.reporttool.beans.AdminAdvBean;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.AdminDAO;

/**
 *
 * @author PHUCTV
 */
public class AdvListAction extends org.apache.struts.action.Action {

    private static Logger logger = Logger.getLogger(AdvListAction.class);
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("GetAdvertisingList");

        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()
                && !loggedInUser.getIsOperator()
                && !loggedInUser.getIsGameMaster()) {
            response.sendRedirect("404.html");
            return null;
        }

        HttpSession session = request.getSession();
        if (session != null) {
                session.setAttribute("funcMenu", "advertisinglist");
                int page = 0;
                if (request.getParameter("currentPageIndex") != null) {
                    page = Integer.parseInt(request.getParameter("currentPageIndex"));
                } else {
                    page = 1;
                }
                try {
                    AdminDAO reportDAO = new AdminDAO();

                    List<Object> data = reportDAO.getAdvList(page);
                    if (data.size() >= 3) {
                        List<AdminAdvBean> report = (List<AdminAdvBean>) data.get(0);

                        request.setAttribute("report", report);
                        request.setAttribute("size", report.size());
                        request.setAttribute("totalrecord", data.get(1));
                        request.setAttribute("totalpage", data.get(2));
                        request.setAttribute("currentPageIndex", page);

                        logger.info("SetAttribute size=            " + report.size());
                        logger.info("SetAttribute totalrecord=     " + data.get(1));
                        logger.info("SetAttribute totalpage=       " + data.get(2));
                        logger.info("SetAttribute currentPageIndex=" + page);
                    }
                } catch (Exception ex) {
                    logger.info(ex.getMessage(),ex);
                    request.setAttribute("message", "");
                    return mapping.findForward(ERROR);
                }
                return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
