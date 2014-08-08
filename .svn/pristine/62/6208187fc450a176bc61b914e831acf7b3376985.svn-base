/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.TopChargingMoney;
import com.tv.xeeng.reporttool.dao.LogDAO;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
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
public class TopChargingMoneyAction extends org.apache.struts.action.Action {

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

        String idStr = BlahBlahUtil.getRightString(request.getParameter("t"));
        int top = !"".equals(idStr) ? Integer.parseInt(idStr) : 10;
        List<TopChargingMoney> lstTop = LogDAO.getTopChargingMoney(top);

        request.setAttribute("lstTop", lstTop);
        request.setAttribute("top", top);
        request.setAttribute("lstSize", lstTop.size());
        return mapping.findForward(SUCCESS);
    }
}
