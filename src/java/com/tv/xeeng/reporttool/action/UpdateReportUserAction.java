package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.ReportUserBean;
import com.tv.xeeng.reporttool.dao.ReportUserDAO;
import com.tv.xeeng.reporttool.forms.AddReportUserForm;

public class UpdateReportUserAction extends Action {
    /* private static Logger logger = Logger.getLogger(LoginAction.class);*/
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

        AddReportUserForm _arpuForm = (AddReportUserForm) form;

        ReportUserDAO rpuDAO = new ReportUserDAO();

        ReportUserBean rpuBean = new ReportUserBean();
        rpuBean.setUserId(_arpuForm.getUserid());
        rpuBean.setPartnerId(_arpuForm.getPartnerId());
        rpuBean.setRoleId(BlahBlahUtil.parseInt(request.getParameter("roleId"), 1));

        int result = rpuDAO.updateReportUser(rpuBean);

        if (result > 0) {
            request.setAttribute("msg_notifi", "Đã cập nhật thành công");
            return mapping.findForward(SUCCESS);
        } else {
            request.setAttribute("msg_notifi", "Cập nhật thất bại <br/> Bạn hãy kiểm tra lại");
            return mapping.findForward(FAIL);
        }
    }

}
