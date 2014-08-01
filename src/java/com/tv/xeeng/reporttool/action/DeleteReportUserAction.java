package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.ReportUserDAO;

public class DeleteReportUserAction extends Action {
	private final String SUCCESS = "success";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()) {
            response.sendRedirect("404.html");
            return null;
        }

		int userId = Integer.parseInt( request.getParameter("userId").toString());
		ReportUserDAO rpuDAO = new ReportUserDAO();
		rpuDAO.deleteReportUserById(userId);
		request.setAttribute("msg_notifi", "Đã xóa thành công ! ");
		return mapping.findForward(SUCCESS);
	}
	
	
}
