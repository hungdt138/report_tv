package com.tv.xeeng.reporttool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.tv.xeeng.reporttool.beans.GoldenHourBean;

public class GoldenhourDAO {
	public List<GoldenHourBean> getAll(int pageCurrent) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		PreparedStatement psmt2 = null;
		ResultSet rs2 = null;

		int rowNumDisplay = 20;
		int rowIdFirst = ((pageCurrent - 1) * rowNumDisplay) + 1;
		int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
		int totalRc = 0;
		int totalPage = 0;
		List<GoldenHourBean> gdhList = new ArrayList<GoldenHourBean>();
		String sql1 = "SELECT [id] = COUNT(id) FROM GoldenHour";
		String sql2 = "select * from  (select *,row_number() over (order by id DESC) as r from GoldenHour ) data_row where r >=? and r <= ?";
		try {
			conn = DBPoolConnection.getConnection();
			psmt = conn.prepareStatement(sql1);
			rs = psmt.executeQuery();
			if (rs.next()) {
				totalRc = rs.getInt(1);
				totalPage = totalRc / rowNumDisplay;
				if (totalRc > (totalPage * rowNumDisplay)) {
					totalPage = totalPage + 1;
				}
				if (totalPage > 0) {
					psmt2 = conn.prepareStatement(sql2);
					psmt2.setInt(1, rowIdFirst);
					psmt2.setInt(2, rowIdLast);
					rs2 = psmt2.executeQuery();
					while (rs2.next()) {
						GoldenHourBean gdhBean = new GoldenHourBean();
						gdhBean.setId(rs2.getInt(1));
						String fromDate = rs2.getDate(2).toString() + " " + rs2.getTime(2).toString();
						gdhBean.setFromDate(fromDate);
						String toDate = rs2.getDate(3).toString() + " " + rs2.getTime(3).toString();
						gdhBean.setToDate(toDate);
						gdhBean.setActive(rs2.getBoolean(4));
						gdhBean.setType(rs2.getInt(5));
						gdhBean.setBonusAmount(rs2.getInt(6));
						gdhBean.setPartnerId(rs2.getInt(7));
						gdhBean.setExternalParam(rs2.getString(8));
						gdhBean.setDescription(rs2.getString(9));
						gdhBean.setTotalPage(totalPage);
						gdhBean.setTotalRecord(totalRc);
						
						gdhList.add(gdhBean);
					}

				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				psmt.close();
				rs2.close();
				psmt2.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gdhList;
	}
	
	public GoldenHourBean getGoldenHourById(int gdhId) {
		GoldenHourBean gdhBean = new GoldenHourBean();
		String sql = "SELECT * FROM GoldenHour WHERE id = ?";
		try {
			Connection conn = DBPoolConnection.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, gdhId);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				gdhBean.setId(rs.getInt(1));
				String fromDate = rs.getDate(2).toString();
				gdhBean.setFromDate(fromDate);
				String fromTime = rs.getTime(2).toString();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime((Date)rs.getTime(2));
				int hours = calendar.get(Calendar.HOUR_OF_DAY);
				int minutes = calendar.get(Calendar.MINUTE);
				int seconds = calendar.get(Calendar.SECOND);
				gdhBean.setFhour(String.valueOf(hours));
				gdhBean.setFminutes(String.valueOf(minutes));
				gdhBean.setFsecond(String.valueOf(seconds));
				gdhBean.setFromTime(fromTime);
				
				String toDate = rs.getDate(3).toString();
				gdhBean.setToDate(toDate);
				String toTime = rs.getTime(3).toString();
				calendar.setTime((Date)rs.getTime(3));
				hours = calendar.get(Calendar.HOUR_OF_DAY);
				minutes = calendar.get(Calendar.MINUTE);
				seconds = calendar.get(Calendar.SECOND);
				gdhBean.setThour(String.valueOf(hours));
				gdhBean.setTminutes(String.valueOf(minutes));
				gdhBean.setTsecond(String.valueOf(seconds));
				gdhBean.setToTime(toTime);
				
				gdhBean.setActive(rs.getBoolean(4));
				gdhBean.setType(rs.getInt(5));
				gdhBean.setBonusAmount(rs.getInt(6));
				gdhBean.setPartnerId(rs.getInt(7));
				gdhBean.setExternalParam(rs.getString(8));
				gdhBean.setDescription(rs.getString(9));
			}
			
			rs.close();
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gdhBean;
	}
	
	public int addGoldenHour(GoldenHourBean gdhBean) {
		
		int result = 0;
		String sql  = "INSERT INTO GoldenHour VALUES(?, ?, ?, ?, ? ,?, ?, ?)";
		Connection conn;
		try {
			conn = DBPoolConnection.getConnection();
			PreparedStatement  psmt = conn.prepareStatement(sql);
			psmt.setString(1, gdhBean.getFromDate());
			psmt.setString(2, gdhBean.getToDate());
			psmt.setBoolean(3, gdhBean.getIsActive());
			psmt.setInt(4, gdhBean.getType());
			psmt.setInt(5, gdhBean.getBonusAmount());
			psmt.setInt(6, gdhBean.getPartnerId());
			psmt.setString(7, gdhBean.getExternalParam());
			psmt.setString(8, gdhBean.getDescription());
			
			result  =  psmt.executeUpdate();
			
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int updatGoldenHour(GoldenHourBean gdhBean) {
		int result  = 0;
		String sql = "UPDATE GoldenHour SET fromDate =? , toDate=?, isActive=?, type=?, bonusAmount=?, partnerId=?, [externalParam]=?,[description]=? WHERE id=? ";
		Connection conn;
		try {
			conn = DBPoolConnection.getConnection();
			PreparedStatement psmt =  conn.prepareStatement(sql);
			psmt.setString(1, gdhBean.getFromDate());
			psmt.setString(2, gdhBean.getToDate());
			psmt.setBoolean(3, gdhBean.getIsActive());
			psmt.setInt(4, gdhBean.getType());
			psmt.setInt(5, gdhBean.getBonusAmount());
			psmt.setInt(6, gdhBean.getPartnerId());
			psmt.setString(7, gdhBean.getExternalParam());
			psmt.setString(8, gdhBean.getDescription());
			psmt.setInt(9, gdhBean.getId());
			
			result = psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteGoldenHour(int gdhId) {
		int result = 0;
		String sql = "DELETE FROm GoldenHour WHERE id=?";
		Connection conn;
		try {
			conn = DBPoolConnection.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, gdhId);
			result = psmt.executeUpdate();
			
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public static void main(String[] args) {
		List<GoldenHourBean> gdhL = new GoldenhourDAO().getAll(1);
		System.out.print(gdhL.get(4).getId()+" fDate: " +  gdhL.get(4).getFromDate() + "to DAte: " + gdhL.get(4).getToDate() + gdhL.get(4).getExternalParam() + "isActive: " +gdhL.get(4).getIsActive());
		// get one
//		
//		GoldenHourBean gdhBean = new GoldenhourDAO().getGoldenHourById(1);
//		System.out.print(gdhBean.getFromDate() + " toDate: " + gdhBean.getToDate());
//		add a gdh
//		GoldenHourBean gdhBean = new GoldenHourBean();
//		
//		gdhBean.setFromDate("2014-03-28 09:58:01");
//		gdhBean.setToDate("2014-03-28 09:58:01");
//		gdhBean.setActive(true);
//		gdhBean.setBonusAmount(1234);
//		gdhBean.setPartnerId(1);
//		gdhBean.setExternalParam("12");
//		gdhBean.setDescription("O la la'");
//		gdhBean.setId(6);
//		int kq  = new GoldenhourDAO().deleteGoldenHour(6);
//		System.out.print(kq);
		
		
	}
}
