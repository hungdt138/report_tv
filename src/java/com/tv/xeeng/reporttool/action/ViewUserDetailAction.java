package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.LogMoneyHistory;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.UserMoneyChange;
import com.tv.xeeng.reporttool.beans.WorkingUserBean;
import com.tv.xeeng.reporttool.dao.LogDAO;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;
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

public class ViewUserDetailAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        long userId = 0;
        try {
            String uid = request.getParameter("userId");
            userId = Long.parseLong(uid);
        } catch (Exception e) {
            userId = -1;
        }

        WorkingUserBean user = WorkingUserDAO.getAWorkingUserById(userId);

        if (user != null) {
            // nhật ký nạp tiền {
            int pageC = BlahBlahUtil.getRightPage(request.getParameter("page"));
            String reason = BlahBlahUtil.getRightString(request.getParameter("reason"));

            List<LogMoneyHistory> logs = LogDAO.getChargingXeengByPage(pageC, user.getLoginName(), user.getName(), reason, Constant.PAGE_SIZE_MINI);
            int numOfRecord = LogDAO.getChargingXeengByPageCount(user.getLoginName(), user.getName(), reason);
            int totalPage = (int) Math.ceil(numOfRecord * 1.0 / Constant.PAGE_SIZE_MINI);

            request.setAttribute("page", pageC);
            request.setAttribute("logs", logs);
            request.setAttribute("totalRecord", numOfRecord);
            request.setAttribute("totalPage", totalPage);
            // } nhật ký nạp tiền

            // nhật ký thay đổi Gold {
            int pageGold = BlahBlahUtil.getRightPage(request.getParameter("pageGold"));

            List<UserMoneyChange> logsGold = LogDAO.getMoneyChangesByPage(userId, pageGold, Constant.PAGE_SIZE);
            int numOfRecordGold = LogDAO.getMoneyChangesCount(userId);
            int totalPageGold = (int) Math.ceil(numOfRecordGold * 1.0 / Constant.PAGE_SIZE);

//            if (logsGold.size() > 0) {
//            if (logsGold != null) {
//                for (int i = 0; i < 20; i++) {
//                    if (!logsGold.isEmpty()) {
//                        logsGold.remove(0);
//                    }
//                }
//            }
//            }
            request.setAttribute("pageGold", pageGold);
            request.setAttribute("logsGold", logsGold);
            request.setAttribute("totalRecordGold", numOfRecordGold);
            request.setAttribute("totalPageGold", totalPageGold);
            // } nhật ký thay đổi Gold
        }

        request.setAttribute("user", user);

        return mapping.findForward("success");
    }

}
