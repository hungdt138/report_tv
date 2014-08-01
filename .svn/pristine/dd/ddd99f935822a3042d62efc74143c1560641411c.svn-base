package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.UserDAO;
import com.tv.xeeng.reporttool.forms.AddSuperUserForm;

public class AddSuperUserAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddSuperUserForm _asuForm = (AddSuperUserForm) form;
		if(!_asuForm.getName().equals("") ) {
			String info = new UserDAO().addASuperUser(_asuForm.getName());
			request.setAttribute("msg_notifi", info);
			return mapping.findForward("success");
		} else {
			request.setAttribute("msg_notifi", "Không tìm thấy tài khoản cần bổ nhiệm Super User");
			return mapping.findForward("fail");
		}
	}
	
}
