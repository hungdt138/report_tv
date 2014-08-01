package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;
import com.tv.xeeng.reporttool.forms.WorkingUserListByNameForm;

public class WorkingUserListByNameAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		int partnerId = ((UserBean) session.getAttribute("loggedInUser")).getPartnerId();
		WorkingUserListByNameForm _wulbForm = (WorkingUserListByNameForm) form;
		if( request.getAttribute("searchbyname")!= null) {
			String name = request.getAttribute("searchbyname").toString();
			if(request.getParameter("page") !=  null) { 
				int pageC = Integer.parseInt(request.getParameter("page"));
				if(pageC > 0) {
					_wulbForm.setWuList(new WorkingUserDAO().getAWorkingUserByName(pageC, name, partnerId));
					if(_wulbForm.getWuList().size() > 0) {
						request.setAttribute("page", pageC);
						request.setAttribute("totalRc", _wulbForm.getWuList().get(0).getTotalRecord());
						request.setAttribute("qname", name);
						return mapping.findForward("success");
					} else {
						request.setAttribute("msg_notifi", "Không tìm thấy bản ghi nào");
						return mapping.findForward("fail");
					}
					
				} else {
					_wulbForm.setWuList(new WorkingUserDAO().getAWorkingUserByName(1, name, partnerId));
					if(_wulbForm.getWuList().size() > 0) {
						request.setAttribute("page", 1);
						request.setAttribute("qname", name);
						request.setAttribute("totalRc", _wulbForm.getWuList().get(0).getTotalRecord());
						return mapping.findForward("success");
					} else {
						request.setAttribute("msg_notifi", "Không tìm thấy bản ghi nào");
						return mapping.findForward("fail");
					}
					
				}
			} else {
				request.setAttribute("msg_notifi", "Không tìm thấy trang cần hiển thị");
				return mapping.findForward("fail");
			}
			
			
		} else if(request.getParameter("searchbyname") != null) {
			String name = request.getParameter("searchbyname").toString();
			if(request.getParameter("page") !=  null) { 
				int pageC = Integer.parseInt(request.getParameter("page"));
				
				if(pageC > 0) {
					_wulbForm.setWuList(new WorkingUserDAO().getAWorkingUserByName(pageC, name, partnerId));
					if(_wulbForm.getWuList().size() > 0) {
						request.setAttribute("page", pageC);
						request.setAttribute("qname", name);
						request.setAttribute("totalRc", _wulbForm.getWuList().get(0).getTotalRecord());
						return mapping.findForward("success");
					} else {
						request.setAttribute("msg_notifi", "Không tìm thấy bản ghi nào");
						return mapping.findForward("fail");
					}
					
				} else {
					_wulbForm.setWuList(new WorkingUserDAO().getAWorkingUserByName(1, name, partnerId));
					if(_wulbForm.getWuList().size() > 0) {
						request.setAttribute("page", 1);
						request.setAttribute("qname", name);
						request.setAttribute("totalRc", _wulbForm.getWuList().get(0).getTotalRecord());
						return mapping.findForward("success");
					} else {
						request.setAttribute("msg_notifi", "Không tìm thấy bản ghi nào");
						return mapping.findForward("fail");
					}
					
				}
				
				
			} else {
				request.setAttribute("msg_notifi", "Không tìm thấy trang cần hiển thị");
				return mapping.findForward("fail");
			}
		} else {
			request.setAttribute("msg_notifi", "Không tìm thấy tên tài khoản cần truy vấn dữ liệu<br/> Bạn hãy kiểm tra lại");
			return mapping.findForward("fail");
		}

	}
}
