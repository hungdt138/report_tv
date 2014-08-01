package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.WorkingUserBean;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnlockUserAvatarAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");

        if (request.getParameter("userId") != null) {
            int userId = Integer.parseInt(request.getParameter("userId"));

            boolean success = new WorkingUserDAO().unlockUserAvatar(userId, loggedInUser);
//            if (success) {
//                WorkingUserBean user = WorkingUserDAO.getAWorkingUserById(userId);
//
//                request.setAttribute("user", user);
//                request.setAttribute("message", "Mở khóa thành công.");
//                return mapping.findForward("success");
//            } else {
//                request.setAttribute("msg_notifi", "Mở khóa không thành công hoặc người chơi không có avatar.");
//                return mapping.findForward("fail");
//            }
            response.sendRedirect("view_user_detail.html?userId=" + String.valueOf(userId));
        }
        request.setAttribute("msg_notifi", "Thông tin không hợp lệ, vui lòng kiểm tra lại.");
        return mapping.findForward("fail");
    }

}
