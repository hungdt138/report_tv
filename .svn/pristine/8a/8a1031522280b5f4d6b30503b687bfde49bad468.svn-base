/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.dao;

import com.tv.xeeng.reporttool.beans.*;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;
import com.tv.xeeng.reporttool.util.Constant;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author thanhnvt
 */
public class LogDAO {
    public static final int[] SMS_VALUES = {10000, 15000};
    public static final int[] CARD_VALUES = {10000, 20000, 30000, 50000, 100000, 120000, 200000, 500000};
    public static final String[] CAMPAIGN_OS = {"Android", "iOS", "Unknown"};

    public static List<LogMoneyHistory> getChargingXeengByPage(int page, String loginName, String username, String reason, int pageSize) {
        List<LogMoneyHistory> logs = new ArrayList<LogMoneyHistory>();
        String sql = ""
                + "SELECT * \n"
                + "FROM\n"
                + "    (SELECT XELogMoneyHistory.*, workinguser.loginName, workinguser.name, ROW_NUMBER() OVER (ORDER BY id DESC) as row \n"
                + "    FROM XELogMoneyHistory, workinguser "
                + "    WHERE XELogMoneyHistory.userId = workinguser.userId" +
                String.format("          AND reason in (%d, %d)", Constant.REASON_SMS, Constant.REASON_CARD);

        if (!BlahBlahUtil.emptyString(loginName)) {
            sql += " AND loginName = '{loginName}'".replace("{loginName}", BlahBlahUtil.escapseString(loginName));
        } else if (!BlahBlahUtil.emptyString(username)) {
            sql += " AND name = '{name}'".replace("{name}", BlahBlahUtil.escapseString(username));
        }

        if (!BlahBlahUtil.emptyString(reason)) {
            sql += " AND reason = " + BlahBlahUtil.escapseNumber(reason);
        }

        sql += ") AS tmp\n"
                + "WHERE row >= ? AND row <= ?\n"
                + "";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, (page - 1) * pageSize + 1);
            psmt.setInt(2, page * pageSize);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                LogMoneyHistory log = new LogMoneyHistory();
                log.setId(rs.getInt("id"));
                log.setMessage(rs.getString("message"));
                log.setModifyDate(rs.getTimestamp("modifyDate"));
                log.setMoneyAfter(rs.getLong("moneyAfter"));
                log.setMoneyBefore(rs.getLong("moneyBefore"));
                log.setMoneyDiff(rs.getLong("moneyDiff"));
                log.setMoneyType(1);
                log.setUserId(rs.getLong("userId"));
                log.setLoginName(rs.getString("loginName"));
                log.setUsername(rs.getString("name"));

                log.setRefId(rs.getString("refId"));

