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

public class GetAQuestionAction extends Action {

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
			
			int questionId = Integer.parseInt(request.getParameter("questionId"));
			if(questionId > 0) {
				QuestionForm _qForm = (QuestionForm) form;
				QuestionBean qBean  = new QuestionDAO().getQuestionById(questionId);
				if(qBean.getQuestionId() > 0) {
					_qForm.setQuestionId(qBean.getQuestionId());
					_qForm.setDetail(qBean.getDetail());
					_qForm.setChoix1(qBean.getChoix1());
					_qForm.setChoix2(qBean.getChoix2());
					_qForm.setChoix3(qBean.getChoix3());
					_qForm.setChoix4(qBean.getChoix4());
					_qForm.setAnswer(qBean.getAnswer());
					_qForm.setLevelId(qBean.getLevelId());
					_qForm.setDomainId(qBean.getDomainId());
					
					request.setAttribute("aw", qBean.getAnswer());
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Không tồn tại mã câu hỏi là: " + questionId);
					return mapping.findForward("fail");
				}
				
			} else {
					request.setAttribute("msg_notifi", "Không tìm thấy mã câu hỏi cần truy vấn!");
					return mapping.findForward("fail");
			} 
			
	}
	

}
