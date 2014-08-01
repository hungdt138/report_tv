package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.GiffCodeDAO;
import com.tv.xeeng.reporttool.forms.GiftCodeListForm;

public class GiftCodeListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()
                && !loggedInUser.getIsOperator()) {
            response.sendRedirect("404.html");
            return null;
        }

			GiftCodeListForm _gclForm = (GiftCodeListForm) form;
			int pageC = Integer.parseInt(request.getParameter("page"));
			if(pageC > 0) {
				_gclForm.setGcList(new GiffCodeDAO().getAll(pageC));
				if(_gclForm.getGcList().size() > 0) {
					request.setAttribute("page", pageC);
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Không tồn tại bản ghi nào <br/> Bạn hãy kiểm tra lại");
					return mapping.findForward("fail");
				}
			} else {
				_gclForm.setGcList(new GiffCodeDAO().getAll(1));
				if(_gclForm.getGcList().size() > 0) {
					request.setAttribute("page", 1);
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Không tồn tại bản ghi.");
					return mapping.findForward("fail");
				}
			}
			
	}

}
