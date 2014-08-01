package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.AdvertisDAO;

public class DeleteAdvertisingAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int advId = Integer.parseInt(request.getParameter("advertisingId"));
		if(advId > 0) {
			int rs = new AdvertisDAO().deleteAdvertising(advId);
			if( rs > 0) {
				request.setAttribute("msg_notifi", "Đã xóa thành công tin quảng cáo<br/>Có mã là :" + advId );
				return mapping.findForward("success");
			} else {

				request.setAttribute("msg_notifi", "Đã có lỗi xảy ra<br/>bạn phải chắc chắn có tồn tại mã là :" + advId );
				return mapping.findForward("fail");
			}
		} else {
			request.setAttribute("msg_notifi", "Đã có lỗi xảy ra<br/>Bạn không được nghịch lung tung");
			return mapping.findForward("fail");
		}
	}
	

}
