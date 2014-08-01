package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.WorkingUserBean;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LockUserAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");

        if (request.getParameter("userId") != null && request.getParameter("numOfDay") != null) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int numOfDay = BlahBlahUtil.parseInt(request.getParameter("numOfDay"), 0);

            if (numOfDay > 0) {
                boolean success = new WorkingUserDAO().lockUser(userId, numOfDay, loggedInUser);
//                if (success) {
//                    WorkingUserBean user = WorkingUserDAO.getAWorkingUserById(userId);
//
//                    request.setAttribute("user", user);
//                    request.setAttribute("message", "Khóa thành công.");
//                    return mapping.findForward("success");
//                } else {
//                    request.setAttribute("msg_notifi", "Có lỗi xảy ra, vui lòng báo cho bộ phận kỹ thuật.");
//                    return mapping.findForward("fail");
//                }
                response.sendRedirect("view_user_detail.html?userId=" + String.valueOf(userId));
            }
        }
        request.setAttribute("msg_notifi", "Thông tin không hợp lệ, vui lòng kiểm tra lại.");
        return mapping.findForward("fail");
    }

}
