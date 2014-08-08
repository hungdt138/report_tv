/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.action;

import com.sun.imageio.plugins.jpeg.JPEG;
import com.tv.xeeng.reporttool.beans.LogEventGiftHistory;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.XeengEventBean;
import com.tv.xeeng.reporttool.dao.LogEventDAO;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import com.tv.xeeng.reporttool.util.Constant;
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
public class ViewUsersLimitAction extends org.apache.struts.action.Action {

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
        String idStr = BlahBlahUtil.getRightString(request.getParameter("e"));
        int eventId = !"".equals(idStr) ? Integer.parseInt(idStr) : 0;

        List<LogEventGiftHistory> lstLogGift = LogEventDAO.getUserLimit(page, username, eventId);
        int totalPage = 0;
        int totalRc = 0;
        if (lstLogGift.size() != 0) {
            totalPage = (int) Math.ceil(lstLogGift.get(0).getTotalRecord() * 1.0 / Constant.PAGE_SIZE);
            totalRc = lstLogGift.get(0).getTotalRecord();
        }

        List<XeengEventBean> lstEvent = LogEventDAO.getAllXeengEvent();
        int count = LogEventDAO.totalOpenGift(eventId);
        request.setAttribute("page", page);
        request.setAttribute("log", lstLogGift);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("username", username);
        request.setAttribute("lstSize", lstLogGift.size());
        request.setAttribute("totalLog", count);
        request.setAttribute("lstEvent", lstEvent);
        request.setAttribute("eventId", eventId);

        return mapping.findForward(SUCCESS);
    }
}
