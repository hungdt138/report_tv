package com.tv.xeeng.reporttool.cron;

import com.tv.xeeng.reporttool.util.BlahBlahUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class XEDailyTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
        Date now = calendar.getTime();

		System.out.println(BlahBlahUtil.getLogString("Start"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dbNow = getDatabaseTime();
        while (dbNow == null || dbNow.compareTo(now) < 0) {
            // nếu thời gian trên database chưa đến thời điểm kỳ vọng thì sleep và thử lại
            System.out.println(BlahBlahUtil.getLogString(String.format("    DB.GETDATE() = %s, sleep 1s and try again...", format.format(dbNow))));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dbNow = getDatabaseTime();
        }

        System.out.println(BlahBlahUtil.getLogString(String.format("    DB.GETDATE() = %s, it's ok to do a report...", format.format(dbNow))));
        System.out.println();

        reportGetDailyStat();

        System.out.println(BlahBlahUtil.getLogString("End."));
        System.out.println();
        System.out.println();
	}
	
	public static boolean reportGetDailyStat() {
	 
	    String query = "{call uspXEDailyReport() }";

        System.out.println(BlahBlahUtil.getLogString("    Getting connection..."));
	    Connection conn = DB.getConnection();

	    CallableStatement cs = null;
	    
	    try {
	        cs = conn.prepareCall(query);

            System.out.println(BlahBlahUtil.getLogString("    Executing query..."));
	        cs.execute();

	    } catch (Exception ex) {
			System.out.println(BlahBlahUtil.getLogString("    Exception - " + ex.getMessage()));

	    } finally {
            System.out.println(BlahBlahUtil.getLogString("    Closing connection... "));

            try {
                if (cs != null) {
                    cs.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(BlahBlahUtil.getLogString("    Exception - " + ex.getMessage()));
            }
	    } 
	    
	    return true;
	}

    public static Date getDatabaseTime() {

        String query = "SELECT GETDATE() AS now";

        Connection conn = DB.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getTimestamp("now");
            }

        } catch (Exception ex) {
            System.out.println(BlahBlahUtil.getLogString("    Exception - " + ex.getMessage()));
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(BlahBlahUtil.getLogString("    Exception - " + ex.getMessage()));
            }
        }

        return null;
    }
}
