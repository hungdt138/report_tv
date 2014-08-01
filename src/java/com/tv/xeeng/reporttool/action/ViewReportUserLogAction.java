package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.ExchangeLog;
import com.tv.xeeng.reporttool.beans.ReportUserLog;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.LogDAO;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import com.tv.xeeng.reporttool.util.Constant;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewReportUserLogAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()) {
            response.sendRedirect("404.html");
            return null;
        }

        HttpSession session = request.getSession();
        int partnerId = ((UserBean) session.getAttribute("loggedInUser")).getPartnerId();

        int pageC = BlahBlahUtil.getRightPage(request.getParameter("page"));
        String username = BlahBlahUtil.getRightString(request.getParameter("username"));

        List<ReportUserLog> logs = LogDAO.getReportUserLogByPage(pageC, Constant.PAGE_SIZE, username);
        int numOfRecord =LogDAO.getNumOfReportUserLog(username);
        int totalPage = (int) Math.ceil(numOfRecord * 1.0 / Constant.PAGE_SIZE);

        if (totalPage == 0) {
            pageC = 0;
        }

        request.setAttribute("page", pageC);
        request.setAttribute("logs", logs);
        request.setAttribute("totalRecord", numOfRecord);
        request.setAttribute("totalPage", totalPage);

        return mapping.findForward("success");
    }

}
