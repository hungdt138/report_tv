package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.ShopDailyStatistics;
import com.tv.xeeng.reporttool.beans.ShopItem;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.dao.LogDAO;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewShopStatisticsAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
        if (!loggedInUser.getIsIA() && !loggedInUser.getIsAdmin() && !loggedInUser.getIsOperator()) {
            response.sendRedirect("404.html");
            return null;
        }

        HttpSession session = request.getSession();

        String dayStart = request.getParameter("dayStart");
        String dayEnd = request.getParameter("dayEnd");

        if (dayStart == null || dayEnd == null) {
            // if dayStart and dayEnd are not specified, set the range to last 7 days

            Calendar cal = Calendar.getInstance();
            dayEnd = BlahBlahUtil.getDateTimeString(cal.getTime());

            cal.add(Calendar.DAY_OF_MONTH, -6); // 6, not 7 :v
            dayStart = BlahBlahUtil.getDateTimeString(cal.getTime());
        }

        Date dateStart = BlahBlahUtil.getDateTime(dayStart);
        Date dateEnd = BlahBlahUtil.getDateTime(dayEnd);

        Calendar start = Calendar.getInstance();
        start.setTime(dateStart);

        Calendar end = Calendar.getInstance();
        end.setTime(dateEnd);
        List<ShopDailyStatistics> stat = LogDAO.getShopDailyStatistics(dateStart, dateEnd);
        List<ShopItem> shopItems = LogDAO.getAllShopItem();

        request.setAttribute("stat", stat);
        request.setAttribute("shopItems", shopItems);
        request.setAttribute("dayStart", dayStart);
        request.setAttribute("dayEnd", dayEnd);

        return mapping.findForward("success");
    }

}
