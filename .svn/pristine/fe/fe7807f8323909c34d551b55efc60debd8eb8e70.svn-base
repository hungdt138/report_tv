package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import com.tv.xeeng.reporttool.util.FileHelper;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewMatchLog extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
        throws Exception {
            UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
            if (!loggedInUser.getIsAdmin() && !loggedInUser.getIsOperator() && !loggedInUser.getIsGameMaster()) {
                response.sendRedirect("404.html");
                return null;
            }

            String log = null;

            String matchId = request.getParameter("matchId");
            if (!BlahBlahUtil.emptyString(matchId)) {
                log = FileHelper.getMatchLog(matchId);
            }

        request.setAttribute("log", log);

        return mapping.findForward("success");
    }

}
