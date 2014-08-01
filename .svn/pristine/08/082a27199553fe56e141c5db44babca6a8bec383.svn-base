package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.GoldenhourDAO;

public class DeleteGoldenHourAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			int gdhId = Integer.parseInt(request.getParameter("goldenhourId"));
			if(gdhId > 0) { 
				int rs = new GoldenhourDAO().deleteGoldenHour(gdhId);
				if(rs > 0) {
					request.setAttribute("msg_notifi", "Đã xóa thành công");
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Không thể tìm kiếm giờ vàng <br/> Bạn hãy kiểm tra lại");
					return mapping.findForward("fail");
				}
			} else {
				request.setAttribute("msg_notifi", "Không tồn tại giờ vàng cần xóa");
				return mapping.findForward("fail");
			}
	}
	
}
