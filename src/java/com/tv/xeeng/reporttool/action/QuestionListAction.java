package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.QuestionDAO;
import com.tv.xeeng.reporttool.forms.QuestionListForm;

public class QuestionListAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()
                && !loggedInUser.getIsOperator()
                && !loggedInUser.getIsGameMaster()) {
            response.sendRedirect("404.html");
            return null;
        }

		int pageC = Integer.parseInt(request.getParameter("page"));

        String keyword = request.getParameter("keyword");

		if(pageC > 0) {
			QuestionListForm _qlForm = (QuestionListForm)form;
			_qlForm.setQbList(new QuestionDAO().getDataByNumRow(pageC, keyword));
			if( _qlForm.getQbList().size() > 0) {
				request.setAttribute("page", pageC);
				return mapping.findForward("success");
			} else {
				request.setAttribute("msg_notifi", "Không tồn tại bản ghi nào");
				return mapping.findForward("fail");
			}
		} else {
			QuestionListForm _qlForm = (QuestionListForm)form;
			_qlForm.setQbList(new QuestionDAO().getDataByNumRow(1, keyword));
			if( _qlForm.getQbList().size() > 0) {
				request.setAttribute("page", pageC);
				return mapping.findForward("success");
			} else {
				request.setAttribute("msg_notifi", "Không tồn tại bản ghi nào");
				return mapping.findForward("fail");
			}
		}
		
	}
	
}
