package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.ReportUserDAO;
import com.tv.xeeng.reporttool.forms.ReportUserForm;

public class ListReportUserAction extends Action {


   /* private static Logger logger = Logger.getLogger(LoginAction.class);*/
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()) {
            response.sendRedirect("404.html");
            return null;
        }

			ReportUserForm _rpuForm = (ReportUserForm) form;
			if(request.getParameter("page") != null) {
				int pageCurrent = Integer.parseInt(request.getParameter("page"));
					if(pageCurrent > 0){
						_rpuForm.setRpUserList(new ReportUserDAO().getDataByNumRow(pageCurrent));
						if(_rpuForm.getRpUserList().size() > 0){
								request.setAttribute("page", pageCurrent);
								//int totalPage = _rpuForm.getRpUserList().get(0).getTotalPage();
								//request.setAttribute("totalPage", totalPage );
								return mapping.findForward(SUCCESS);
						} 
						else {
							request.setAttribute("page", pageCurrent);
							return mapping.findForward(FAIL);
						}
					}
					else {
						_rpuForm.setRpUserList(new ReportUserDAO().getDataByNumRow(1));
						request.setAttribute("page", "1");
						return mapping.findForward(SUCCESS);
					}
			}
			else {
				_rpuForm.setRpUserList(new ReportUserDAO().getDataByNumRow(1));
				request.setAttribute("page", "1");
				request.setAttribute("data", "nothave");
				return mapping.findForward(SUCCESS);
			}
				
				
	}

	
}
