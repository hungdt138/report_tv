package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.QuestionBean;
import com.tv.xeeng.reporttool.dao.QuestionDAO;
import com.tv.xeeng.reporttool.forms.QuestionForm;

public class AddQuestionAction extends Action {

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

		QuestionForm _qForm = (QuestionForm) form;
		boolean isValidate = true;
		if(_qForm.getDetail() == null || _qForm.getDetail().equals("")) {
			isValidate = false;
		} else if(_qForm.getChoix1() == null || _qForm.getChoix1().equals("")) {
			isValidate = false;
		} else if(_qForm.getChoix2() == null || _qForm.getChoix2().equals("")) {
			isValidate = false;
		} else if(_qForm.getChoix3() == null || _qForm.getChoix3().equals("")) {
			isValidate = false;
		} else if(_qForm.getChoix4() == null || _qForm.getChoix4().equals("")) {
			isValidate = false;
		} else if(_qForm.getLevelId() < 0) {
			isValidate = false;
		} else if(_qForm.getAnswer() != 0 && _qForm.getAnswer() != 1 && _qForm.getAnswer() != 2 && _qForm.getAnswer() != 3 && _qForm.getAnswer() != 4) {
			isValidate = false;
		} else if(_qForm.getDomainId() < 0) {
			isValidate = false;
		}
		
		if(isValidate == true) {
			
			QuestionBean qBean = new QuestionBean();
			qBean.setDetail(_qForm.getDetail());
			qBean.setChoix1(_qForm.getChoix1());
			qBean.setChoix2(_qForm.getChoix2());
			qBean.setChoix3(_qForm.getChoix3());
			qBean.setChoix4(_qForm.getChoix4());
			qBean.setAnswer(_qForm.getAnswer());
			qBean.setLevelId(_qForm.getLevelId());
			qBean.setDomainId(_qForm.getDomainId());
			
			int rs = new QuestionDAO().addQuestionBean(qBean);
			if(rs > 0){
				request.setAttribute("msg_notifi", "Đã thêm mới thành công.");
				return mapping.findForward("success");
			} else {
				request.setAttribute("msg_notifi", "Bạn cần điền chính xác các thông tin");
				return mapping.findForward("fail");
			}
			
		} else {
			request.setAttribute("msg_notifi", "Bạn cần điền đủ và chính xác các thông tin");
			return mapping.findForward("fail");
		}
	}
	
}
