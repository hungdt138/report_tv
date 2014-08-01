package com.tv.xeeng.reporttool.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Duyvv
 */
public final class DateHelper {

    /**
     *
     * @param input
     * @param total
     * @return
     */
    public static final String dateAdd(String input, Integer total) {
        String output = "";
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
            Date inputDate = sdfInput.parse(input);
            long t = inputDate.getTime();
            t += total * 24 * 60 * 60 * 1000;
            Date outputDate = new Date(t);
            output = sdfInput.format(outputDate);
        } catch (Exception ex) {
            System.err.println("dateAdd:" + ex.getMessage());
        }
        return output;
    }

    /**
     * convert data to database format
     *
     * @param input
     * @return
     */
    public static final String convertDate(String input) {
        String output = null;
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdfInput.parse(input);
            SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
            output = sdfOutput.format(date);
        } catch (Exception ex) {
            System.err.println("convertDate:" + ex.getMessage());
        }
        return output;
    }
    
    /**
     * convert data to database format
     *
     * @param input
     * @return
     */
    public static final String convertFormatDate(String input, String iFormat, String oFormat) {
        String output = null;
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat(iFormat);
            Date date = sdfInput.parse(input);
            SimpleDateFormat sdfOutput = new SimpleDateFormat(oFormat);
            output = sdfOutput.format(date);
        } catch (Exception ex) {
            System.err.println("convertDate:" + ex.getMessage());
        }
        return output;
    }
    
    public static final String dateToString(Date date, String format) {
        String output = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            output = sdf.format(date);
        } catch (Exception ex) {
            System.err.println("convertDate:" + ex.getMessage());
        }
        return output;
    }

    /**
     *
     * @return date string format dd-mm-yyyy for today
     */
    public static final String getDisplayDate() {
        String dateDisplay = "";
        Calendar cld = null;
        cld = Calendar.getInstance();
        dateDisplay += (cld.get(Calendar.DAY_OF_MONTH) < 10 ? ("0" + cld.get(Calendar.DAY_OF_MONTH)) : cld.get(Calendar.DAY_OF_MONTH)) + "/";
        dateDisplay += (cld.get(Calendar.MONTH) < 9 ? ("0" + (cld.get(Calendar.MONTH) + 1)) : (cld.get(Calendar.MONTH) + 1)) + "/";
        dateDisplay += cld.get(Calendar.YEAR);
        return dateDisplay;
    }

    /**
     *
     * @param patttern
     * @param fullDate
     * @return
     */
    public static final String formatDate(String patttern, String fullDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(patttern);
            return formatter.format(formatter.parse(fullDate));
        } catch (Exception exception) {
        }
        return fullDate;
    }

    public static void main(String[] args) {
        String date = "2014-05-06";
        System.out.println(convertFormatDate(date,"yyyy-MM-dd","dd/MM/yyyy"));
    }
}