                logs.add(log);
            }
            rs.close();
            psmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return logs;
    }

    public static int getChargingXeengByPageCount(String loginName, String username, String reason) {
        String sql = ""
                + "SELECT COUNT(*) \n"
                + "FROM\n"
                + "    (SELECT XELogMoneyHistory.*, workinguser.loginName, workinguser.name, ROW_NUMBER() OVER (ORDER BY id DESC) as row \n"
                + "    FROM XELogMoneyHistory, workinguser "
                + "    WHERE XELogMoneyHistory.userId = workinguser.userId" +
                String.format("          AND reason in (%d, %d)", Constant.REASON_SMS, Constant.REASON_CARD);

        if (!BlahBlahUtil.emptyString(loginName)) {
            sql += " AND loginName = '{loginName}'".replace("{loginName}", BlahBlahUtil.escapseString(loginName));
        } else if (!BlahBlahUtil.emptyString(username)) {
            sql += " AND name = '{name}'".replace("{name}", BlahBlahUtil.escapseString(username));
        }

        if (!BlahBlahUtil.emptyString(reason)) {
            sql += " AND reason = " + BlahBlahUtil.escapseNumber(reason);
        }

        sql += ") AS tmp";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();
            rs.next();

            int count = rs.getInt(1);

            rs.close();
            psmt.close();

            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return 0;
    }


    public static List<UserMoneyChange> getMoneyChangesByPage(long userId, int page, int pageSize) {
        List<UserMoneyChange> logs = new ArrayList<UserMoneyChange>();
        String sql = ""
                + "SELECT * \n"
                + "FROM\n"
                + "    (SELECT XELogMoneyHistory.*, XELogMoneyHistoryReason.name AS reasonName, ROW_NUMBER() OVER (ORDER BY XELogMoneyHistory.modifyDate ASC) as row \n"
                + "    FROM XELogMoneyHistory, XELogMoneyHistoryReason "
                + "    WHERE XELogMoneyHistory.reason = XELogMoneyHistoryReason.id "
//                + "        AND DATEDIFF(second, '2014-07-19 10:26:02', XELogMoneyHistory.modifyDate) >= 0 "
//                + "        AND DATEDIFF(second, XELogMoneyHistory.modifyDate, '2014-07-20 00:00:00') >= 0"
                + "        AND userId = {userId}".replace("{userId}", String.valueOf(userId));
        sql += ") AS tmp\n"
                + "WHERE row >= ? AND row <= ?\n"
                + "";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, (page - 1) * pageSize + 1);
            psmt.setInt(2, page * pageSize);

            ResultSet rs = psmt.executeQuery();

            logs = BlahBlahUtil.getResultSetAsList(rs, UserMoneyChange.class);

            rs.close();
            psmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return logs;
    }

    public static int getMoneyChangesCount(long userId) {
        List<UserMoneyChange> logs = new ArrayList<UserMoneyChange>();
        String sql = ""
                + "SELECT COUNT(*) \n"
                + "FROM XELogMoneyHistory, XELogMoneyHistoryReason "
                + "    WHERE XELogMoneyHistory.reason = XELogMoneyHistoryReason.id "
                + "        AND userId = {userId}".replace("{userId}", String.valueOf(userId));

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            rs.close();
            psmt.close();

            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return 0;
    }


    public static List<LogMoneyHistory> getAllModifyMoneyLog(String loginName, String info, String moneyType) {
        return getAllModifyMoneyLog(1, loginName, info, moneyType);
    }

    public static List<LogMoneyHistory> getAllModifyMoneyLog(int page, String loginName, String reason, String moneyType) {
        List<LogMoneyHistory> logs = new ArrayList<LogMoneyHistory>();
        String sql = ""
                + "SELECT * \n"
                + "FROM\n"
                + "    (SELECT XELogMoneyHistory.*, workinguser.loginName, reportuser.name AS adminName, ROW_NUMBER() OVER (ORDER BY id DESC) as row \n"
                + "    FROM XELogMoneyHistory, workinguser, reportuser "
                + "    WHERE XELogMoneyHistory.userId = workinguser.userId" +
                "          AND XELogMoneyHistory.refId = reportuser.Userid" +
                String.format("          AND reason = %d", Constant.REASON_REPORT_TOOL);

        if (!BlahBlahUtil.emptyString(loginName)) {
            sql += " AND loginName LIKE '%{loginName}%'".replace("{loginName}", BlahBlahUtil.escapseString(loginName));
        }

        if (moneyType.trim().length() != 0) {
            sql += " AND moneyType = {moneyType}".replace("{moneyType}", BlahBlahUtil.escapseString(moneyType));
        }

        sql += ") AS tmp\n"
                + "WHERE row >= ? AND row <= ?\n"
                + "";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, (page - 1) * Constant.PAGE_SIZE + 1);
            psmt.setInt(2, page * Constant.PAGE_SIZE);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                LogMoneyHistory log = new LogMoneyHistory();
                log.setId(rs.getInt("id"));
                log.setMessage(rs.getString("message"));
                log.setModifyDate(rs.getTimestamp("modifyDate"));
                log.setMoneyAfter(rs.getLong("moneyAfter"));
                log.setMoneyBefore(rs.getLong("moneyBefore"));
                log.setMoneyDiff(rs.getLong("moneyDiff"));
                log.setMoneyType(rs.getInt("moneyType"));
                log.setReason(rs.getInt("reason"));
                log.setUserId(rs.getLong("userId"));
                log.setUsername(rs.getString("loginName"));
                log.setRefId(rs.getString("refId"));
                log.setRefName(rs.getString("adminName"));

                logs.add(log);
            }
            rs.close();
            psmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return logs;
    }

    public static int getNumOfModifyMoneyLog(String loginName, String reason, String moneyType) {
        List<LogMoneyHistory> logs = new ArrayList<LogMoneyHistory>();
        String sql = ""
                + "SELECT COUNT(*) "
                + "FROM XELogMoneyHistory, workinguser "
                + "WHERE XELogMoneyHistory.userId = workinguser.userId" +
                String.format("          AND reason = %d", Constant.REASON_REPORT_TOOL);

        if (!BlahBlahUtil.emptyString(loginName)) {
            sql += " AND loginName LIKE '%{loginName}%'".replace("{loginName}", BlahBlahUtil.escapseString(loginName));
        }

        if (moneyType.trim().length() != 0) {
            sql += " AND moneyType = {moneyType}".replace("{moneyType}", BlahBlahUtil.escapseString(moneyType));
        }

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();
            rs.next();

            int count = rs.getInt(1);

            rs.close();
            psmt.close();

            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return 0;
    }

    public static List<ExchangeLog> getAllExchangeLog(int page, String loginName, int numOfXeeng) {
        List<ExchangeLog> logs = new ArrayList<ExchangeLog>();
        String sql = ""
                + "SELECT * \n"
                + "FROM\n"
                + "    (SELECT XELogMoneyHistory.*, workinguser.loginName, ROW_NUMBER() OVER (ORDER BY id DESC) as row \n"
                + "    FROM XELogMoneyHistory, workinguser "
                + "    WHERE XELogMoneyHistory.userId = workinguser.userId " +
                "        AND moneyType = 1 " +
                String.format("          AND reason = %d", Constant.REASON_EXCHANGE);

        if (!BlahBlahUtil.emptyString(loginName)) {
            sql += " AND loginName LIKE '%{loginName}%'".replace("{loginName}", BlahBlahUtil.escapseString(loginName));
        }

        if (numOfXeeng != -1) {
            sql += String.format(" AND ABS(moneyDiff) = %d ", numOfXeeng);
        }

        sql += ") AS tmp\n"
                + "WHERE row >= ? AND row <= ?\n"
                + "";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, (page - 1) * Constant.PAGE_SIZE + 1);
            psmt.setInt(2, page * Constant.PAGE_SIZE);

            ResultSet rs = psmt.executeQuery();
            logs = BlahBlahUtil.getResultSetAsList(rs, ExchangeLog.class);
            rs.close();
            psmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return logs;
    }

    public static int getNumOfExchangeLog(String loginName, int numOfXeeng) {
        List<LogMoneyHistory> logs = new ArrayList<LogMoneyHistory>();
        String sql = ""
                + "SELECT COUNT(*) "
                + "FROM XELogMoneyHistory, workinguser "
                + "WHERE XELogMoneyHistory.userId = workinguser.userId" +
                "        AND moneyType = 1 " +
                String.format("          AND reason = %d", Constant.REASON_EXCHANGE);

        if (!BlahBlahUtil.emptyString(loginName)) {
            sql += " AND loginName LIKE '%{loginName}%'".replace("{loginName}", BlahBlahUtil.escapseString(loginName));
        }

        if (numOfXeeng != -1) {
            sql += String.format(" AND ABS(moneyDiff) = %d ", numOfXeeng);
        }


        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();
            rs.next();

            int count = rs.getInt(1);

            rs.close();
            psmt.close();

            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return 0;
    }

    public static long getXeengInWorkingUsers() {
        long totalXeeng = 0;

        String query = "" +
                "SELECT SUM(xeeng) AS totalXeeng \n" +
                "FROM workinguser";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(query);

            ResultSet rs = psmt.executeQuery();
            if (rs != null && rs.next()) {
                totalXeeng = rs.getLong("totalXeeng");
            }

            rs.close();
            psmt.close();
        } catch (SQLException ex) {
            totalXeeng = -1;

            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return totalXeeng;
    }

    public static Map<String, Long> getXeengInByDay(int reason, Date dateStart, Date dateEnd) {
        Map<String, Long> map = new HashMap<String, Long>();

        String query = "{call uspXEGetXeengInByDay(?, ?, ?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.setInt(1, reason);
            cs.setDate(2, new java.sql.Date(dateStart.getTime()));
            cs.setDate(3, new java.sql.Date(dateEnd.getTime()));

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("dayString"), rs.getLong("money"));
            }

            cs.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return map;
    }

    public static List<MoneyByDayAIO> getXeengInByDayAIO(String dayStart, String dayEnd) {
        List<MoneyByDayAIO> moneyByDayAIOs = new ArrayList<MoneyByDayAIO>();

        Date dateStart = BlahBlahUtil.getDateTime(dayStart);
        Date dateEnd = BlahBlahUtil.getDateTime(dayEnd);

        Map<String, Long> mapReportTool = getXeengInByDay(Constant.REASON_REPORT_TOOL, dateStart, dateEnd);
        Map<String, Long> mapSMS = getXeengInByDay(Constant.REASON_SMS, dateStart, dateEnd);
        Map<String, Long> mapCard = getXeengInByDay(Constant.REASON_CARD, dateStart, dateEnd);

        Calendar start = Calendar.getInstance();
        start.setTime(dateStart);

        Calendar end = Calendar.getInstance();
        end.setTime(dateEnd);

        while (!start.after(end)) {
            String currentDate = BlahBlahUtil.getDateTimeString(start.getTime());

            MoneyByDayAIO moneyByDay = new MoneyByDayAIO();
            moneyByDay.setDay(currentDate);

            if (mapReportTool.get(currentDate) == null) {
                moneyByDay.getMoney().add(Long.valueOf(0));
            } else {
                moneyByDay.getMoney().add(mapReportTool.get(currentDate));
            }

            if (mapSMS.get(currentDate) == null) {
                moneyByDay.getMoney().add(Long.valueOf(0));
            } else {
                moneyByDay.getMoney().add(mapSMS.get(currentDate));
            }

            if (mapCard.get(currentDate) == null) {
                moneyByDay.getMoney().add(Long.valueOf(0));
            } else {
                moneyByDay.getMoney().add(mapCard.get(currentDate));
            }

            moneyByDayAIOs.add(moneyByDay);

            start.add(Calendar.DATE, 1);
        }

        return moneyByDayAIOs;
    }

    public static Map<String, Long> getXeengOutByDay(int reason, Date dateStart, Date dateEnd) {
        Map<String, Long> map = new HashMap<String, Long>();

        String query = "{call uspXEGetXeengOutByDay(?, ?, ?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.setInt(1, reason);
            cs.setDate(2, new java.sql.Date(dateStart.getTime()));
            cs.setDate(3, new java.sql.Date(dateEnd.getTime()));

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("dayString"), rs.getLong("money"));
            }

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return map;
    }

    public static List<MoneyByDayAIO> getXeengOutByDayAIO(String dayStart, String dayEnd) {
        List<MoneyByDayAIO> moneyByDayAIOs = new ArrayList<MoneyByDayAIO>();

        Date dateStart = BlahBlahUtil.getDateTime(dayStart);
        Date dateEnd = BlahBlahUtil.getDateTime(dayEnd);

        Map<String, Long> mapReportTool = getXeengOutByDay(Constant.REASON_REPORT_TOOL, dateStart, dateEnd);
        Map<String, Long> mapExchange = getXeengOutByDay(Constant.REASON_EXCHANGE, dateStart, dateEnd);

        Calendar start = Calendar.getInstance();
        start.setTime(dateStart);

        Calendar end = Calendar.getInstance();
        end.setTime(dateEnd);

        while (!start.after(end)) {
            String currentDate = BlahBlahUtil.getDateTimeString(start.getTime());

            MoneyByDayAIO moneyByDay = new MoneyByDayAIO();
            moneyByDay.setDay(currentDate);

            if (mapReportTool.get(currentDate) == null) {
                moneyByDay.getMoney().add(Long.valueOf(0));
            } else {
                moneyByDay.getMoney().add(mapReportTool.get(currentDate));
            }

            if (mapExchange.get(currentDate) == null) {
                moneyByDay.getMoney().add(Long.valueOf(0));
            } else {
                moneyByDay.getMoney().add(mapExchange.get(currentDate));
            }

            moneyByDayAIOs.add(moneyByDay);

            start.add(Calendar.DATE, 1);
        }

        return moneyByDayAIOs;
    }

    public static List<StatisticsByDay> getMoneyStatistics(Date dateStart, Date dateEnd) {
        List<StatisticsByDay> moneys = null;

        String query = "{call uspXEGetDailyStatistics(?, ?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setDate(1, new java.sql.Date(dateStart.getTime()));
            cs.setDate(2, new java.sql.Date(dateEnd.getTime()));

            ResultSet rs = cs.executeQuery();
            moneys = BlahBlahUtil.getResultSetAsList(rs, StatisticsByDay.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return moneys;
    }


    public static List<ShopDailyStatistics> getShopDailyStatistics(Date dateStart, Date dateEnd) {
        // danh sách bản ghi, mỗi bản ghi chứa thông tin của 1 item trong 1 ngày
        List<ShopItemDailyStatistics> itemStat = getShopItemDailyStatistics(dateStart, dateEnd);

        // hashmap có cấu trúc: map[reportDate][itemId] = itemCount
        LinkedHashMap<String, LinkedHashMap<Integer, Integer>> map = new LinkedHashMap<String, LinkedHashMap<Integer, Integer>>();

        if (itemStat == null) {
            return null;
        }

        for (ShopItemDailyStatistics item : itemStat) {
            if (map.get(item.getReportDate()) == null) {
                map.put(item.getReportDate(), new LinkedHashMap<Integer, Integer>());
            }

            map.get(item.getReportDate()).put(item.getItemId(), item.getItemCount());

            // với totalXeeng và totalGold, coi price là count để thống nhất khi lấy dữ liệu
            if (item.getItemId() == ShopDailyStatistics.TOTAL_XEENG_ID
                    || item.getItemId() == ShopDailyStatistics.TOTAL_GOLD_ID) {
                map.get(item.getReportDate()).put(item.getItemId(), item.getItemPrice());
            }
        }

        // danh sách item trong shop
        List<ShopItem> shopItems = getAllShopItem();

        // chuẩn hóa
        List<ShopDailyStatistics> shopStat = new ArrayList<ShopDailyStatistics>();
        for (Map.Entry<String, LinkedHashMap<Integer, Integer>> entry : map.entrySet()) {
            // duyệt từng ngày

            ShopDailyStatistics dayStat = new ShopDailyStatistics();
            dayStat.setReportDate(entry.getKey());

            // tạo danh sách count của từng item
            HashMap<Integer, Integer> dayCounts = entry.getValue();
            for (ShopItem shopItem : shopItems) {
                if (dayCounts.containsKey(shopItem.getId())) {
                    dayStat.getItemCounts().add(dayCounts.get(shopItem.getId()));
                } else {
                    dayStat.getItemCounts().add(0);
                }
            }
            if (dayCounts.containsKey(ShopDailyStatistics.TOTAL_XEENG_ID)) {
                dayStat.setTotalXeeng(dayCounts.get(ShopDailyStatistics.TOTAL_XEENG_ID));
            }
            if (dayCounts.containsKey(ShopDailyStatistics.TOTAL_GOLD_ID)) {
                dayStat.setTotalXeeng(dayCounts.get(ShopDailyStatistics.TOTAL_GOLD_ID));
            }

            shopStat.add(dayStat);
        }

        return shopStat;
    }

    public static List<ShopItemDailyStatistics> getShopItemDailyStatistics(Date dateStart, Date dateEnd) {
        List<ShopItemDailyStatistics> items = null;

        String query = "{call uspXEGetShopDailyStatistics(?, ?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setDate(1, new java.sql.Date(dateStart.getTime()));
            cs.setDate(2, new java.sql.Date(dateEnd.getTime()));

            ResultSet rs = cs.executeQuery();
            items = BlahBlahUtil.getResultSetAsList(rs, ShopItemDailyStatistics.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return items;
    }

    public static List<ShopItem> getAllShopItem() {
        List<ShopItem> items = null;

        String query = "{call uspXEGetAllShopItems() }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            ResultSet rs = cs.executeQuery();
            items = BlahBlahUtil.getResultSetAsList(rs, ShopItem.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return items;
    }

    /**
     * Lấy danh sách số lượng người chơi trả tiền theo ngày (distinct).
     *
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public static List<ChargingDailyStatistics> getChargingStatistics(Date dateStart, Date dateEnd) {
        List<ChargingDailyStatistics> stat = null;

        String query = "{call uspXEGetChargingDailyStatistics(?, ?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setDate(1, new java.sql.Date(dateStart.getTime()));
            cs.setDate(2, new java.sql.Date(dateEnd.getTime()));

            ResultSet rs = cs.executeQuery();
            stat = BlahBlahUtil.getResultSetAsList(rs, ChargingDailyStatistics.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return stat;
    }

    /**
     * Lấy danh sách số lượng SMS được nạp theo mức tiền.
     *
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public static List<SMSStatistics> getSMSStatistics(Date month) {
        List<SMSStatistics> items = null;

        String query = "{call uspXEGetMonthlySMSStatistics(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setDate(1, new java.sql.Date(month.getTime()));

            ResultSet rs = cs.executeQuery();
            items = BlahBlahUtil.getResultSetAsList(rs, SMSStatistics.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return items;
    }

    /**
     * Lấy danh sách số lượng Card được nạp theo mức tiền.
     *
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public static List<CardStatistics> getCardStatistics(Date month) {
        List<CardStatistics> items = null;

        String query = "{call uspXEGetMonthlyCardStatistics(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setDate(1, new java.sql.Date(month.getTime()));

            ResultSet rs = cs.executeQuery();
            items = BlahBlahUtil.getResultSetAsList(rs, CardStatistics.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return items;
    }

    /**
     * Lấy số lượng SMS được nạp theo mức tiền
     *
     * @param month
     * @return
     */
    public static List<Integer> getMonthlySMSStatistics(Date month) {
        // danh sách bản ghi, mỗi bản ghi chứa thông tin của 1 item trong 1 ngày
        List<SMSStatistics> itemStat = getSMSStatistics(month);

        // hashmap có cấu trúc: map[itemMoney] = itemCount
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

//        if (itemStat == null) {
//            return null;
//        }

        if (itemStat != null) {
            for (SMSStatistics item : itemStat) {
                if (map.get(item.getItemMoney()) == null) {
                    map.put(item.getItemMoney(), item.getItemCount());
                }
            }
        }

        // chuẩn hóa
        List<Integer> itemCounts = new ArrayList<Integer>();
        for (int smsValue : SMS_VALUES) {
            if (map.containsKey(smsValue)) {
                itemCounts.add(map.get(smsValue));
            } else {
                itemCounts.add(0);
            }
        }

        return itemCounts;
    }


    /**
     * Lấy số lượng Card được nạp theo mức tiền
     *
     * @param month
     * @return
     */
    public static List<Integer> getMonthlyCardStatistics(Date month) {
        // danh sách bản ghi, mỗi bản ghi chứa thông tin của 1 item trong 1 ngày
        List<CardStatistics> itemStat = getCardStatistics(month);

        // hashmap có cấu trúc: map[itemMoney] = itemCount
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

//        if (itemStat == null) {
//            return null;
//        }

        if (itemStat != null) {
            for (CardStatistics item : itemStat) {
                if (map.get(item.getItemMoney()) == null) {
                    map.put(item.getItemMoney(), item.getItemCount());
                }
            }
        }

        // chuẩn hóa
        List<Integer> itemCounts = new ArrayList<Integer>();
        for (int cardValue : CARD_VALUES) {
            if (map.containsKey(cardValue)) {
                itemCounts.add(map.get(cardValue));
            } else {
                itemCounts.add(0);
            }
        }

        return itemCounts;
    }


    public static int getNumOfUniquePayer(Date month) {
        String query = "{call uspXEGetNumOfUniquePayer(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setTimestamp(1, new Timestamp(month.getTime()));

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return 0;
    }

    public static List<NumOfPayerByDay> getNumOfUniquePayerByDay(Date month) {
        List<NumOfPayerByDay> items = null;

        String query = "{call uspXEGetNumOfUniquePayerByDay(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setTimestamp(1, new Timestamp(month.getTime()));

            ResultSet rs = cs.executeQuery();

            items = BlahBlahUtil.getResultSetAsList(rs, NumOfPayerByDay.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return items;
    }

    public static List<NumOfUserOnlineBean> getNumOfUserOnlineBy5Minutes(Date day) {
        List<CardStatistics> items = null;

        String query = "{call uspXEGetNumOfUserOnlineBy5Minutes(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.setTimestamp(1, new Timestamp(day.getTime()));

            ResultSet rs = cs.executeQuery();
            List<NumOfUserOnlineBean> nums = BlahBlahUtil.getResultSetAsList(rs, NumOfUserOnlineBean.class);

            cs.close();

            return nums;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return null;
    }

    public static List<NumOfUserOnlineBean> getNumOfUserOnlineByHour(Date day) {
        List<CardStatistics> items = null;

        String query = "{call uspXEGetNumOfUserOnlineByHour(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.setTimestamp(1, new Timestamp(day.getTime()));

            ResultSet rs = cs.executeQuery();
            List<NumOfUserOnlineBean> nums = BlahBlahUtil.getResultSetAsList(rs, NumOfUserOnlineBean.class);

            cs.close();

            return nums;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return null;
    }

    public static List<NumOfUserOnlineBean> getNumOfUserOnlineByDay(Date day) {
        List<CardStatistics> items = null;

        String query = "{call uspXEGetNumOfUserOnlineByDay(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.setTimestamp(1, new Timestamp(day.getTime()));

            ResultSet rs = cs.executeQuery();
            List<NumOfUserOnlineBean> nums = BlahBlahUtil.getResultSetAsList(rs, NumOfUserOnlineBean.class);

            cs.close();

            return nums;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return null;
    }

    public static List<NumOfUserOnlineBean> getMaxNumOfUserOnlineByHour(Date day) {
        List<CardStatistics> items = null;

        String query = "{call uspXEGetMaxNumOfUserOnlineByHour(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.setTimestamp(1, new Timestamp(day.getTime()));

            ResultSet rs = cs.executeQuery();
            List<NumOfUserOnlineBean> nums = BlahBlahUtil.getResultSetAsList(rs, NumOfUserOnlineBean.class);

            cs.close();

            return nums;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return null;
    }

    public static List<NumOfUserOnlineBean> getMaxNumOfUserOnlineByDay(Date day) {
        List<CardStatistics> items = null;

        String query = "{call uspXEGetMaxNumOfUserOnlineByDay(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.setTimestamp(1, new Timestamp(day.getTime()));

            ResultSet rs = cs.executeQuery();
            List<NumOfUserOnlineBean> nums = BlahBlahUtil.getResultSetAsList(rs, NumOfUserOnlineBean.class);

            cs.close();

            return nums;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return null;
    }

    public static List<ReportUserLog> getReportUserLogByPage(int page, int pageSize, String username) {
        Map<String, Long> map = new HashMap<String, Long>();

        String query = "{call uspXEGetReportUserLog(?, ?, ?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.setInt(1, page);
            cs.setInt(2, pageSize);
            cs.setString(3, username);

            ResultSet rs = cs.executeQuery();

            List<ReportUserLog> logs = BlahBlahUtil.getResultSetAsList(rs, ReportUserLog.class);

            cs.close();

            return logs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return null;
    }

    public static int getNumOfReportUserLog(String username) {
        int result = 0;

        String query = "{? = call uspXEGetNumOfReportUserLog(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, username);

            boolean success = cs.execute();
            result = cs.getInt(1);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

            result = 0;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return result;
    }

    public static List<DeviceStatistics> getDeviceStatistics(Date month) {
        Map<String, Long> map = new HashMap<String, Long>();

        String query = "{call uspXEGetDeviceStatistics(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);
            cs.setDate(1, new java.sql.Date(month.getTime()));

            ResultSet rs = cs.executeQuery();

            List<DeviceStatistics> logs = BlahBlahUtil.getResultSetAsList(rs, DeviceStatistics.class);

            cs.close();

            return logs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return null;
    }

    /**
     * Thống kê số lượng thiết bị theo OS (từng ngày trong tháng)
     *
     * @param month
     * @return
     */
    public static List<NumOfUserDeviceByDay> getNumOfUserDeviceByDay(Date month) {
        List<NumOfUserDeviceByDay> records = new ArrayList<>();

        String query = "{call uspXEGetNumOfUserDeviceByDay(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setTimestamp(1, new Timestamp(month.getTime()));

            ResultSet rs = cs.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                NumOfUserDeviceByDay record = new NumOfUserDeviceByDay();
                record.setReportDate(rs.getTimestamp("reportDate"));

                for (int i = 2; i <= meta.getColumnCount(); i++) {
                    String deviceName = meta.getColumnName(i);
                    int num = rs.getInt(deviceName);

                    UserDeviceCount item = new UserDeviceCount(deviceName, num);
                    record.getDeviceCounts().add(item);
                }

                records.add(record);
            }

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return records;
    }

    public static List<UserOSCount> getNumOfUserDeviceByMonth(Date month) {
        List<UserOSCount> records = new ArrayList<>();

        String query = "{call uspXEGetNumOfUserDeviceByMonth(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setTimestamp(1, new Timestamp(month.getTime()));

            ResultSet rs = cs.executeQuery();
            records = BlahBlahUtil.getResultSetAsList(rs, UserOSCount.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return records;
    }

    public static List<Campaign> getAllCampaigns() {
        List<Campaign> records = null;

        String query = "{call uspXEGetAllCampaigns() }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            ResultSet rs = cs.executeQuery();
            records = BlahBlahUtil.getResultSetAsList(rs, Campaign.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return records;
    }


    public static List<CampaignOSCountRecord> getCampaignOSCountRecords(Date dateStart, Date dateEnd) {
        List<CampaignOSCountRecord> records = null;

        String query = "{call uspXEGetCampaignStatistics(?, ?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setDate(1, new java.sql.Date(dateStart.getTime()));
            cs.setDate(2, new java.sql.Date(dateEnd.getTime()));

            ResultSet rs = cs.executeQuery();
            records = BlahBlahUtil.getResultSetAsList(rs, CampaignOSCountRecord.class);

            cs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {

            }
        }

        return records;
    }

    public static List<CampaignStatistics> getCampaignStatistics(Date dateStart, Date dateEnd) {
        // danh sách bản ghi, mỗi bản ghi chứa thông tin của 1 item trong 1 ngày
        List<CampaignOSCountRecord> records = getCampaignOSCountRecords(dateStart, dateEnd);

        // hashmap có cấu trúc: map[campaign][osName] = osCount
        LinkedHashMap<String, LinkedHashMap<String, Integer>> map = new LinkedHashMap<String, LinkedHashMap<String, Integer>>();

        if (records == null) {
            return null;
        }

        for (CampaignOSCountRecord record : records) {
            if (map.get(record.getCampaignName()) == null) {
                // nếu là bản ghi đầu tiên của campaign thì tạo mới HashMap
                map.put(record.getCampaignName(), new LinkedHashMap<String, Integer>());
            }

            // thêm bản ghi vào HashMap của campaign
            map.get(record.getCampaignName()).put(record.getOsName(), record.getOsCount());
        }

        // chuẩn hóa
        List<CampaignStatistics> campaignStats = new ArrayList<CampaignStatistics>();
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : map.entrySet()) {
            // với từng campaign

            CampaignStatistics campaignStat = new CampaignStatistics();
            campaignStat.setCampaignName(entry.getKey());

            // danh sách osCount của từng campaign
//            List<Campaign> campaigns = getAllCampaigns();
            HashMap<String, Integer> mapOSCount = entry.getValue();

            for (String osName : CAMPAIGN_OS) {
                if (mapOSCount.containsKey(osName)) {
                    campaignStat.getOsCounts().add(new CampaignOSCount(osName,
                            mapOSCount.get(osName)));
                } else {
                    campaignStat.getOsCounts().add(new CampaignOSCount(osName,
                            0));
                }
            }

            campaignStats.add(campaignStat);
        }

        return campaignStats;
    }
}
