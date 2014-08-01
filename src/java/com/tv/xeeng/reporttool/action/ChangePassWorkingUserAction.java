package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.crypto.MD5;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;
import com.tv.xeeng.reporttool.forms.ChangePassWorkingUserForm;

public class ChangePassWorkingUserAction extends Action {
	 private static Logger logger = Logger.getLogger(AdvListAction.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChangePassWorkingUserForm _cpwuForm = (ChangePassWorkingUserForm) form;
		boolean isValidate = true;
		if(_cpwuForm.getUserId() <= 0) {
			logger.info("Khong tim thay id");
			isValidate = false;
		}/* else if(_cpwuForm.getOldPass().equals("")) {
			logger.info("Khong tim thay OldPass");
			isValidate = false;
		}*/ else if(_cpwuForm.getNewPass().equals("")) {
			logger.info("Khong tim thay NewPass");
			isValidate = false;
		}/* else if(!_cpwuForm.getOldPass().equals(MD5.md5(_cpwuForm.getReOldPass()).toUpperCase()) ) {
			logger.info("OldPass vs NewPass ko hop le\n" + _cpwuForm.getOldPass() + "::" + _cpwuForm.getReOldPass() +  " = " + MD5.md5(_cpwuForm.getReOldPass()) );
			isValidate = false;
		}*/
		if(isValidate) {
			if(_cpwuForm.getNewPass().equals(_cpwuForm.getReNewPass())) {
				String newPass = MD5.md5(_cpwuForm.getNewPass());
				int rs = new WorkingUserDAO().changePass(_cpwuForm.getUserId(), newPass);
				if(rs > 0) {
					request.setAttribute("msg_notifi", "Bạn đã thay đổi mật khẩu thành công");
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Tài khoản này không tồn tại hoặc<br/> Đã phát sinh lỗi bất kì ngoài dự đoán");
					return mapping.findForward("fail");
				}
				
				
			} else {
				request.setAttribute("msg_notifi", "Bạn nhập mật khẩu mới không khớp<br/> Vui lòng nhập lại chính xác");
				return mapping.findForward("fail");
			}
			
			
			
		} else {
			request.setAttribute("msg_notifi", "Bạn nhập mật khẩu cũ không đúng<br/>Hoặc mật khẩu mới lỗi<br/> Vui lòng kiểm tra lại và nhập chính xác");
			return mapping.findForward("fail");
		}
		
	}
	
}
