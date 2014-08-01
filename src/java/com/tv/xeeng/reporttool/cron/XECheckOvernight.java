package com.tv.xeeng.reporttool.cron;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class XECheckOvernight {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date yesDate = calendar.getTime();
		String day = sdf.format(yesDate);
		
		System.out.println(String.format("[+] (%s) Getting statistics... ", calendar.getTime().toString(), day));

        long startTime = System.currentTimeMillis();
        reportGetDailyStat();
        long endTime = System.currentTimeMillis();

        System.out.println(String.format("    - Done.", endTime - startTime));
        System.out.println();
        System.out.println();
	}
	
	public static boolean reportGetDailyStat () throws SQLException {
	 
	    String query = "{call uspXECheckOvernight() }";

        System.out.println(String.format("    - Getting connection..."));
	    Connection conn = DB.getConnection();

	    CallableStatement cs = null;
	    
	    try {
	        cs = conn.prepareCall(query);

            System.out.println(String.format("    - Executing query..."));
	        cs.executeUpdate();

	    } catch (Exception ex) {
			System.out.println("    - Exception " + ex.getMessage());

	    } finally {
            System.out.println("    - Closing connection... ");

            try {
                if (cs != null) {
                    cs.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("    - /!\\ Can not close connection! ");
            }
	    } 
	    
	    return true;
	}
}
