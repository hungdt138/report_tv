/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.dao;

import com.tv.xeeng.reporttool.beans.ShopItemDailyStatistics;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.WorkingUserBean;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHUCTV
 */
public class UserDAO {

    public static UserBean getUserLogin(String username, String password) {
        String query = "{call uspReportDoLogin(?,?) }";
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        UserBean ret = null;
        try {
            conn = DBPoolConnection.getConnection();
            cs = conn.prepareCall(query);
            cs.clearParameters();
            cs.setString(1, username);
            cs.setString(2, password);
            rs = cs.executeQuery();

            List<UserBean> users = BlahBlahUtil.getResultSetAsList(rs, UserBean.class);
            if (users != null && !users.isEmpty()) {
                return users.get(0);
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int changePassword(String username, String password) throws SQLException {
        String query = "update ReportUser SET password='" + password + "'" + "where name='" + username + "'";
        //=======MrKiddy_Lovely========//
        Connection conn = null;
        PreparedStatement psmt = null;
        int result = 0;
        try {
            conn = DBPoolConnection.getConnection();
            psmt = conn.prepareStatement(query);
            result = psmt.executeUpdate();
            // return result;
        } finally {
            if (psmt != null) {
                psmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return result;
    }

    public static List<UserBean> getSuperUsers(int pageCurrent) throws SQLException {

        int rowNumDisplay = 20;
        int rowIdFirst = ((pageCurrent - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;

        ArrayList<UserBean> ret = new ArrayList<UserBean>();
        String sql1 = " SELECT count(*) FROM WorkingUser t1 INNER JOIN SuperUser t2 ON t1.userId = t2.userId ";
        String query = "select * from "
                + " (select *,row_number() over (order by lastLogin DESC) as r from ("
                + " SELECT t1.userId, t1.name, t1.cash, t1.lastLogin, t1.isActive, t1.isOnline "
                + " FROM WorkingUser  t1 "
                + " INNER JOIN SuperUser t2 ON t1.userId = t2.userId "
                + " )t) "
                + " t where r >= ? and r <= ? ";
        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql1);
            rs = psmt.executeQuery();
            if (rs.next()) {
                totalRc = rs.getInt(1);
                System.out.println("Tong ban ghi: " + totalRc);
                totalPage = totalRc / rowNumDisplay;
                System.out.println("Tong trang: " + totalPage);
                if (totalRc > (totalPage * rowNumDisplay)) {
                    totalPage = totalPage + 1;
                }
                System.out.print("totalRC = " + totalRc + "totalPage = " + totalPage + "rF = " + rowIdFirst + "rL = " + rowIdLast);
                if (totalPage > 0) {
                    try {
                        psmt = conn.prepareStatement(query);
                        psmt.setInt(1, rowIdFirst);
                        psmt.setInt(2, rowIdLast);
                        rs = psmt.executeQuery();
                        if (rs != null) {
                            while (rs.next()) {
                                UserBean bean = new UserBean();
                                bean.setId(rs.getLong("userId"));
                                bean.setUsername(rs.getString("name"));
                                bean.setMoney(rs.getInt("cash"));
                                bean.setLastLogin(rs.getString("lastLogin"));
                                bean.setActive((rs.getInt("isActive") == 1) ? true : false);
                                bean.setOnline(rs.getInt("isOnline"));
                                bean.setTotalPage(totalPage);
                                bean.setTotalRecord(totalRc);
                                ret.add(bean);
                            }
                        } else {
                            System.out.print("rs = null");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

            }
            psmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

    public int deleteUser(int userId) {
        String sql = "DELETE FROM superuser WHERE userId = ?";
        int result = 0;
        Connection conn;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, userId);
            result = psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public String addASuperUser(String userName) {
        int result = 0;
        StringBuilder info = new StringBuilder("Chỉ định: " + userName + " là Super User không thành công<br/> Bạn hãy kiểm tra lại");
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql1 = "SELECT COUNT(*) FROM WorkingUser WHERE name = ?";
            PreparedStatement psmt = conn.prepareStatement(sql1);
            psmt.setString(1, userName);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 1) {
                    String sql2 = "SELECT userId FROM WorkingUser WHERE name = ?";
                    psmt = conn.prepareStatement(sql2);
                    psmt.setString(1, userName);
                    rs = psmt.executeQuery();
                    if (rs.next()) {
                        String sql3 = "SELECT COUNT(*) FROM superuser WHERE userId = ?";
                        psmt = conn.prepareStatement(sql3);
                        int userId = rs.getInt(1);
                        psmt.setInt(1, userId);
                        rs = psmt.executeQuery();
                        if (rs.next()) {
                            if (rs.getInt(1) == 0) {
                                String sql4 = "INSERT INTO superuser VALUES(?)";
                                psmt = conn.prepareStatement(sql4);
                                psmt.setInt(1, userId);

                                result = psmt.executeUpdate();
                                rs.close();
                                psmt.close();
                                conn.close();
                                info = new StringBuilder("\n Đã chỉ định: " + userName + " là Super User thành công");
                                return info.toString();
                            } else {
                                rs.close();
                                psmt.close();
                                conn.close();
                                return info.toString();
                            }

                        } else {
                            rs.close();
                            psmt.close();
                            conn.close();
                            return info.toString();
                        }

                    } else {
                        rs.close();
                        psmt.close();
                        conn.close();
                        return info.toString();
                    }

                } else {
                    rs.close();
                    psmt.close();
                    conn.close();
                    return info.toString();
                }
            } else {
                rs.close();
                psmt.close();
                conn.close();
                return info.toString();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return info.toString();
    }


    public static void main(String[] args) {
//    	try {
//			List<UserBean> ret = new UserDAO().getSuperUsers(1);
//			System.out.print(ret.size());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	String rs = new UserDAO().addASuperUser("rretretreterterterter");
//    	
//    	System.out.print(rs);
//    	Connection conn;
//		try {
//			conn = DBPoolConnection.getConnection();
//			String sql = "INSERT INTO superuser VALUES(690)";
//	    	PreparedStatement psmt = conn.prepareStatement(sql);
//	    	int rs = psmt.executeUpdate();
//	    	System.out.print(rs);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }
}
