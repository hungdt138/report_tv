package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.QuestionDAO;

public class DeleteQuestionAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()
                && !loggedInUser.getIsOperator()
                && !loggedInUser.getIsGameMaster()) {
            response.sendRedirect("404.html");
            return null;
        }

		int questionId = Integer.parseInt( request.getParameter("questionId"));
		if(questionId >= 1) {
			int rs  = new QuestionDAO().deleteAQuestion(questionId);
			if(rs > 0) {
				request.setAttribute("msg_notifi", "Đã xóa thành công !");
				return mapping.findForward("success");
			} else {
				request.setAttribute("msg_notifi", "Lỗi! Không thể xóa!");
				return mapping.findForward("fail");
			}
		} else {
			request.setAttribute("msg_notifi", "Lỗi! Không thể xóa!");
			return mapping.findForward("fail");
		}
	}
	
}
