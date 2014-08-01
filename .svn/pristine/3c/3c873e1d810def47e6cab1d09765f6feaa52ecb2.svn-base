package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.GiffCodeDAO;

public class DeleteGiftCodeAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			int giftcodeId = Integer.parseInt(request.getParameter("giftcodeId"));
			if(giftcodeId > 0) {
				int rs = new GiffCodeDAO().deleteGiffCode(giftcodeId);
				if( rs > 0) {
					request.setAttribute("msg_notifi", "Đã xóa thẻ thành công!");
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Có lỗi!Bạn hãy xem lại!");
					return mapping.findForward("fail");
				}
			} else {
				request.setAttribute("msg_notifi", "Có lỗi!Bạn đừng nghịch :))!");
				return mapping.findForward("fail");
			}
		
		
	}
	

}
