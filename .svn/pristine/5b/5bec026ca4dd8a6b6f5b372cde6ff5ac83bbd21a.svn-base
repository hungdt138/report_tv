/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.dao;

import com.tv.xeeng.reporttool.beans.DailyStatReportBean;
import com.tv.xeeng.reporttool.beans.PartnerMonthlyReportBean;
import com.tv.xeeng.reporttool.beans.UserByDayReportBean;
import com.tv.xeeng.reporttool.beans.UserByPartnerReportBean;
import com.tv.xeeng.reporttool.beans.UserChargingReportBean;
import com.tv.xeeng.reporttool.beans.UserRegisterReportBean;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHUCTV
 */
public class ReportDAO {

    public List<Object> reportByDayDetail(int partnerId, String datetime, boolean isSMS, boolean isCard, int pageIndex) throws SQLException {
        ArrayList<Object> ret = new ArrayList<Object>();

        ArrayList<UserChargingReportBean> data = new ArrayList<UserChargingReportBean>();
        String query = "{call uspReportByDayDetail(?,?,?,?,?,?,?,?) }";
        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        int totalrecord;
        int totalpage;
        try {
            conn = DBPoolConnection.getConnection();
            cs = conn.prepareCall(query);
            cs.clearParameters();
            cs.setInt(1, partnerId);
            cs.setString(2, datetime);
            cs.setBoolean(3, isSMS);
            cs.setBoolean(4, isCard);
            cs.setInt(5, pageIndex);
            cs.setInt(6, 10);
            cs.registerOutParameter(7, java.sql.Types.INTEGER);
            cs.registerOutParameter(8, java.sql.Types.INTEGER);
            rs = cs.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UserChargingReportBean bean = new UserChargingReportBean();
                    bean.setOrder(rs.getLong("row"));
                    bean.setUserId(rs.getLong("userId"));
                    bean.setUserName(rs.getString("name"));
                    bean.setDescription(rs.getString("Description"));
                    bean.setDatetime(rs.getString("dateTime"));
                    bean.setMoney(rs.getLong("money"));
                    bean.setSMS((rs.getInt("isCard")) == 1 ? false : true);
                    data.add(bean);
                }
            }
            totalrecord = cs.getInt(7);
            totalpage = cs.getInt(8);
            ret.add(data);
            ret.add(totalrecord);
            ret.add(totalpage);

        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

