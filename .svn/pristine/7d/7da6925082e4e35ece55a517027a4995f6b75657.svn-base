package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.WorkingUserBean;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;
import com.tv.xeeng.reporttool.forms.WorkingUserForm;

public class SearchWorkingUserAction extends Action {
	/*private static Logger logger = Logger.getLogger(SearchWorkingUserAction.class);*/
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		int partnerId = ((UserBean) session.getAttribute("loggedInUser")).getPartnerId();
		WorkingUserForm _wuForm = (WorkingUserForm) form;
		if( _wuForm.getUserId() < 0 && _wuForm.getName().equals("")) {
			request.setAttribute("msg_notifi", "Bạn đã nhập dữ liệu không chính xác<br/> Hãy điền lại thông tin để tìm kiếm chính xác hơn");
			return mapping.findForward("fail");
		} else {
				if(_wuForm.getName().equals("") || _wuForm.getUserId() > 0) {
				//search by id
					try{ 
						WorkingUserBean wuBean = new WorkingUserDAO().getAWorkingUser(_wuForm.getUserId(), partnerId );
						if(!wuBean.getName().equals("")) {
							_wuForm.setUserId(wuBean.getUserId());
							_wuForm.setName(wuBean.getName());
							_wuForm.setLastLongin(wuBean.getLastLongin());
							_wuForm.setPartnerId(wuBean.getPartnerId());
							_wuForm.setRefCode(wuBean.getRefCode());
							_wuForm.setRegisterDate(wuBean.getRegisterDate());
							_wuForm.setStateActive(wuBean.getStateOnline());
							_wuForm.setTotalRecord(wuBean.getTotalRecord());
							return mapping.findForward("success");
						} else {
							request.setAttribute("msg_notifi", "Không tồn tại tài khoản theo yêu cầu của bạn<br/> Hãy kiểm tra lại thông tin đầu vào");
							return mapping.findForward("fail");
						} 
					} catch (Exception ex) {
						request.setAttribute("msg_notifi", "Không tồn tại tài khoản theo yêu cầu của bạn<br/> Hãy kiểm tra lại thông tin đầu vào");
						return mapping.findForward("fail");
					}
					
				} else if(_wuForm.getUserId() <= 0 || !_wuForm.getName().equals("")) {
				//search by name
					request.setAttribute("searchbyname", _wuForm.getName());
					return mapping.findForward("searchbyname");
				} else {
					//search by id and name
					try{ 
						WorkingUserBean wuBean = new WorkingUserDAO().getAWorkingUserByIdAndName(_wuForm.getUserId(), _wuForm.getName());
						if(!wuBean.getName().equals("")) {
							_wuForm.setUserId(wuBean.getUserId());
							_wuForm.setName(wuBean.getName());
							_wuForm.setLastLongin(wuBean.getLastLongin());
							_wuForm.setPartnerId(wuBean.getPartnerId());
							_wuForm.setRefCode(wuBean.getRefCode());
							_wuForm.setRegisterDate(wuBean.getRegisterDate());
							_wuForm.setStateActive(wuBean.getStateOnline());
							_wuForm.setTotalRecord(wuBean.getTotalRecord());
							return mapping.findForward("success");
						} else {
							request.setAttribute("msg_notifi", "Không tồn tại tài khoản theo yêu cầu của bạn<br/> Hãy kiểm tra lại thông tin đầu vào");
							return mapping.findForward("fail");
						} 
					} catch (Exception ex) {
						request.setAttribute("msg_notifi", "Không tồn tại tài khoản theo yêu cầu của bạn<br/> Hãy kiểm tra lại thông tin đầu vào");
						return mapping.findForward("fail");
					}
				}
			}
		}
}
