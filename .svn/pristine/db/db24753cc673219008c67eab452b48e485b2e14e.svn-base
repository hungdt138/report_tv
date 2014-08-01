package com.tv.xeeng.reporttool.cron;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HourlyTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		Date date =  new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int hour   = date.getHours() - 1;
		hour = 15;
		String day = "";
		
        if (hour < 0) {
        	hour = 23;
        	Calendar calendar = Calendar.getInstance();
        	calendar.add(Calendar.DAY_OF_YEAR, -1);// 1 is used for tomorrow date, and -1 is used for yesterday date. 
        	Date yesDate = calendar.getTime();
        	day = sdf.format(yesDate);
        } else {
        	day = sdf.format(date);
        }
		
		
		System.out.println("Ex Hourly " + day + "  " + hour);
		reportGetHourlyStat(day, hour);
	}
	
	public static boolean reportGetHourlyStat (String day, int hour) throws SQLException {
	 
	    String query = "{call uspReportGetHourlyStat(?,?) }";
	    Connection conn = DB.getConnection();
	    CallableStatement cs = null;
	    try {
	        cs = conn.prepareCall(query);
	        cs.clearParameters();
	        cs.setString(1, day);
	        cs.setInt(2, hour);
	        cs.executeUpdate();
	    } catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());

	    } finally {
	        if (cs != null) {
	            cs.close();
	        }
	        
	        if (conn != null) {
	            conn.close();
	        }
	    } 
	    
	    return true;
	}
}
