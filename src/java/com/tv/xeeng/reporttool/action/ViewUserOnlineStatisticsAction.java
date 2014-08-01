package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.NumOfUserOnlineBean;
import com.tv.xeeng.reporttool.dao.LogDAO;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewUserOnlineStatisticsAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String dayStr = request.getParameter("day");

        if (dayStr == null) {
            // if day is not specified, set to current time

            Calendar cal = Calendar.getInstance();
            dayStr = BlahBlahUtil.getDateTimeString(cal.getTime());
        }

        Date day = BlahBlahUtil.getDateTime(dayStr);
        Calendar dayCal = Calendar.getInstance();
        dayCal.setTime(day);

        Calendar dateTimeCal = Calendar.getInstance();
        dateTimeCal.set(dayCal.get(Calendar.YEAR), dayCal.get(Calendar.MONTH), dayCal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);

        day = dateTimeCal.getTime();

        List<NumOfUserOnlineBean> numsBy5Minutes = LogDAO.getNumOfUserOnlineBy5Minutes(day);
        List<NumOfUserOnlineBean> numsByHour = LogDAO.getNumOfUserOnlineByHour(day);
        List<NumOfUserOnlineBean> numsByDay = LogDAO.getNumOfUserOnlineByDay(day);
        List<NumOfUserOnlineBean> numsMaxByHour = LogDAO.getMaxNumOfUserOnlineByHour(day);
        List<NumOfUserOnlineBean> numsMaxByDay = LogDAO.getMaxNumOfUserOnlineByDay(day);

        request.setAttribute("day", dayStr);
        request.setAttribute("numsBy5Minutes", numsBy5Minutes);
        request.setAttribute("numsByHour", numsByHour);
        request.setAttribute("numsByDay", numsByDay);
        request.setAttribute("numsMaxByHour", numsMaxByHour);
        request.setAttribute("numsMaxByDay", numsMaxByDay);

        return mapping.findForward("success");
    }

}
