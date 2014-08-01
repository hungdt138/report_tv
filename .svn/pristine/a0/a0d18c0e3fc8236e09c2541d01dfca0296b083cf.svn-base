package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.GiffCodeDAO;
import com.tv.xeeng.reporttool.forms.AddGiftCodeForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddGiftCodeAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()) {
            response.sendRedirect("404.html");
            return null;
        }

        AddGiftCodeForm _agcForm = (AddGiftCodeForm) form;
        if (_agcForm.getValue() >= 1000 && _agcForm.getTotal() > 0) {
            boolean success = new GiffCodeDAO().createGiftCode(_agcForm.getValue(), _agcForm.getTotal(), _agcForm.getType());
            if (success) {
                request.setAttribute("msg_notifi", "Thêm thành công.");
                return mapping.findForward("success");
            } else {
                request.setAttribute("msg_notifi", "Có lỗi xảy ra, bạn hãy kiểm tra lại");
                return mapping.findForward("fail");
            }
        } else {
            request.setAttribute("msg_notifi", "Bạn cần điền chính xác thông tin đầu vào!!");
            return mapping.findForward("fail");
        }

    }

}
