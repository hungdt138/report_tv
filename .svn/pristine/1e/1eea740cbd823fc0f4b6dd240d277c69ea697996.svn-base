package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.WorkingUserBean;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;
import com.tv.xeeng.reporttool.util.AuthUtil;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserDetailAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();

        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null || !loggedInUser.isCanChangeUserInfo()) {

        }

        if (!request.getMethod().equals("POST")) {
            long userId = 0;
            try {
                String uid = request.getParameter("userId");
                userId = Long.parseLong(uid);
            } catch (Exception e) {
                userId = 0;
            }

            WorkingUserBean user = WorkingUserDAO.getAWorkingUserById(userId);

            request.setAttribute("user", user);

            return mapping.findForward("success");
        } else {
            long userId = 0;
            try {
                String uid = request.getParameter("userId");
                userId = Long.parseLong(uid);
            } catch (NumberFormatException e) {
                userId = 0;
            }

            String displayName = request.getParameter("name");
            String email = request.getParameter("email");
            int sex = BlahBlahUtil.parseInt(request.getParameter("sex"), 1);
            String cmnd = request.getParameter("cmnd");
            String phone = request.getParameter("phone");

            if (BlahBlahUtil.hasEmptyString(displayName, email, cmnd, phone)) {
                request.setAttribute("message", "Vui lòng điền đầy đủ thông tin.");

                return mapping.findForward("error");
            }

            boolean success = WorkingUserDAO.updateWorkingUser(userId, displayName, email, sex, cmnd, phone, loggedInUser);

            if (success) {
                WorkingUserBean user = WorkingUserDAO.getAWorkingUserById(userId);

                request.setAttribute("user", user);
                request.setAttribute("message", "Cập nhật thành công.");

                return mapping.findForward("success");
            } else {
                request.setAttribute("message", "Không cập nhật được thông tin, vui lòng báo cho đội ngũ kỹ thuật.");

                return mapping.findForward("error");
            }
        }
    }

}
