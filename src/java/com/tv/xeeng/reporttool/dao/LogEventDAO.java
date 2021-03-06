/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.dao;

import com.tv.xeeng.reporttool.beans.LogEventGiftHistory;
import com.tv.xeeng.reporttool.beans.LogEventItemHistory;
import com.tv.xeeng.reporttool.beans.XeengEventBean;
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
            String fromDate, String toDate, int eventId) throws SQLException {
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
            search += "AND w.name like '%" + username + "%' ";
        }

        if (itemCode != null && !"".equalsIgnoreCase(itemCode.trim())) {
            search += "AND x.itemCode like '%" + itemCode + "%' ";
        }

        if (fromDate != null && toDate != null) {
            search += "AND x.time >= '" + fromDate + "' AND x.time <= '" + toDate + "' ";
        }

        if (eventId != 0) {
            search += "AND x.eventId = " + eventId + "";
        }

        try {
            String sql_count = "select COUNT(x.id) from xeLogJoinItemHistory x\n"
                    + "	inner join workinguser w\n"
                    + "	on x.userId = w.userId \n"
                    + " inner join xeInGameEvent e \n"
                    + " on e.id = x.eventId " + search;
            String sqlStr = "select * from (select ROW_NUMBER() over (order by time desc) as row, x.*, w.name "
                    + "from xeLogJoinItemHistory x "
                    + "inner join workinguser w "
                    + "on x.userId = w.userId "
                    + "inner join xeInGameEvent e "
                    + "on e.id = x.eventId " + search + " ) as log "
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

    public static List<LogEventItemHistory> getItemHistory(int currentPage, String itemCode, int eventId) throws SQLException {
        List<LogEventItemHistory> lst = new ArrayList<>();
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
        if (itemCode != null && !"".equalsIgnoreCase(itemCode.trim())) {
            search += "AND itemCode like '%" + itemCode + "%' ";
        }

        if (eventId != 0) {
            search += "AND e.id = " + eventId + "";
        }
        try {
            String sqlCount = "SELECT \n"
                    + "	COUNT(*) \n"
                    + "FROM \n"
                    + "(SELECT \n"
                    + "		ITEMCODE, \n"
                    + "		COUNT(*) AS COUNT \n"
                    + "	  FROM \n"
                    + "		XELOGJOINITEMHISTORY\n"
                    + "	  GROUP BY ITEMCODE) C \n"
                    + "INNER JOIN (select * from xeEventItems where id in (select MIN(id) from xeEventItems group by code)) I\n"
                    + "ON C.ITEMCODE = I.CODE \n"
                    + "inner join xeInGameEvent e "
                    + "on I.eventId = e.id " + search;
            String sqlStr = "SELECT ROWNUM, C.ITEMCODE, C.COUNT, I.NAME\n"
                    + "FROM (SELECT ROW_NUMBER() OVER (ORDER BY ITEMCODE) AS ROWNUM ,ITEMCODE, COUNT(*) AS COUNT FROM XELOGJOINITEMHISTORY\n"
                    + "	GROUP BY ITEMCODE) C INNER JOIN (select * from xeEventItems where id in (select MIN(id) from xeEventItems group by code)) I\n"
                    + "		ON C.ITEMCODE = I.CODE\n "
                    + "inner join xeInGameEvent e "
                    + "on I.eventId = e.id "
                    + search + " AND ROWNUM BETWEEN ? AND ?";

            connection = DBPoolConnection.getConnection();
            stmtCount = connection.prepareStatement(sqlCount);
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
                    log.setItemCode(rs.getString("itemCode"));
                    log.setCount(rs.getInt("Count"));
                    log.setMessage(rs.getString("NAME"));
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

    public static List<LogEventGiftHistory> getAllLogEventGiftHistory(int currentPage, String username,
            String evgfCode, String fromDate, String toDate, int eventId) throws SQLException {
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
            search += "and w.name like '%" + username + "%' ";
        }
        if (evgfCode != null && !"".equalsIgnoreCase(evgfCode)) {
            search += "and g.evgfCode like '%" + evgfCode + "%'";
        }

        if (fromDate != null && toDate != null) {
            search += "AND g.useDate >= '" + fromDate + "' AND g.useDate <= '" + toDate + "' ";
        }

        if (eventId != 0) {
            search += "AND e.id = " + eventId + "";
        }
        try {
            String sql_cout = "select COUNT(g.id) from xeLogEventGiftsHistory g \n"
                    + "	inner join workinguser w\n"
                    + "	on g.userId = w.userId "
                    + " inner join xeInGameEvent e"
                    + " on g.eventId = e.id " + search;
            String sqlStr = "select * from (select ROW_NUMBER() over (order by useDate desc) as row, g.*, u.name\n"
                    + "				from xeLogEventGiftsHistory g\n"
                    + "				inner join workinguser u\n"
                    + "				on g.userId = u.userId "
                    + "inner join xeInGameEvent e "
                    + "on g.eventId = e.id " + search + ") as log\n"
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

    public static List<LogEventGiftHistory> getGiftHistory(int currentPage,
            String evgfCode, int eventId) throws SQLException {
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
        String search = "WHERE 1 = 1 ";
        if (evgfCode != null && !"".equalsIgnoreCase(evgfCode.trim())) {
            search += "AND e.evgfCode like '%" + evgfCode + "%'";
        }

        String eventSearch = "WHERE 1 = 1 ";
        if (eventId != 0) {
            eventSearch += "AND eventId = " + eventId + "";
        }
        try {
            String sqlCount = "select count(*) from \n"
                    + "(select evgfCode, COUNT(*) as count from xeLogEventGiftsHistory "
                    + eventSearch
                    + "	group by evgfCode) g inner join xeEventGifts e\n"
                    + "	on g.evgfCode = e.evgfCode " + search;
            String sqlStr = "select g.evgfCode, g.count, e.value, e.type, e.name from \n"
                    + "(select ROW_NUMBER() over (order by COUNT(*) desc) as rownum, evgfCode, COUNT(*) as count from xeLogEventGiftsHistory "
                    + eventSearch
                    + "	group by evgfCode) g inner join xeEventGifts e\n"
                    + "	on g.evgfCode = e.evgfCode\n"
                    + search + " AND ROWNUM BETWEEN ? AND ?";

            connection = DBPoolConnection.getConnection();
            stmtCount = connection.prepareStatement(sqlCount);
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
                    log.setCount(rs.getInt("count"));
                    log.setValue(rs.getInt("value"));
                    log.setMessage(rs.getString("name"));
                    log.setType(rs.getString("type"));
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

    public static List<LogEventGiftHistory> getUserLimit(int currentPage,
            String username, int eventId) throws SQLException {
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
        String search = "WHERE 1 = 1 ";
        if (username != null && !"".equalsIgnoreCase(username.trim())) {
            search += "AND a.name like '%" + username + "%'";
        }

        String eventSearch = "WHERE 1 = 1 ";
        if (eventId != 0) {
            eventSearch += "AND e.id = " + eventId + "";
        }
        try {
            String sqlCount = "select count(*) from (select w.name, COUNT(*) as count, x1.limit from xeLogEventGiftsHistory g \n"
                    + "	inner join (select * from xeEventItems where id in (select MIN(id) from xeEventItems group by code)) x1\n"
                    + "	on g.evitCode = x1.code\n"
                    + "	inner join workinguser w\n"
                    + "	on g.userId = w.userId\n"
                    + " inner join xeInGameEvent e"
                    + " on x1.eventId = e.id "
                    + eventSearch
                    + "	group by w.name, x1.limit\n"
                    + "	having COUNT (g.id) >= x1.limit) a " + search;
            String sqlStr = "select * from ( select ROW_NUMBER() over (order by COUNT(*) desc) as rownum, w.name, COUNT(*) as count, x1.limit from xeLogEventGiftsHistory g \n"
                    + "	inner join (select * from xeEventItems where id in (select MIN(id) from xeEventItems group by code)) x1\n"
                    + "	on g.evitCode = x1.code\n"
                    + "	inner join workinguser w\n"
                    + "	on g.userId = w.userId\n"
                    + " inner join xeInGameEvent e"
                    + " on x1.eventId = e.id "
                    + eventSearch
                    + "	group by w.name, x1.limit\n"
                    + "	having COUNT (g.id) >= x1.limit) a\n"
                    + search + "and a.rownum between ? and ?";
            connection = DBPoolConnection.getConnection();
            stmtCount = connection.prepareStatement(sqlCount);
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
                    log.setUsername(rs.getString("name"));
                    log.setCount(rs.getInt("count"));
                    log.setValue(rs.getInt("limit"));
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

    public static int totalOpenGift(int eventId) {
        int count = 0;
        Connection connection = null;
        PreparedStatement stmtCount = null;
        ResultSet rsCount = null;
         String eventSearch = "WHERE 1 = 1 ";
        if (eventId != 0) {
            eventSearch += "AND eventId = " + eventId + "";
        }
        try {
            String sql = "select COUNT(*) from xeLogEventGiftsHistory "  + eventSearch;
            connection = DBPoolConnection.getConnection();
            stmtCount = connection.prepareCall(sql);
            rsCount = stmtCount.executeQuery();
            if (rsCount.next()) {
                count = rsCount.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPoolConnection.closeObject(connection);
            DBPoolConnection.closeObject(stmtCount);
            DBPoolConnection.closeObject(rsCount);
        }
        return count;
    }

    public static int countUserEvent(int eventId) {
        int count = 0;
        Connection connection = null;
        PreparedStatement stmtCount = null;
        ResultSet rsCount = null;
         String eventSearch = "WHERE 1 = 1 ";
        if (eventId != 0) {
            eventSearch += "AND eventId = " + eventId + "";
        }
        try {
            String sql = "select count(b.userId) from (select userId, COUNT(*) as c from xeLogJoinItemHistory "+eventSearch+" group by userId) as b";
            connection = DBPoolConnection.getConnection();
            stmtCount = connection.prepareCall(sql);
            rsCount = stmtCount.executeQuery();
            if (rsCount.next()) {
                count = rsCount.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPoolConnection.closeObject(connection);
            DBPoolConnection.closeObject(stmtCount);
            DBPoolConnection.closeObject(rsCount);
        }
        return count;
    }

    public static List<XeengEventBean> getAllXeengEvent() {
        List<XeengEventBean> lst = new ArrayList<XeengEventBean>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "select id, name, content, description, components, type, isActive from xeInGameEvent order by id desc";
            connection = DBPoolConnection.getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            XeengEventBean xe = null;
            while (rs.next()) {
                xe = new XeengEventBean();
                xe.setId(rs.getInt("id"));
                xe.setContent(rs.getString("content"));
                xe.setDescription(rs.getString("description"));
                xe.setIsActive(rs.getBoolean("isActive"));
                xe.setName(rs.getString("name"));
                xe.setType(rs.getString("type"));
                lst.add(xe);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPoolConnection.closeObject(connection);
            DBPoolConnection.closeObject(stmt);
            DBPoolConnection.closeObject(rs);
        }
        return lst;
    }
}
