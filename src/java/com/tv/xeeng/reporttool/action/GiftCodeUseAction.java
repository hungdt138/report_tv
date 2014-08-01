package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.GiffCodeDAO;
import com.tv.xeeng.reporttool.forms.GiftCodeUseForm;

public class GiftCodeUseAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			GiftCodeUseForm _gcuForm = (GiftCodeUseForm) form;
			boolean isValidate = true;
			if(_gcuForm.getUsername().equals("")) {
				isValidate = false;
			} else if (_gcuForm.getSerial() <= 0) {
				isValidate = false;
			}
			
			if( isValidate == true) {
				int rs  =  new GiffCodeDAO().useGiftCode(_gcuForm.getUsername(), _gcuForm.getSerial());
				if(rs == -2) {
					request.setAttribute("msg_notifi", "Mã thẻ cào không hợp lệ <br/> Hoặc đã được sử dụng <br/> Bạn hãy kiểm tra lại!");
					return mapping.findForward("fail");
				} else if(rs == -1) {
					request.setAttribute("msg_notifi", "Bạn nhập sai tài khoản!<br/> Hãy nhập lại");
					return mapping.findForward("fail");
				} else if(rs == -99 || rs == 99) {
					request.setAttribute("msg_notifi", "Tài khoản không tồn tại<br/> hoặc <br/> Mã thẻ cào không hợp lệ!");
					return mapping.findForward("fail");
				} else {
					request.setAttribute("msg_notifi", "Đã nạp thẻ thành công<br/> Bạn hãy kiểm tra lại");
					return mapping.findForward("success");
				}
			} 
			else {
				request.setAttribute("msg_notifi", "Tài khoản không tồn tại<br/> hoặc <br/> Mã thẻ cào không hợp lệ!");
				return mapping.findForward("fail");
			}
			
	}
	
		
}
