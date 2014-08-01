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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.UserRegisterReportBean;
import com.tv.xeeng.reporttool.dao.ReportDAO;
import com.tv.xeeng.reporttool.forms.RegisterReportForm;
import com.tv.xeeng.reporttool.util.DateHelper;

/**
 *
 * @author PHUCTV
 */
public class RegisterReportAction extends org.apache.struts.action.Action {

   /* private static Logger logger = Logger.getLogger(RegisterReportAction.class);*/
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()
                && !loggedInUser.getIsOperator()) {
            response.sendRedirect("404.html");
            return null;
        }

        HttpSession session = request.getSession();
        if (session != null) {
            if (session.getAttribute("loggedInUser") != null&&(((UserBean) session.getAttribute("loggedInUser")).getUserType()==1)||((UserBean) session.getAttribute("loggedInUser")).getUserType()==2) {
                session.setAttribute("funcMenu", "registerReport");
                String action = request.getParameter("action");
                if (action.equals("prepareReport")) {
                    return prepareReport(mapping, form, request, response);
                } else if (action.equals("report")) {
                    return doReport(mapping, form, request, response);
                }
            }
        }
        return mapping.findForward(SUCCESS);

    }

    private ActionForward doReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      /*  logger.info("doReport");*/
        RegisterReportForm inputForm = (RegisterReportForm) form;
        int page = 0;
        request.setAttribute("fromDate", inputForm.getFromDate());
        request.setAttribute("toDate", inputForm.getToDate());
        if (request.getParameter("currentPageIndex") != null) {
            page = Integer.parseInt(request.getParameter("currentPageIndex"));
        } else {
            page = 1;
        }
        String startDate = DateHelper.convertDate(inputForm.getFromDate());
        String toDate = DateHelper.convertDate(inputForm.getToDate());
        inputForm.setFromDate(startDate);
        inputForm.setToDate(toDate);
        try {
            HttpSession session = request.getSession();
            UserBean user = (UserBean) session.getAttribute("loggedInUser");
            ReportDAO reportDAO = new ReportDAO();
           /* logger.info("Parameter partnerId=" + user.getPartnerId());
            logger.info("Parameter fromDate =" + inputForm.getFromDate());
            logger.info("Parameter toDate   =" + inputForm.getToDate());
            logger.info("Parameter page     =" + page);
            logger.info("Parameter partnerId=" + user.getPartnerId());*/
            List<Object> data = reportDAO.reportRegister(user.getPartnerId(), inputForm.getFromDate(), inputForm.getToDate(), page);
            if (data.size() >= 3) {
                List<UserRegisterReportBean> report = (List<UserRegisterReportBean>) data.get(0);
                request.setAttribute("report", report);
                request.setAttribute("size", report.size());
                request.setAttribute("totalrecord", data.get(1));
                request.setAttribute("totalpage", data.get(2));
                request.setAttribute("currentPageIndex", page);
             /*   logger.info("Attribute size            =" + report.size());
                logger.info("Attribute totalrecord     =" + data.get(1));
                logger.info("Attribute totalpage       =" + data.get(2));
                logger.info("Attribute currentPageIndex=" + page);*/
            }
        } catch (Exception ex) {
           /* logger.error(ex.getMessage(),ex);*/
            request.setAttribute("message", "Error in processing");
           /* logger.info("RETURN ERROR");*/
            return mapping.findForward(ERROR);
        }
      /*  logger.info("RETURN SUCCESS");*/
        return mapping.findForward(SUCCESS);
    }

    private ActionForward prepareReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       /* logger.info("prepareReport");*/
        String displayDate = DateHelper.getDisplayDate();
        request.setAttribute("fromDate", displayDate);
        request.setAttribute("toDate", displayDate);
        return mapping.findForward(SUCCESS);
    }
}
