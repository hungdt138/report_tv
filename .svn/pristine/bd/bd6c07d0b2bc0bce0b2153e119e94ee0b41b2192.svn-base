package com.tv.xeeng.reporttool.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.tv.xeeng.reporttool.beans.GiffCodeBean;

public class GiffCodeDAO {

    private final int USER_NAME = 2;
    private final int SERIAL = 3;

    public List<GiffCodeBean> getAll(int pageCurrent) {
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
        List<GiffCodeBean> giffCodeList = new ArrayList<GiffCodeBean>();
        String sql1 = "SELECT [GiftCodeId] = COUNT(GiftCodeId) FROM GiftCode";
        String sql2 = "select * from  (select *,row_number() over (order by GiftCodeId DESC) as r from GiftCode ) data_row where r >=? and r <= ?";
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
                        GiffCodeBean giffCodeBean = new GiffCodeBean();
                        giffCodeBean.setGiffCodeId(rs2.getInt(1));
                        giffCodeBean.setSerial(rs2.getString(2));
                        giffCodeBean.setCreatedDate(rs2.getDate(3));
                        giffCodeBean.setCreatedTime(rs2.getTime(3));
                        giffCodeBean.setUsedDate(rs2.getDate(4));
                        giffCodeBean.setUsedTime(rs2.getTime(4));
                        giffCodeBean.setBonusMoney(rs2.getInt(5));
                        giffCodeBean.setMarketingChannelId(rs2.getInt(6));
                        giffCodeBean.setTotalPage(totalPage);
                        giffCodeBean.setTotalRecord(totalRc);
                        giffCodeBean.setType(rs2.getString("type"));

                        giffCodeList.add(giffCodeBean);
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
                return null;
            }
        }
        return giffCodeList;
    }

    public int deleteGiffCode(int giftCodeId) {
        int rs = 0;
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = " DELETE FROM GiftCode WHERE GiftCodeId = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, giftCodeId);
            rs = psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }

    public int addGiffCode(int value, int total) {
        int result = 0;
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call  uspCreateGiftCode(?,?,?,?) }";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, value);
            psmt.setInt(2, total);
            result = psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Tạo Giftcode
     *
     * @param numOfGold Giá trị Giftcode
     * @param numOfCode Số lượng Giftcode
     * @param type Loại Giftcode (GC1, GC2...)
     * @return
     */
    public boolean createGiftCode(int numOfGold, int numOfCode, String type) {
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call  uspCreateGiftCode(?,?,?) }";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, numOfGold);
            psmt.setInt(2, numOfCode);
            psmt.setString(3, type);
            psmt.execute();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public int useGiftCode(String username, int serial) {
        int result = 99;
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql1 = "SELECT COUNT(*) FROM WorkingUser WHERE name = ?";
            PreparedStatement psmt1 = conn.prepareStatement(sql1);
            psmt1.setString(1, username);
            ResultSet rs1 = psmt1.executeQuery();
            if (rs1.next() && rs1.getInt(1) == 1) {
                rs1.close();
                psmt1.close();

                String sql = "{? = call uspUseGiftCodeUserName(?, ?)}";
                CallableStatement cs = conn.prepareCall(sql);
                cs.registerOutParameter(1, Types.INTEGER);
                cs.setString(USER_NAME, username);
                cs.setInt(SERIAL, serial);
                cs.execute();
                result = cs.getInt(1);
                cs.close();
                conn.close();
            } else {
                return -99;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public List<GiffCodeBean> getGiftCodeByName(int pageCurrent, int money) {
        List<GiffCodeBean> gcList = new ArrayList<GiffCodeBean>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        int rowNumDisplay = 20;
        int rowIdFirst = ((pageCurrent - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;
        String sql1 = "SELECT GiftCodeId = COUNT(*) FROM GiftCode WHERE bonusMoney = ?";
        String sql2 = "select * from  (select *,row_number() over (order by GiftCodeId) as r from GiftCode WHERE bonusMoney = ?) data_row where r >=? and r <= ?";
        try {
            conn = DBPoolConnection.getConnection();
            psmt = conn.prepareStatement(sql1);
            psmt.setInt(1, money);
            rs = psmt.executeQuery();

            if (rs.next()) {
                totalRc = rs.getInt(1);
                totalPage = totalRc / rowNumDisplay;
                if (totalRc > (totalPage * rowNumDisplay)) {
                    totalPage = totalPage + 1;
                }
                if (totalPage > 0) {
                    rs.close();
                    psmt.close();

                    psmt = conn.prepareStatement(sql2);
                    psmt.setInt(1, money);
                    psmt.setInt(2, rowIdFirst);
                    psmt.setInt(3, rowIdLast);
                    rs = psmt.executeQuery();

                    while (rs.next()) {
                        GiffCodeBean giffCodeBean = new GiffCodeBean();
                        giffCodeBean.setGiffCodeId(rs.getInt(1));
                        giffCodeBean.setSerial(rs.getString(2));
                        giffCodeBean.setCreatedDate(rs.getDate(3));
                        giffCodeBean.setCreatedTime(rs.getTime(3));
                        giffCodeBean.setUsedDate(rs.getDate(4));
                        giffCodeBean.setUsedTime(rs.getTime(4));
                        giffCodeBean.setBonusMoney(rs.getInt(5));
                        giffCodeBean.setMarketingChannelId(rs.getInt(6));
                        giffCodeBean.setTotalPage(totalPage);
                        giffCodeBean.setTotalRecord(totalRc);

                        gcList.add(giffCodeBean);
                    }

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return gcList;
    }

    public GiffCodeBean getAGiftCodeById(int id) {
        String sql = "SELECT * FROM GiftCode WHERE GiftCodeId = ?";
        GiffCodeBean gcBean = new GiffCodeBean();
        try {
            Connection conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                gcBean.setGiffCodeId(rs.getInt(1));
                gcBean.setSerial(rs.getString(2));
                gcBean.setCreatedDate(rs.getDate(3));
                gcBean.setCreatedTime(rs.getTime(3));
                gcBean.setUsedDate(rs.getDate(4));
                gcBean.setUsedTime(rs.getTime(4));
                gcBean.setBonusMoney(rs.getInt(5));
                gcBean.setMarketingChannelId(rs.getInt(6));

            }

            rs.close();
            psmt.close();
            conn.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return gcBean;
    }

    public static void main(String[] args) {

//			int kq = new GiffCodeDAO().useGiftCode("admin", 91561872);
//			System.out.print("kq = "+kq);
//			
//			
        List<GiffCodeBean> gcListName = new GiffCodeDAO().getGiftCodeByName(1, 2000);
        System.out.print(gcListName.size() + " ttRC: " + gcListName.get(0).getTotalRecord());

//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss aa");
//		Calendar cal = Calendar.getInstance();
//		String datetime = dateFormat.format(cal.getTime());
//		System.out.println(datetime);
//		
//		Connection conn;
//		try {
//			conn = DBPoolConnection.getConnection();
//			String sql = "INSERT INTO [ReportDB].[dbo].[GiftCode] VALUES(?,?,?,?,?)";
//			PreparedStatement psmt = conn.prepareStatement(sql);
//			psmt.setString(1, "NGUYENVANHAI");
//			psmt.setString(2, datetime );
//			psmt.setString(3, datetime );
//			psmt.setInt(4, 9999);
//			psmt.setInt(5, 1);
//			
//			int kq = psmt.executeUpdate();
//			System.out.println("kq: "+kq);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
    }
}
