package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.WorkingUserBean;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;
import com.tv.xeeng.reporttool.forms.ChangePassWorkingUserForm;

public class GetAWorkingUserAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser.getIsIA()) {
            response.sendRedirect("404.html");
            return null;
        }

		HttpSession session = request.getSession();
		int partnerId = ((UserBean) session.getAttribute("loggedInUser")).getPartnerId();
		if(request.getParameter("userId") != null) { 
			int userId = Integer.parseInt(request.getParameter("userId"));
			if(userId > 0) { 
				ChangePassWorkingUserForm _cpwuForm = (ChangePassWorkingUserForm) form;
				WorkingUserBean wuBean = new WorkingUserDAO().getAWorkingUser(userId, partnerId);
				_cpwuForm.setUserId(wuBean.getUserId());
				_cpwuForm.setOldPass(wuBean.getPassword());
				return mapping.findForward("success");
			} else {
				request.setAttribute("msg_notifi", "Không tồn tại tài khoản cần tìm! <br/> Bạn hãy kiểm tra lại");
				return mapping.findForward("fail");
			} 
			
		} else {
			request.setAttribute("msg_notifi", "Không tìm thấy tham số ! <br/> Bạn hãy kiểm tra lại");
			return mapping.findForward("fail");
		} 
		
	}
	
}
