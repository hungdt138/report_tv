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
import com.tv.xeeng.reporttool.beans.UserChargingReportBean;
import com.tv.xeeng.reporttool.dao.ReportDAO;
import com.tv.xeeng.reporttool.forms.ChargingDetailReportForm;
import com.tv.xeeng.reporttool.util.DateHelper;

/**
 * @author PHUCTV
 */
public class ChargingDetailReportAction extends org.apache.struts.action.Action {

    private static Logger logger = Logger.getLogger(ChargingDetailReportAction.class);
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
            session.setAttribute("funcMenu", "chargingDetailReport");
            String action = request.getParameter("action");
            if (action.equals("prepareReport")) {
                return prepareReport(mapping, form, request, response);
            } else if (action.equals("report")) {
                return doReport(mapping, form, request, response);
            }
        }
        return mapping.findForward(SUCCESS);
    }

    private ActionForward doReport(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("doReport");
        ChargingDetailReportForm mForm = (ChargingDetailReportForm) form;
        try {

            int page = 0;
            String fromDate = request.getParameter("sfromDate");
            String toDate = request.getParameter("stoDate");

            if (fromDate == null || toDate == null) {
                fromDate = DateHelper.convertDate(DateHelper.getDisplayDate());
                toDate = fromDate;
            } else {
                fromDate = DateHelper.convertDate(fromDate);
                toDate = DateHelper.convertDate(toDate);
            }

            request.setAttribute("sfromDate", fromDate);
            request.setAttribute("stoDate", toDate);

            if (request.getParameter("currentPageIndex") != null) {
                page = Integer.parseInt(request.getParameter("currentPageIndex"));
            } else {
                page = 1;
            }

            logger.info("fromdate    = " + fromDate);
            logger.info("todate    = " + toDate);
            logger.info("Parameter page    =" + page);
            logger.info("Parameter isSMS   =" + mForm.isSMS());
            logger.info("Parameter isCard  =" + mForm.isCard());
            ReportDAO reportDAO = new ReportDAO();
            HttpSession session = request.getSession();
            UserBean user = (UserBean) session.getAttribute("loggedInUser");

            int partnerId = user.getPartnerId();

            if (partnerId <= 0) {

                String reqPartnerId = request.getParameter("partnerId");
                if (reqPartnerId != null) {
                    partnerId = Integer.parseInt(reqPartnerId);
                }
            }

//            List<Object> data = reportDAO.reportByDayDetail(partnerId, datetime, mForm.isSMS(), mForm.isCard(), page);

            List<Object> data = reportDAO.reportByPartnerDetail(partnerId, fromDate, toDate, mForm.isSMS(), mForm.isCard(), page);

            if (data.size() >= 3) {
                List<UserChargingReportBean> report = (List<UserChargingReportBean>) data.get(0);

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
            logger.error(ex.getMessage(), ex);
            request.setAttribute("message", "Error in processing");
            logger.info("RETURN ERROR");
            return mapping.findForward(ERROR);
        }
        logger.info("RETURN SUCCESS");
        return mapping.findForward(SUCCESS);
    }

    private ActionForward prepareReport(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("PrepareReport");
        ChargingDetailReportForm mForm = (ChargingDetailReportForm) form;
        mForm.setSMS(true);
        mForm.setCard(true);
        return mapping.findForward(SUCCESS);
    }
}
