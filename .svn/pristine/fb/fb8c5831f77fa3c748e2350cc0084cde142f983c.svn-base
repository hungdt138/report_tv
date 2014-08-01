package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.NumOfPayerByDay;
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
import java.util.List;

public class ViewMonthlyChargingStatistics extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();

        String monthStr = request.getParameter("month");

        if (monthStr == null) {
            // if month is not specified, set to current month

            Calendar cal = Calendar.getInstance();
            monthStr = BlahBlahUtil.getDateTimeString(cal.getTime());
        }

        Calendar month = Calendar.getInstance();
        month.setTime(BlahBlahUtil.getDateTime(monthStr));
        List<Integer> smsCounts = LogDAO.getMonthlySMSStatistics(month.getTime());
        List<Integer> cardCounts = LogDAO.getMonthlyCardStatistics(month.getTime());
        int numOfPayer = LogDAO.getNumOfUniquePayer(month.getTime());
        List<NumOfPayerByDay> numOfPayerByDays = LogDAO.getNumOfUniquePayerByDay(month.getTime());

        int numOfSMS = 0, numOfCard = 0;
        for (int count : smsCounts) {
            numOfSMS += count;
        }

        for (int count : cardCounts) {
            numOfCard += count;
        }

        request.setAttribute("numOfPayer", numOfPayer);
        request.setAttribute("numOfPayerByDays", numOfPayerByDays);

        request.setAttribute("numOfSMS", numOfSMS);
        request.setAttribute("numOfCard", numOfCard);

        request.setAttribute("smsValues", LogDAO.SMS_VALUES);
        request.setAttribute("cardValues", LogDAO.CARD_VALUES);
        request.setAttribute("smsCounts", smsCounts);
        request.setAttribute("cardCounts", cardCounts);
        request.setAttribute("monthStr", monthStr);

        for (int value : smsCounts) {
            if (value != 0) {
                request.setAttribute("hasSMS", true);
            }
        }

        for (int value : cardCounts) {
            if (value != 0) {
                request.setAttribute("hasCard", true);
            }
        }

        return mapping.findForward("success");
    }

}
