/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.dao;

import com.tv.xeeng.reporttool.beans.LogEventGiftHistory;
import com.tv.xeeng.reporttool.beans.LogEventItemHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hungdt
 */
public class LogEventDAO {

    public static List<LogEventItemHistory> getAllLogEventItemHistory(int currentPage, String username, String itemCode,
            String fromDate, String toDate) throws SQLException {
        List<LogEventItemHistory> lst = new ArrayList<LogEventItemHistory>();
        String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
        int rowNumDisplay = 20;
        int rowIdFirst = ((currentPage - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Connection connection = null;
        PreparedStatement stmtCount = null;
        ResultSet rsCount = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LogEventItemHistory log = null;

        String search = "WHERE 1 = 1 ";
        if (username != null && !"".equalsIgnoreCase(username.trim())) {
            search += "AND name like '%" + username + "%'";
        }

        if (itemCode != null && !"".equalsIgnoreCase(itemCode.trim())) {
            search += "AND itemCode like '%" + itemCode + "%'";
        }

        if (fromDate != null && toDate != null) {
            search += "AND time >= '" + fromDate + "' AND time <= '" + toDate + "' ";
        }

        try {
            String sql_count = "select COUNT(id) from xeLogJoinItemHistory x\n"
                    + "	inner join workinguser w\n"
                    + "	on x.userId = w.userId " + search;
            String sqlStr = "select * from (select ROW_NUMBER() over (order by time desc) as row, x.*, w.name "
                    + "from xeLogJoinItemHistory x "
                    + "inner join workinguser w "
                    + "on x.userId = w.userId " + search + " ) as log "
                    + "where row between ? and ?";

            connection = DBPoolConnection.getConnection();
            stmtCount = connection.prepareStatement(sql_count);
            rsCount = stmtCount.executeQuery();
            if (rsCount.next()) {
                totalRc = rsCount.getInt(1);
                totalPage = totalRc / rowNumDisplay;
                if (totalRc > (totalPage * rowNumDisplay)) {
                    totalPage = totalPage + 1;
                }
            }

            if (totalPage > 0) {
                stmt = connection.prepareStatement(sqlStr);
                stmt.setInt(1, rowIdFirst);
                stmt.setInt(2, rowIdLast);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    log = new LogEventItemHistory();
                    log.setId(rs.getInt("id"));
                    log.setItemCode(rs.getString("itemCode"));
                    log.setMessage(rs.getString("message"));
                    log.setDatetime(rs.getTimestamp("time"));
                    log.setUsername(rs.getString("name"));
                    log.setUserid(rs.getInt("userId"));
                    log.setTotalPage(totalPage);
                    log.setTotalRecord(totalRc);
                    lst.add(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPoolConnection.closeObject(connection);
            DBPoolConnection.closeObject(stmt);
            DBPoolConnection.closeObject(stmtCount);
            DBPoolConnection.closeObject(rs);
            DBPoolConnection.closeObject(rsCount);
        }

        return lst;
    }

    public static List<LogEventItemHistory> getLogEventItemHistoryByUserId(int currentPage, int userId) throws SQLException {
        List<LogEventItemHistory> lst = new ArrayList<LogEventItemHistory>();
        String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
        int rowNumDisplay = 20;
        int rowIdFirst = ((currentPage - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Connection connection = null;
        PreparedStatement stmtCount = null;
        ResultSet rsCount = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LogEventItemHistory log = null;
        try {
            String sql_count = "select COUNT(id) from xeLogJoinItemHistory where userId = ?";
            String sqlStr = "select * from (select ROW_NUMBER() over (order by time desc) as row, x.*, w.name "
                    + "from xeLogJoinItemHistory x "
                    + "inner join workinguser w "
                    + "on x.userId = w.userId) as log "
                    + "where userid = ? "
                    + "and row between ? and ?";

            connection = DBPoolConnection.getConnection();
            stmtCount = connection.prepareStatement(sql_count);
            stmtCount.setInt(1, userId);
            rsCount = stmtCount.executeQuery();
            if (rsCount.next()) {
                totalRc = rs.getInt(1);
                totalPage = totalRc / rowNumDisplay;
                if (totalRc > (totalPage * rowNumDisplay)) {
                    totalPage = totalPage + 1;
                }
            }

            if (totalPage > 0) {
                stmt = connection.prepareStatement(sqlStr);
                stmt.setInt(1, userId);
                stmt.setInt(2, rowIdFirst);
                stmt.setInt(3, rowIdLast);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    log = new LogEventItemHistory();
                    log.setId(rs.getInt("id"));
                    log.setItemCode(rs.getString("itemCode"));
                    log.setMessage(rs.getString("message"));
                    //     log.setDatetime(sdf.format(rs.getDate("time")));
                    log.setUsername(rs.getString("name"));
                    lst.add(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPoolConnection.closeObject(connection);
            DBPoolConnection.closeObject(stmt);
            DBPoolConnection.closeObject(stmtCount);
            DBPoolConnection.closeObject(rs);
            DBPoolConnection.closeObject(rsCount);
        }
        return lst;
    }

    public List<LogEventItemHistory> getLogEventItemHistoryByDate(int currentPage, String fromDate, String toDate) {
        List<LogEventItemHistory> lst = new ArrayList<LogEventItemHistory>();

        return lst;
    }

    public static List<LogEventGiftHistory> getAllLogEventGiftHistory(int currentPage, String username,
            String evgfCode, String fromDate, String toDate) throws SQLException {
        List<LogEventGiftHistory> lst = new ArrayList<LogEventGiftHistory>();
        String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
        int rowNumDisplay = 20;
        int rowIdFirst = ((currentPage - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

        Connection connection = null;
        PreparedStatement stmtCount = null;
        ResultSet rsCount = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LogEventGiftHistory log = null;

        String search = "WHERE 1=1 ";
        if (username != null && !"".equalsIgnoreCase(username)) {
            search += "and name like '%" + username + "%' ";
        }
        if (evgfCode != null && !"".equalsIgnoreCase(evgfCode)) {
            search += "and evgfCode like '%" + evgfCode + "%'";
        }

        if (fromDate != null && toDate != null) {
            search += "AND useDate >= '" + fromDate + "' AND useDate <= '" + toDate + "' ";
        }
        try {
            String sql_cout = "select COUNT(id) from xeLogEventGiftsHistory g \n"
                    + "	inner join workinguser w\n"
                    + "	on g.userId = w.userId " + search;
            String sqlStr = "select * from (select ROW_NUMBER() over (order by useDate desc) as row, g.*, u.name\n"
                    + "				from xeLogEventGiftsHistory g\n"
                    + "				inner join workinguser u\n"
                    + "				on g.userId = u.userId " + search + ") as log\n"
                    + "				where  row between ? and ?";

            connection = DBPoolConnection.getConnection();
            stmtCount = connection.prepareStatement(sql_cout);
            rsCount = stmtCount.executeQuery();
            if (rsCount.next()) {
                totalRc = rsCount.getInt(1);
                totalPage = totalRc / rowNumDisplay;
                if (totalRc > (totalPage * rowNumDisplay)) {
                    totalPage = totalPage + 1;
                }
            }
            if (totalPage > 0) {
                stmt = connection.prepareStatement(sqlStr);
                stmt.setInt(1, rowIdFirst);
                stmt.setInt(2, rowIdLast);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    log = new LogEventGiftHistory();
                    log.setEvgfCode(rs.getString("evgfCode"));
                    log.setFromValue(rs.getInt("fromValue"));
                    log.setId(rs.getInt("id"));
                    log.setMessage(rs.getString("message"));
                    log.setToValue(rs.getInt("toValue"));
                    log.setType(rs.getString("type"));
                    log.setUseDate(rs.getTimestamp("useDate"));
                    log.setUserId(rs.getInt("userId"));
                    log.setValue(rs.getInt("value"));
                    log.setUsername(rs.getString("name"));
                    log.setTotalPage(totalPage);
                    log.setTotalRecord(totalRc);
                    lst.add(log);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPoolConnection.closeObject(connection);
            DBPoolConnection.closeObject(stmt);
            DBPoolConnection.closeObject(stmtCount);
            DBPoolConnection.closeObject(rs);
            DBPoolConnection.closeObject(rsCount);
        }
        return lst;
    }

    public List<LogEventGiftHistory> getLogEventGiftHistoryByUserId(int currentPage, int userId) {
        List<LogEventGiftHistory> lst = new ArrayList<LogEventGiftHistory>();

        return lst;
    }

    public List<LogEventGiftHistory> getLogEventGiftHistoryByDate(int currentPage, String fromDate, String toDate) {
        List<LogEventGiftHistory> lst = new ArrayList<LogEventGiftHistory>();

        return lst;
    }
}
