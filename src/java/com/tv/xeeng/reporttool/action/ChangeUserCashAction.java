package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;
import com.tv.xeeng.reporttool.forms.ChangeUserCashForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChangeUserCashAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ChangeUserCashForm dataFrom = (ChangeUserCashForm) form;

        String action = request.getParameter("action");
        String type = request.getParameter("type");
        String message = request.getParameter("message");
        UserBean user = (UserBean) request.getSession(true).getAttribute("loggedInUser");

        if (action == null || "".equals(action)) {
            String userId = request.getParameter("userId");
            if (userId != null) {
                request.setAttribute("userId", userId);
                return mapping.findForward("input");
            } else {
                request.setAttribute("msg_notifi", "UserID không hợp lệ!");
                return mapping.findForward("fail");
            }
        } else {
            if (dataFrom.getUserId() > 0 && dataFrom.getCash() != 0) {
                boolean success = false;
                if (type != null && type.equals("xeeng")) {
                    success = new WorkingUserDAO().changeUserXeeng(dataFrom.getUserId(), dataFrom.getCash(), message, user);
                } else {
                    success = new WorkingUserDAO().changeUserCash(dataFrom.getUserId(), dataFrom.getCash(), message, user);
                }

                if (success) {
                    request.setAttribute("msg_notifi", "Thay đổi thành công!");
                    return mapping.findForward("success");
                } else {
                    request.setAttribute("msg_notifi", "Thay đổi không thành công!<br/> Bạn hãy kiểm tra lại!");
                    return mapping.findForward("fail");
                }

            } else {
                request.setAttribute("msg_notifi", "Dữ liệu không đúng!<br/> Bạn hãy kiểm tra lại!");
                return mapping.findForward("fail");
            }
        }
    }

}
