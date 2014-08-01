package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.ReportUserBean;
import com.tv.xeeng.reporttool.dao.ReportUserDAO;
import com.tv.xeeng.reporttool.forms.AddReportUserForm;

public class AddReportUserAction extends Action {
	private static Logger logger = Logger.getLogger(AddReportUserAction.class);
	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()) {
            response.sendRedirect("404.html");
            return null;
        }

		AddReportUserForm _rpuForm = (AddReportUserForm) form;
		logger.info("name == "+_rpuForm.getName());
		boolean isValid = true;
		if(_rpuForm.getName() == null || _rpuForm.getName().equals("")) {
			isValid = false;
			logger.info("Empty username.");
		} else if(_rpuForm.getPassword() == null || _rpuForm.getPassword().equals("")) {
			isValid = false; 
			logger.info("Empty password.");
		} else if(!_rpuForm.getPassword().equals(_rpuForm.getPasswordRetype())) {
			isValid = false;
			logger.info("Password confirmation is invalid.");
		}
		
		ReportUserDAO rpuDAO = new ReportUserDAO();
		int result  =  rpuDAO.checkUsername(_rpuForm.getName());
		if(result == 0 && isValid == true) {
			logger.info("check's Okey and isValid = true");
			ReportUserBean rpuBean = new ReportUserBean();
			rpuBean.setName(_rpuForm.getName());
			rpuBean.setPassword(_rpuForm.getPassword());
			rpuBean.setPartnerId(_rpuForm.getPartnerId());
			rpuBean.setUserTypeId(_rpuForm.getUserTypeId());
            rpuBean.setRoleId(BlahBlahUtil.parseInt(request.getParameter("roleId"), 1));
			rpuDAO.insertReportUser(rpuBean);
			request.setAttribute("msg_notifi", "Bạn đã tạo tài khoản thành công");
			return mapping.findForward(SUCCESS);
		} else {
			logger.info("check's Fail and isValid = Fail");
			request.setAttribute("msg_notifi", "Đã có lỗi xảy ra <br/> Bạn hãy kiểm tra lại thông tin đầu vào");
			return mapping.findForward(FAIL);
		}
	}
	
	
}
