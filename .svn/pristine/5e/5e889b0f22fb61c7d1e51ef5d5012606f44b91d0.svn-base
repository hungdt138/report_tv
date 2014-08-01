package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.AdvertisDAO;
import com.tv.xeeng.reporttool.forms.AdvertisingListForm;

public class AdvertisingListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		AdvertisingListForm _advForm = (AdvertisingListForm) form;
		int pageC = Integer.parseInt(request.getParameter("page"));
		if(pageC >= 1 ) {
			_advForm.setAdvList(new AdvertisDAO().getAll(pageC));
			if(_advForm.getAdvList().size() > 0) {
				request.setAttribute("page", pageC);
				return mapping.findForward("success");
			} else {
				request.setAttribute("msg_notifi", "Danh sách rỗng!");
				return mapping.findForward("fail");
			}
		} else {
			_advForm.setAdvList(new AdvertisDAO().getAll(1));
			if(_advForm.getAdvList().size() > 0) {
				request.setAttribute("page", pageC);
				return mapping.findForward("success");
			} else {
				request.setAttribute("msg_notifi", "Danh sách rỗng!");
				return mapping.findForward("fail");
			}
		}
		
	}
	
}