    public List<Object> reportByPartnerDetail(int partnerId, String fromdate, String todate, boolean isSMS, boolean isCard, int pageIndex) throws SQLException {
        ArrayList<Object> ret = new ArrayList<Object>();
        ArrayList<UserChargingReportBean> data = new ArrayList<UserChargingReportBean>();
        fromdate = fromdate + " 00:00:00";
        todate = todate + " 23:59:59";

        int rowNumDisplay = 20;
        int rowIdFirst = ((pageIndex - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;

        String where = "";
        if (partnerId > 0) {
            where += " AND partnerId = " + partnerId;
        }

        if (isSMS && !isCard) {
            where += " AND phoneNumber is not null";
        }

        if (isCard && !isCard) {
            where += " AND phoneNumber is null";
        }

        String query1 = " SELECT count(*) "
                + " FROM LogRevenue t1 "
                + " INNER JOIN "
                + " WorkingUser t2 "
                + " on t1.userId = t2.userId "
                + " WHERE revenueDate >= ? and revenueDate <= ? " + where;

        String query2 = " SELECT * FROM (SELECT t2.userId, t2.name, t1.description, t1.money, t1.revenueDate, t1.phoneNumber, row_number() over (ORDER BY revenueDate DESC) as row "
                + " FROM LogRevenue t1 "
                + " INNER JOIN "
                + " WorkingUser t2 "
                + " on t1.userId = t2.userId "
                + " WHERE revenueDate >= ? and revenueDate <= ? " + where + " ) data_row where row>=? and row<=?";

        System.out.print(" query 1" + query1 + fromdate + todate);

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;

        try {
            conn = DBPoolConnection.getConnection();
            psmt = conn.prepareStatement(query1);
            psmt.setString(1, fromdate);
            psmt.setString(2, todate);
            rs = psmt.executeQuery();
            if (rs.next()) {

                totalRc = rs.getInt(1);
                System.out.println("Tong ban ghi: " + totalRc);
                totalPage = totalRc / rowNumDisplay;
                System.out.println("Tong trang: " + totalPage);
                if (totalRc > (totalPage * rowNumDisplay)) {
                    totalPage = totalPage + 1;
                }

                if (totalPage > 0) {
                    psmt = conn.prepareStatement(query2);
                    psmt.setString(1, fromdate);
                    psmt.setString(2, todate);
                    psmt.setInt(3, rowIdFirst);
                    psmt.setInt(4, rowIdLast);
                    rs = psmt.executeQuery();
                    while (rs.next()) {
                        UserChargingReportBean bean = new UserChargingReportBean();
                        bean.setOrder(rs.getLong("row"));
                        bean.setUserId(rs.getLong("userId"));
                        bean.setUserName(rs.getString("name"));
                        bean.setDescription(rs.getString("description"));
                        bean.setDatetime(rs.getString("revenueDate"));
                        bean.setMoney(rs.getLong("money"));
                        bean.setSMS((rs.getString("phoneNumber") == null) ? false : true);
                        data.add(bean);
                    }
                }
                ret.add(data);
                ret.add(totalRc);
                ret.add(totalPage);
            }

        } finally {
            if (psmt != null) {
                psmt.close();
            }
            if (rs != null) {
                rs.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

    public List<Object> reportRegister(int partnerId, String fromdate, String todate, int pageIndex) throws SQLException {
        ArrayList<Object> ret = new ArrayList<Object>();
        ArrayList<UserRegisterReportBean> data = new ArrayList<UserRegisterReportBean>();
        String query = "{call uspReportRegisters(?,?,?,?,?,?,?) }";
        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        int totalrecord;
        int totalpage;
        try {
            conn = DBPoolConnection.getConnection();
            cs = conn.prepareCall(query);
            cs.clearParameters();
            cs.setInt(1, partnerId);
            cs.setString(2, fromdate);
            cs.setString(3, todate);
            cs.setInt(4, pageIndex);
            cs.setInt(5, 10);
            cs.registerOutParameter(6, java.sql.Types.INTEGER);
            cs.registerOutParameter(7, java.sql.Types.INTEGER);
            rs = cs.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UserRegisterReportBean bean = new UserRegisterReportBean();
                    bean.setOrder(rs.getLong("row"));
                    bean.setUserId(rs.getLong("userId"));
                    bean.setUserName(rs.getString("name"));
                    bean.setMoney(rs.getLong("cash"));
                    bean.setRegisterDate(rs.getString("registerDate"));
                    data.add(bean);
                }
            }
            totalrecord = cs.getInt(6);
            totalpage = cs.getInt(7);
            ret.add(data);
            ret.add(totalrecord);
            ret.add(totalpage);
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

    public List<UserByDayReportBean> reportByDay(int partnerId, String fromdate, String todate) throws SQLException {
        ArrayList<UserByDayReportBean> ret = new ArrayList<UserByDayReportBean>();
        String query = "";
        fromdate = fromdate + " 00:00:00";
        todate = todate + " 23:59:59";

        if (partnerId > 0) {
            query = "SELECT ISNULL(t3.countDate, t4.countDate) as countDate, sms, card, SMSVND, CardVND "
                    + "FROM "
                    + "(select CONVERT(varchar, revenueDate, 103) as countDate, COUNT(*) sms, SUM(tRev.VND) SMSVND from LogRevenue t1 "
                    + "inner join RevenueType tRev on t1.RevenueTypeId = tRev.RevenueTypeId "
                    + "inner join WorkingUser t2 on t1.userId = t2.userId "
                    + "where revenueDate >= ? and revenueDate <= ? and t2.partnerId = " + partnerId + " "
                    + "and t1.phoneNumber is not null "
                    + "group by CONVERT(varchar, revenueDate, 103)) t3 "
                    + "FULL OUTER JOIN "
                    + "( select CONVERT(varchar, revenueDate, 103) as countDate , COUNT(*) card, SUM(tRev.VND) CardVND from LogRevenue t1 "
                    + "inner join RevenueType tRev on t1.RevenueTypeId = tRev.RevenueTypeId "
                    + "inner join WorkingUser t2 on t1.userId = t2.userId "
                    + "where revenueDate >= ? and revenueDate <= ? and t2.partnerId = " + partnerId + " "
                    + "and t1.phoneNumber is null "
                    + "group by CONVERT(varchar, revenueDate, 103)) t4 "
                    + "ON t3.countDate = t4.countDate "
                    + "ORDER BY countDate desc";
        } else {
            query = "SELECT ISNULL(t3.countDate, t4.countDate) as countDate, sms, card, SMSVND, CardVND "
                    + "FROM "
                    + "(select CONVERT(varchar, revenueDate, 103) as countDate, COUNT(*) sms, SUM(tRev.VND) SMSVND from LogRevenue t1 "
                    + "inner join RevenueType tRev on t1.RevenueTypeId = tRev.RevenueTypeId "
                    + "where revenueDate >= ? and revenueDate <= ? "
                    + "and t1.phoneNumber is not null "
                    + "group by CONVERT(varchar, revenueDate, 103)) t3 "
                    + "FULL OUTER JOIN "
                    + "( select CONVERT(varchar, revenueDate, 103) as countDate , COUNT(*) card, SUM(tRev.VND) CardVND from LogRevenue t1 "
                    + "inner join RevenueType tRev on t1.RevenueTypeId = tRev.RevenueTypeId "
                    + "where revenueDate >= ? and revenueDate <= ? "
                    + "and t1.phoneNumber is null "
                    + "group by CONVERT(varchar, revenueDate, 103)) t4 "
                    + "ON t3.countDate = t4.countDate "
                    + "ORDER BY countDate desc";

        }

        System.out.println(query + fromdate + todate);

        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            conn = DBPoolConnection.getConnection();
            cs = conn.prepareCall(query);
            cs.clearParameters();
            cs.setString(1, fromdate);
            cs.setString(2, todate);
            cs.setString(3, fromdate);
            cs.setString(4, todate);
            rs = cs.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UserByDayReportBean bean = new UserByDayReportBean();
                    bean.setTotalcard(rs.getLong("card"));
                    bean.setTotalCardVND(rs.getLong("CardVND"));
                    bean.setTotalsms(rs.getLong("sms"));
                    bean.setTotalSMSVND(rs.getLong("SMSVND"));
                    bean.setDatetime(rs.getString("countDate"));
                    ret.add(bean);
                }
            }
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

    public List<UserByPartnerReportBean> reportByPartner(String fromdate, String todate) throws SQLException {
        ArrayList<UserByPartnerReportBean> ret = new ArrayList<UserByPartnerReportBean>();
        fromdate = fromdate + " 00:00:00";
        todate = todate + " 23:59:59";

        String query = " SELECT isnull(t4.partnerId,t3.partnerId) as partnerId, isnull(t4.name,t3.name) as partnerName, card, sms, cardVND, smsVND FROM "
                + "( SELECT t2.partnerId, tp.name, COUNT(*) as card, SUM(tRev.VND) as cardVND "
                + "FROM LogRevenue t1 "
                + "INNER JOIN "
                + "WorkingUser t2 "
                + "on t1.userId = t2.userId "
                + "INNER JOIN "
                + "Partner tp "
                + "on t2.partnerId = tp.partnerId "
                + "inner join RevenueType tRev on t1.RevenueTypeId = tRev.RevenueTypeId "
                + "WHERE revenueDate >= ? and revenueDate <= ? "
                + "and t1.phoneNumber is null "
                + "Group by t2.partnerId, tp.name) t3 "
                + "FULL OUTER JOIN "
                + "(SELECT t2.partnerId, tp.name, COUNT(*) as sms, SUM(tRev.VND) as smsVND "
                + "FROM LogRevenue t1 "
                + "INNER JOIN "
                + "WorkingUser t2 "
                + "on t1.userId = t2.userId "
                + "INNER JOIN "
                + "Partner tp "
                + "on t2.partnerId = tp.partnerId "
                + "inner join RevenueType tRev on t1.RevenueTypeId = tRev.RevenueTypeId "
                + "WHERE revenueDate >= ? and revenueDate <= ? "
                + "and t1.phoneNumber is not null "
                + "Group by t2.partnerId, tp.name) t4 "
                + "ON t3.partnerId = t4.partnerId "
                + "order by partnerId asc ";
        System.out.println(" sql " + query + fromdate + todate);
        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            conn = DBPoolConnection.getConnection();
            cs = conn.prepareCall(query);
            cs.clearParameters();
            cs.setString(1, fromdate);
            cs.setString(2, todate);
            cs.setString(3, fromdate);
            cs.setString(4, todate);
            rs = cs.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UserByPartnerReportBean bean = new UserByPartnerReportBean();
                    bean.setTotalcard(rs.getLong("card"));
                    bean.setTotalsms(rs.getLong("sms"));

                    bean.setTotalCardVND(rs.getLong("cardVND"));
                    bean.setTotalSMSVND(rs.getLong("smsVND"));

                    bean.setPartnerId(rs.getInt("partnerId"));
                    bean.setPartnerName(rs.getString("partnerName"));
                    ret.add(bean);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

    public List<Object> reportDailyStat(String fromdate, String todate, int pageIndex) throws SQLException {
        ArrayList<Object> ret = new ArrayList<Object>();
        ArrayList<DailyStatReportBean> data = new ArrayList<DailyStatReportBean>();
        String query = "{call uspReportDailyStat(?,?,?,?,?,?) }";
        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        int totalrecord;
        int totalpage;
        try {
            conn = DBPoolConnection.getConnection();
            cs = conn.prepareCall(query);
            cs.clearParameters();
            cs.setString(1, fromdate);
            cs.setString(2, todate);
            cs.setInt(3, pageIndex);
            cs.setInt(4, 24);
            cs.registerOutParameter(5, java.sql.Types.INTEGER);
            cs.registerOutParameter(6, java.sql.Types.INTEGER);
            rs = cs.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    DailyStatReportBean bean = new DailyStatReportBean();
                    bean.setOrder(rs.getLong("row"));
                    bean.setDay(rs.getString("day"));
                    bean.setHour(rs.getInt("hour"));
                    bean.setDownloadCount(rs.getInt("downloadCount"));
                    bean.setRegistCount(rs.getInt("registCount"));
                    bean.setLoginCount(rs.getInt("loginCount"));
                    bean.setCcu(rs.getInt("ccu"));
                    bean.setSmsCount(rs.getInt("smsCount"));
                    bean.setCardCount(rs.getInt("cardCount"));
                    bean.setSamCount(rs.getInt("samCount"));
                    bean.setSamMoney(rs.getInt("samMoney"));
                    bean.setAltpCount(rs.getInt("altpCount"));
                    bean.setAltpMoney(rs.getInt("altpMoney"));
                    bean.setPhomCount(rs.getInt("phomCount"));
                    bean.setPhomMoney(rs.getInt("phomMoney"));
                    bean.setTlmnCount(rs.getInt("tlmnCount"));
                    bean.setTlmnMoney(rs.getInt("tlmnMoney"));
                    bean.setPicachuCount(rs.getInt("picachuCount"));
                    bean.setPicachuMoney(rs.getInt("picachuMoney"));
                    bean.setLiengCount(rs.getInt("liengCount"));
                    bean.setLiengMoney(rs.getInt("liengMoney"));
                    bean.setXitoCount(rs.getInt("xitoCount"));
                    bean.setXitoMoney(rs.getInt("xitoMoney"));
                    bean.setBacayCount(rs.getInt("bacayCount"));
                    bean.setBacayMoney(rs.getInt("bacayMoney"));
                    bean.setBaucuaCount(rs.getInt("baucuaCount"));
                    bean.setBaucuaMoney(rs.getInt("baucauMoney"));
                    bean.setUniqueUserGameCount(rs.getInt("uniqueUserGameCount"));
                    bean.setRevenueMoney(rs.getInt("revenueMoney"));
                    bean.setVascMoney(rs.getInt("vascMoney"));
                    bean.setFeeMoney(rs.getInt("feeMoney"));
                    data.add(bean);
                }
            }
            totalrecord = cs.getInt(5);
            totalpage = cs.getInt(6);
            ret.add(data);
            ret.add(totalrecord);
            ret.add(totalpage);

        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

    public List<PartnerMonthlyReportBean> reportParnerMonthly(int partnerId, String fromDate, String toDate, int type) throws SQLException {
        ArrayList<PartnerMonthlyReportBean> ret = new ArrayList<PartnerMonthlyReportBean>();
        String query = "{call uspReportPartnerMonthly(?,?,?,?) }";
        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            conn = DBPoolConnection.getConnection();
            cs = conn.prepareCall(query);
            cs.clearParameters();
            cs.setString(1, fromDate);
            cs.setString(2, toDate);
            cs.setInt(3, partnerId);
            cs.setInt(4, type);

            rs = cs.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    PartnerMonthlyReportBean bean = new PartnerMonthlyReportBean();
                    bean.setPartnerId(rs.getInt("partnerId"));
                    bean.setCode(rs.getString("description"));
                    bean.setTotal(rs.getInt("total"));
                    ret.add(bean);
                }
            }
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

}
