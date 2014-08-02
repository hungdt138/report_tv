/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.LogEventGiftHistory;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.LogEventDAO;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import com.tv.xeeng.reporttool.util.Constant;
import com.tv.xeeng.reporttool.util.DateHelper;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author hungdt
 */
public class ViewGiftEventAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsAdmin()
                && !loggedInUser.getIsOperator()) {
            response.sendRedirect("404.html");
            return null;
        }

        int page = BlahBlahUtil.getRightPage(request.getParameter("p"));
        String username = BlahBlahUtil.getRightString(request.getParameter("u"));
        String fromDate = DateHelper.convertDate(request.getParameter("f"));
        String toDate = DateHelper.convertDate(request.getParameter("t"));
        String evgfCode = BlahBlahUtil.getRightString(request.getParameter("e"));

        List<LogEventGiftHistory> lstLogGift = LogEventDAO.getAllLogEventGiftHistory(page, username,  evgfCode, fromDate, toDate);
        int totalPage = 0;
        if (lstLogGift.size() != 0) {
            totalPage = (int) Math.ceil(lstLogGift.get(0).getTotalRecord() * 1.0 / Constant.PAGE_SIZE);
        }

        request.setAttribute("page", page);
        request.setAttribute("log", lstLogGift);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("fromDate", request.getParameter("f"));
        request.setAttribute("toDate", request.getParameter("t"));
        request.setAttribute("username", username);
        request.setAttribute("evgfCode", evgfCode);
        request.setAttribute("lstSize", lstLogGift.size());

        return mapping.findForward(SUCCESS);
    }
}
