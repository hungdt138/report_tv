package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.UserDAO;

public class DeleteSuperUserAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			if(request.getParameter("userId") != null) {
				int userId = Integer.parseInt(request.getParameter("userId"));
				int rs = new UserDAO().deleteUser(userId);
				if(rs > 0) {
					request.setAttribute("msg_notifi", "Đã xóa thành công!");
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Không tồn tại tài khoản cần xóa!<br/> Bạn hãy kiểm tra lại!");
					return mapping.findForward("fail");
				}
				
			} else {
				request.setAttribute("msg_notifi", "Không tồn tại tài khoản cần xóa!<br/> Bạn hãy kiểm tra lại!");
				return mapping.findForward("fail");
			}
	}
	

}
