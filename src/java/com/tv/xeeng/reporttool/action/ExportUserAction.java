package com.tv.xeeng.reporttool.action;

import java.util.List;

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
import com.tv.xeeng.reporttool.forms.ChangeUserCashForm;
import com.tv.xeeng.reporttool.util.CSVUtils;
import com.tv.xeeng.reporttool.util.DateHelper;

public class ExportUserAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		int partnerId = ((UserBean) session.getAttribute("loggedInUser")).getPartnerId();
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        
        String displayDate = DateHelper.getDisplayDate();
		if(fromDate == null || "".equals(fromDate.trim())) {
			fromDate = displayDate;
		} 
			
		if(toDate == null || "".equals(toDate.trim())) {
			toDate = displayDate;
		} 
		
		int orderType = getIntValue(request.getParameter("orderType"));
		
		int userId = getIntValue(request.getParameter("userId"));
		
		String name = request.getParameter("name");

		String nickName = request.getParameter("nickName");
		
		if(partnerId <= 0) {
			partnerId = getIntValue(request.getParameter("partnerId"));
		}
		
		int refCode = getIntValue(request.getParameter("refCode"));
		
		String data = new WorkingUserDAO().exportData(partnerId, name, nickName, userId, refCode, orderType, DateHelper.convertDate(fromDate),DateHelper.convertDate(toDate));
		
		// create export data
		boolean result = CSVUtils.downloadFile("users.csv", response, data);
			
			if(result) {
				request.setAttribute("msg_notifi", "Download thành công!");
				return mapping.findForward("success");
			} else {
				request.setAttribute("msg_notifi", "Download thất bại!");
				return mapping.findForward("fail");
			}
		}

		public int getIntValue(String data) {
			int value = 0;
			try {
				value = Integer.parseInt(data);
			} catch(Exception ex) {
			}
			
			return value;
		}

}
