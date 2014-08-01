package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.LogMoneyHistory;
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

public class ViewMoneyChargingLogAction extends Action {

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
        String username = BlahBlahUtil.getRightString(request.getParameter("username"));
        String reason = BlahBlahUtil.getRightString(request.getParameter("reason"));

        List<LogMoneyHistory> logs = LogDAO.getChargingXeengByPage(pageC, loginName, username, reason, Constant.PAGE_SIZE);
        int numOfRecord = LogDAO.getChargingXeengByPageCount(loginName, null, reason);
        int totalPage = (int) Math.ceil(numOfRecord * 1.0 / Constant.PAGE_SIZE);

        request.setAttribute("page", pageC);
        request.setAttribute("logs", logs);
        request.setAttribute("totalRecord", numOfRecord);
        request.setAttribute("totalPage", totalPage);

        return mapping.findForward("success");
    }

}
