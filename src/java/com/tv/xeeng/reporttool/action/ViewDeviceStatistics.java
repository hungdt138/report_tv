package com.tv.xeeng.reporttool.action;

import com.tv.xeeng.reporttool.beans.DeviceStatistics;
import com.tv.xeeng.reporttool.beans.NumOfPayerByDay;
import com.tv.xeeng.reporttool.beans.NumOfUserDeviceByDay;
import com.tv.xeeng.reporttool.beans.UserOSCount;
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

public class ViewDeviceStatistics extends Action {

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
        List<NumOfUserDeviceByDay> stat = LogDAO.getNumOfUserDeviceByDay(month.getTime());
        List<UserOSCount> monthStat = LogDAO.getNumOfUserDeviceByMonth(month.getTime());

        int numOfAndroid = 0, numOfIOS = 0, numOfJava = 0;
        for (UserOSCount os : monthStat) {
            if (os.getOsName().contains("android")) {
                numOfAndroid += os.getOsCount();
            } else if (os.getOsName().contains("iOS")) {
                numOfIOS += os.getOsCount();
            } else if (os.getOsName().contains("java")) { // vẫn nên có if để xử lý các trường hợp gửi sai hoặc không gửi (do sai lệch version)
                numOfJava += os.getOsCount();
            }
        }

        request.setAttribute("stat", stat);
        request.setAttribute("monthStat", monthStat);
        request.setAttribute("monthStr", monthStr);

        request.setAttribute("numOfAndroid", numOfAndroid);
        request.setAttribute("numOfIOS", numOfIOS);
        request.setAttribute("numOfJava", numOfJava);

        return mapping.findForward("success");
    }

}
