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

import com.tv.xeeng.reporttool.beans.AdminAdvBean;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.AdminDAO;

/**
 *
 * @author PHUCTV
 */
public class AdvDetailAction extends org.apache.struts.action.Action {

    private static Logger logger = Logger.getLogger(AdvDetailAction.class);
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
//        logger.info("GetAdvertisingDetail");
        HttpSession session = request.getSession();
        if (session != null) {
                session.setAttribute("funcMenu", "advertisinglist");
                int advId = 0;
                if (request.getParameter("advertisingId") != null) {
                    advId = Integer.parseInt(request.getParameter("advertisingId"));
                }
                try {
                    AdminDAO reportDAO = new AdminDAO();

                    AdminAdvBean data = reportDAO.getAdvDetail(advId);
                    if (data == null) {
                        request.setAttribute("message", "Error in processing");
                    }
                    request.setAttribute("data", data);
                    request.setAttribute("advertisingId", advId);

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
