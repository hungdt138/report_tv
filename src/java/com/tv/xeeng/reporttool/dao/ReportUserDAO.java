package com.tv.xeeng.reporttool.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tv.xeeng.reporttool.beans.ReportUserBean;
import com.tv.xeeng.reporttool.crypto.MD5;
public class ReportUserDAO {
	
	public int checkUsername(String userName) {
		int result = 0;
		try {
			Connection conn = DBPoolConnection.getConnection();
			String sql = "SELECT * FROM [ReportUser] WHERE name= ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, userName);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	} 
	
	public int insertReportUser(ReportUserBean newReportUser) {
		int result = 0;
		try {
			Connection conn = DBPoolConnection.getConnection();
			String sql = "INSERT INTO [ReportUser] " +
                    "(name, password, partnerId, userTypeId, roleId, active) " +
                    "VALUES " +
                    "(?,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, newReportUser.getName());
			psmt.setString(2, MD5.md5(newReportUser.getPassword()));
			psmt.setInt(3, newReportUser.getPartnerId());
			psmt.setInt(4, newReportUser.getUserTypeId());
            psmt.setInt(5, newReportUser.getRoleId());
            psmt.setInt(6, 1); // mặc định tài khoản sẽ ở trạng thái active = true
			
			result = psmt.executeUpdate();
			
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public List<ReportUserBean> getDataByNumRow(int pageCurrent) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int rowNumDisplay = 20;
		int rowIdFirst = ((pageCurrent-1)*rowNumDisplay) + 1;
		int rowIdLast = rowIdFirst + (rowNumDisplay -1);
		int totalRc = 0;
		int totalPage = 0;
		List<ReportUserBean> rpuList = new ArrayList<ReportUserBean>();
		String sql1 = "SELECT Userid = COUNT(Userid) FROM ReportUser, xeReportUserRole WHERE ReportUser.roleId = xeReportUserRole.id";
		String sql2 = "select * from  (select ReportUser.*, xeReportUserRole.roleName, row_number() over (order by Userid) as r from ReportUser, xeReportUserRole WHERE ReportUser.roleId = xeReportUserRole.id ) data_row where r >=? and r <= ?";
		try {
			conn = DBPoolConnection.getConnection();
			psmt = conn.prepareStatement(sql1);
			rs = psmt.executeQuery();
			if(rs.next()) {
				totalRc = rs.getInt(1);
				totalPage = totalRc/rowNumDisplay;
				if(totalRc > (totalPage* rowNumDisplay))
				{
					totalPage = totalPage+1;
				}
				if( totalPage > 0) {
					psmt = conn.prepareStatement(sql2);
					psmt.setInt(1, rowIdFirst);
					psmt.setInt(2, rowIdLast);
					rs = psmt.executeQuery();
					
					while(rs.next()) {
						ReportUserBean rpuBean = new ReportUserBean();
						rpuBean.setUserId(rs.getInt("Userid"));
						rpuBean.setName(rs.getString("name"));
						rpuBean.setPartnerId(rs.getInt("partnerId"));
						rpuBean.setUserTypeId(rs.getInt("userTypeId"));
                        rpuBean.setRoleName(rs.getString("roleName"));
						rpuBean.setTotalPage(totalPage);
						rpuBean.setTotalRecord(totalRc);
						
						rpuList.add(rpuBean);
					}
					
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rpuList;
	}
	
	public int updateReportUser(ReportUserBean rpuBean) {
		int result = 0;
		try {
			Connection conn = DBPoolConnection.getConnection();
			String sql = "UPDATE [ReportUser] SET roleId = ? WHERE Userid = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);

			psmt.setInt(1, rpuBean.getRoleId());
			psmt.setInt(2, rpuBean.getUserId());
			
			result = psmt.executeUpdate();
		
			psmt.close();
		
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ReportUserBean getReportUserById (int Userid) {
		Connection conn;
		PreparedStatement psmt;
		ResultSet rs;
		ReportUserBean rpuBean = new ReportUserBean();
		try {
			conn = DBPoolConnection.getConnection();
			String sql = "SELECT * FROM [ReportUser] WHERE Userid = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Userid);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) { 
				rpuBean.setUserId(rs.getInt("Userid"));
				rpuBean.setName(rs.getString("name"));
				rpuBean.setPassword(rs.getString("password"));
				rpuBean.setPartnerId(rs.getInt("partnerId"));
				rpuBean.setUserTypeId(rs.getInt("userTypeId"));
                rpuBean.setRoleId(rs.getInt("roleId"));
				
				rs.close();
				psmt.close();
				conn.close();
				
			} else {
				
				rs.close();
				psmt.close();
				conn.close();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return rpuBean;
	}
	
	public int deleteReportUserById(int userid) {
		int result = 0;
		try {
			Connection conn = DBPoolConnection.getConnection();
			String sql = "DELETE FROM [ReportUser] WHERE Userid = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userid);
			result = psmt.executeUpdate();
			
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*public static void main(String[] args) {			
		ReportUserDAO rpuDAO = new ReportUserDAO();
		List<ReportUserBean> rpuLsit = rpuDAO.getDataByNumRow(1);
		for(ReportUserBean rpuBean : rpuLsit) {
			System.out.print("id: "+ rpuBean.getUserId());
			System.out.print("name: "+ rpuBean.getName());
			System.out.print("partnerid: "+ rpuBean.getPartnerId());
			System.out.print("usertypeid: "+ rpuBean.getUserTypeId());
			System.out.print("totalPage: "+ rpuBean.getTotalPage());
		}
		ReportUserBean rpuBean = new ReportUserBean();
		rpuBean.setUserId(2);
		rpuBean.setPassword("1234567");
		rpuBean.setPartnerId(0);
		rpuBean.setUserTypeId(0);
		int kq = rpuDAO.updateReportUser(rpuBean);
		if(kq > 0) {
			System.out.print("Update thanh cong");
		} else {
			System.out.print("Update that bai");
		}
		
	}*/
}
