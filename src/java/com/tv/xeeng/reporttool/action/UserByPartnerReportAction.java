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

import com.tv.xeeng.reporttool.beans.UserBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.UserByPartnerReportBean;
import com.tv.xeeng.reporttool.dao.ReportDAO;
import com.tv.xeeng.reporttool.forms.UserByPartnerReportForm;
import com.tv.xeeng.reporttool.util.DateHelper;

/**
 *
 * @author PHUCTV
 */
public class UserByPartnerReportAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsIA() && !loggedInUser.getIsAdmin() && !loggedInUser.getIsOperator()) {
            response.sendRedirect("404.html");
            return null;
        }

        HttpSession session = request.getSession();
        if (session != null) {
//            if (session.getAttribute("loggedInUser") != null &&(((UserBean) session.getAttribute("loggedInUser")).getUserType()==1)||((UserBean) session.getAttribute("loggedInUser")).getUserType()==2) {
                session.setAttribute("funcMenu", "bypartnerreport");
                String action = request.getParameter("action");
                if (action.equals("prepareReport")) {
                    return prepareReport(mapping, form, request, response);
                } else if (action.equals("report")) {
                    return doReport(mapping, form, request, response);
//                }
            }
        }
        return mapping.findForward(SUCCESS);
    }

    private ActionForward doReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserByPartnerReportForm inputForm = (UserByPartnerReportForm) form;

        request.setAttribute("fromDate", inputForm.getFromDate());
        request.setAttribute("toDate", inputForm.getToDate());

        String startDate = DateHelper.convertDate(inputForm.getFromDate());
        String toDate = DateHelper.convertDate(inputForm.getToDate());
        
        inputForm.setFromDate(startDate);
        inputForm.setToDate(toDate);
        try {
            ReportDAO reportDAO = new ReportDAO();
            List<UserByPartnerReportBean> report = reportDAO.reportByPartner(inputForm.getFromDate(), inputForm.getToDate());
            request.setAttribute("report", report);
            request.setAttribute("size", report.size());
            request.setAttribute(toDate, form);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", "Error in processing");
            return mapping.findForward(ERROR);
        }
        return mapping.findForward(SUCCESS);
    }

    private ActionForward prepareReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String displayDate = DateHelper.getDisplayDate();
        request.setAttribute("fromDate", displayDate);
        request.setAttribute("toDate", displayDate);
        return mapping.findForward(SUCCESS);
    }
}
