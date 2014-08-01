package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.GoldenhourDAO;
import com.tv.xeeng.reporttool.forms.GoldenHourListForm;

public class GoldenHourListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			GoldenHourListForm _gdhLForm = (GoldenHourListForm) form;
			int pageC = Integer.parseInt(request.getParameter("page"));
			if(pageC > 0) {
				_gdhLForm.setGdhList(new GoldenhourDAO().getAll(pageC));
				if(_gdhLForm.getGdhList().size() > 0) {
					request.setAttribute("page", pageC);
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Không tồn tại bản ghi nào");
					return mapping.findForward("fail");
				}
			} else {
				_gdhLForm.setGdhList(new GoldenhourDAO().getAll(1));
				if(_gdhLForm.getGdhList().size() > 0) {
					request.setAttribute("page", pageC);
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Không tồn tại bản ghi nào");
					return mapping.findForward("fail");
				}
			}
			
		
	}
	
}
