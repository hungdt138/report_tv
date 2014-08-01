/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tv.xeeng.reporttool.util;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author thanhnvt
 */
public class BlahBlahUtil {
    public static int getIntValue(String data) {
        int value = 0;

        try {
            value = Integer.parseInt(data);
        } catch (Exception ex) {
        }

        return value;
    }

    public static int getRightPage(String pageRaw) {
        int page;
        try {
            page = Integer.valueOf(pageRaw);
        } catch (NumberFormatException ex) {
            page = 1;
        }

        if (page < 1) {
            page = 1;
        }

        return page;
    }

    public static String getRightString(String str) {
        return str == null ? "" : str;
    }

    public static String escapseString(String str) {
        if (str == null) {
            return null;
        }

        return str.replace("'", "\\'")
                .replace("\"", "\\\"");
    }

    public static String escapseNumber(String str) {
        try {
            return String.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException ex) {
            return "0";
        }
    }

    public static Date getDateTime(String dt) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            return formatter.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();

            return new Date();
        }
    }

    public static String getDateTimeString(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        return formatter.format(d);
    }

    public static <T> List<T> getResultSetAsList(ResultSet rs, Class<T> type) {
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();

        List<T> results = resultSetMapper.mapRersultSetToObject(rs, type);

        return results;
    }

    public static String getConditionString(String colName, String compare, String condition) {
        return String.format("%s %s %s", colName, compare, condition);
    }

    public static String join(String[] as, String glue) {
        StringBuilder sb = new StringBuilder();
        if (as.length == 0) {
            return "";
        } else if (as.length == 1) {
            return as[0];
        } else {
            for (int i = 0; i < as.length - 1; i++) {
                sb.append(as[i]).append(glue);
            }
            sb.append(as[as.length - 1]);

            return sb.toString();
        }
    }

    public static String getConditionString(String colName, String[] allowValues) {
        return String.format("%s in (%s)", colName, join(allowValues, ","));
    }

    public static boolean emptyString(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static boolean hasEmptyString(String... as) {
        for (String s : as) {
            if (emptyString(s)) {
                return true;
            }
        }

        return false;
    }

    public static int parseInt(String s, int failValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return failValue;
        }
    }

    public static long parseLong(String s, long failValue) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return failValue;
        }
    }


    public static String getHumanNumberString(long n) {
        if (Math.abs(n) < 10000) {
            return String.format("%,d", n);
        } else if (Math.abs(n) < 10000000) {
            return String.format("%,dK", n / 1000);
        } else if (Math.abs(n) < 10000000000l) {
            return String.format("%,dM", n / 1000000);
        } else {
            return String.format("%,dB", n / 1000000000);
        }
    }

    public static String getLogString(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return String.format("%s: %s", format.format(new Date()), s);
    }

    public static boolean isUrlNotFound(String url) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) urlObj.openConnection();
            huc.setRequestMethod("HEAD");

            int responseCode = huc.getResponseCode();

            return responseCode == 404;
        } catch (Exception ex) {
            return true;
        }
    }
}
