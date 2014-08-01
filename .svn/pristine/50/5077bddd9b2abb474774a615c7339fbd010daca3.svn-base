package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.WorkingUserDAO;
import com.tv.xeeng.reporttool.forms.WorkingUserListForm;
import com.tv.xeeng.reporttool.util.DateHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class WorkingUserListAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser.getIsIA()) {
            response.sendRedirect("404.html");
            return null;
        }

        HttpSession session = request.getSession();
        WorkingUserListForm _wuList = (WorkingUserListForm) form;
        int pageC;

        if (request.getParameter("page") == null) {
            pageC = 1;
        } else {
            pageC = Integer.parseInt(request.getParameter("page"));
            if (pageC < 1) {
                pageC = 1;
            }
        }

        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");

        String displayDate = DateHelper.getDisplayDate();
        if (fromDate == null || "".equals(fromDate.trim())) {
            fromDate = displayDate;
        }

        if (toDate == null || "".equals(toDate.trim())) {
            toDate = displayDate;
        }

        int orderType = getIntValue(request.getParameter("orderType"));

        int userId = getIntValue(request.getParameter("userId"));

        String name = request.getParameter("name");

        String nickName = request.getParameter("nickName");

        int partnerId = getIntValue(request.getParameter("partnerId"));

        int refCode = getIntValue(request.getParameter("refCode"));

        _wuList.setWuList(new WorkingUserDAO().getAll(pageC, partnerId, name, nickName, userId, refCode, orderType, DateHelper.convertDate(fromDate), DateHelper.convertDate(toDate)));

        request.setAttribute("page", pageC);
        request.setAttribute("orderType", orderType);
        request.setAttribute("name", name);
        request.setAttribute("nickName", nickName);
        request.setAttribute("fromDate", fromDate);
        request.setAttribute("toDate", toDate);

        if (partnerId > 0) {
            request.setAttribute("partnerId", partnerId);
        }
        if (refCode > 0) {
            request.setAttribute("refCode", refCode);
        }
        if (userId > 0) {
            request.setAttribute("userId", userId);
        }

        request.setAttribute("partners", WorkingUserDAO.getAllPartners());

//		if(_wuList.getWuList().size() > 0) {
        return mapping.findForward("success");
//		} 
//		else {
//			request.setAttribute("msg_notifi", "Không tồn tại bản ghi nào");
//			return mapping.findForward("fail");
//		}
    }

    public int getIntValue(String data) {
        int value = 0;

        try {
            value = Integer.parseInt(data);
        } catch (Exception ex) {
        }

        return value;
    }

}
