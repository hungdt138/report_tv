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
import com.tv.xeeng.reporttool.forms.PartnerMonthlyReportForm;
import com.tv.xeeng.reporttool.util.DateHelper;

public class PartnerMonthlyReportAction extends org.apache.struts.action.Action {

    private static Logger logger = Logger.getLogger(PartnerMonthlyReportAction.class);
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        if (session != null) {
                session.setAttribute("funcMenu", "partnermonthly");
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
        PartnerMonthlyReportForm mForm = (PartnerMonthlyReportForm) form;
        
        try {

            String datetime;
            int page = 0;
            if (request.getParameter("dt") != null && !request.getParameter("dt").equals("")) {
                request.setAttribute("dt", request.getParameter("dt"));
                datetime = DateHelper.convertDate(request.getParameter("dt"));
            } else {
                request.setAttribute("dt", DateHelper.getDisplayDate());
                datetime = DateHelper.convertDate(DateHelper.getDisplayDate());
            }
            if (request.getParameter("currentPageIndex") != null) {
                page = Integer.parseInt(request.getParameter("currentPageIndex"));
            } else {
                page = 1;
            }
//            logger.info("Parameter datetime="+datetime);
//            logger.info("Parameter page    ="+page);
//            logger.info("Parameter isSMS   ="+mForm.isSMS());
//            logger.info("Parameter isCard  ="+mForm.isCard());
//            ReportDAO reportDAO = new ReportDAO();
//            List<Object> data = reportDAO.reportByDayDetail(mForm.getPartnerId(), datetime, mForm.isSMS(), mForm.isCard(), page);
//            if (data.size() >= 3) {
//                List<UserChargingReportBean> report = (List<UserChargingReportBean>) data.get(0);
//
//                request.setAttribute("report", report);
//                request.setAttribute("size", report.size());
//                request.setAttribute("totalrecord", data.get(1));
//                request.setAttribute("totalpage", data.get(2));
//                request.setAttribute("currentPageIndex", page);
//                
//                logger.info("SetAttribute size=            "+report.size());
//                logger.info("SetAttribute totalrecord=     "+data.get(1));
//                logger.info("SetAttribute totalpage=       "+data.get(2));
//                logger.info("SetAttribute currentPageIndex="+page);
//            }
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
        PartnerMonthlyReportForm mForm = (PartnerMonthlyReportForm) form;
//        mForm.setSMS(true);
//        mForm.setCard(true);
        return mapping.findForward(SUCCESS);
    }
}
