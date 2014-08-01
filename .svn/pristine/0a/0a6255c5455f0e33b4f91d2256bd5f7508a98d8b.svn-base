package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.ReportUserBean;
import com.tv.xeeng.reporttool.dao.ReportUserDAO;

public class GetReportUserByIdAction extends Action {
    private static Logger logger = Logger.getLogger(GetReportUserByIdAction.class);
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()) {
            response.sendRedirect("404.html");
            return null;
        }

        if (request.getParameter("userId") != null) {
            logger.info("DA vao action");
            int userId = Integer.parseInt(request.getParameter("userId"));
            logger.info("userId = " + userId);
            ReportUserBean rpuBean = new ReportUserDAO().getReportUserById(userId);
            logger.info("partnerId: " + rpuBean.getPartnerId() + "userTypeId: " + rpuBean.getUserTypeId());
            request.setAttribute("rpuBean", rpuBean);
            request.setAttribute("msg_notifi", "Đang chuyển hướng");
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAIL);
        }

    }


}
