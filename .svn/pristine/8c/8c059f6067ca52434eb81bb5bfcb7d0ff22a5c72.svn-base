package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.LogMoneyHistory;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.LogDAO;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import com.tv.xeeng.reporttool.util.Constant;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewModifyMoneyLogAction extends Action {

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
        int partnerId = ((UserBean) session.getAttribute("loggedInUser")).getPartnerId();

        int pageC = BlahBlahUtil.getRightPage(request.getParameter("page"));
        String loginName = BlahBlahUtil.getRightString(request.getParameter("loginName"));
        String reason = BlahBlahUtil.getRightString(request.getParameter("reason"));
        String moneyType = BlahBlahUtil.getRightString(request.getParameter("moneyType"));

        reason = BlahBlahUtil.getConditionString("reason", "=", "0"); // by Tool

        List<LogMoneyHistory> logs = LogDAO.getAllModifyMoneyLog(pageC, loginName, reason, moneyType);
        int numOfRecord = LogDAO.getNumOfModifyMoneyLog(loginName, reason, moneyType);
        int totalPage = (int) Math.ceil(numOfRecord * 1.0 / Constant.PAGE_SIZE);

        request.setAttribute("page", pageC);
        request.setAttribute("logs", logs);
        request.setAttribute("totalRecord", numOfRecord);
        request.setAttribute("totalPage", totalPage);

        return mapping.findForward("success");
    }

}
