/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.LogEventItemHistory;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.XeengEventBean;
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
public class viewItemStatisticsAction extends org.apache.struts.action.Action {

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
        String itemCode = BlahBlahUtil.getRightString(request.getParameter("i"));
        String idStr = BlahBlahUtil.getRightString(request.getParameter("e"));
        int eventId = !"".equals(idStr) ? Integer.parseInt(idStr) : 0;

        List<LogEventItemHistory> lstLogItem = LogEventDAO.getItemHistory(page, itemCode, eventId);
        int totalPage = 0;
        int totalRc = 0;
        if (lstLogItem.size() != 0) {
            totalPage = (int) Math.ceil(lstLogItem.get(0).getTotalRecord() * 1.0 / Constant.PAGE_SIZE);
            totalRc = lstLogItem.get(0).getTotalRecord();
        }
         List<XeengEventBean> lstEvent = LogEventDAO.getAllXeengEvent();
        request.setAttribute("page", page);
        request.setAttribute("log", lstLogItem);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("itemCode", itemCode);
        request.setAttribute("lstSize", lstLogItem.size());
        request.setAttribute("total", totalRc);
        request.setAttribute("lstEvent", lstEvent);
        request.setAttribute("eventId", eventId);
        
        return mapping.findForward(SUCCESS);
    }
}
