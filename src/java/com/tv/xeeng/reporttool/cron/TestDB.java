package com.tv.xeeng.reporttool.cron;

import java.sql.Connection;

public class TestDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = DB.getConnection();
		
		if(conn != null) {
			System.out.print(" Connect success ");
		}	
		else {
			System.out.print(" Connect error ");
		}
		
		try {
			System.out.println("1");
		} catch (Exception ex) {
			System.out.println("2"); return;
		} finally {
			System.out.println("3");
		}
		
	}
}
