package com.tv.xeeng.reporttool.cron;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);// 1 is used for tomorrow date, and -1 is used for yesterday date. 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date yesDate = calendar.getTime();
		String day = sdf.format(yesDate);
		
		System.out.println("Ex Daily " + day);
		reportGetDailyStat(day);
	}
	
	public static boolean reportGetDailyStat (String day) throws SQLException {
	 
	    String query = "{call uspReportGetDailyStat(?) }";
	    Connection conn = DB.getConnection();
	    CallableStatement cs = null;
	    
	    try {
	        cs = conn.prepareCall(query);
	        cs.clearParameters();
	        cs.setString(1, day);
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
