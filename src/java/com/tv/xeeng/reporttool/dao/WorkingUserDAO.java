package com.tv.xeeng.reporttool.dao;

import com.tv.xeeng.reporttool.beans.Partner;
import com.tv.xeeng.reporttool.beans.UserBean;
import com.tv.xeeng.reporttool.beans.WorkingUserBean;
import com.tv.xeeng.reporttool.util.CSVUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingUserDAO {

    public List<WorkingUserBean> getAll(int pageCurrent, int partnerId, String name, String nickName, long userId,
                                        int refCode, int orderType, String fromdate, String todate) {
        List<WorkingUserBean> wUList = new ArrayList<WorkingUserBean>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        int rowNumDisplay = 20;
        int rowIdFirst = ((pageCurrent - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;

        String search = " WHERE WorkingUser.partnerId = partner.partnerId ";

        if (partnerId > 0) {
            search += " AND WorkingUser.partnerID = " + partnerId;
        }

        fromdate = fromdate + " 00:00:00";
        todate = todate + " 23:59:59";

        String orderBy = "";
        if (orderType == 1) {
            orderBy = "order by userId DESC";

            if (refCode > 0) {
                search += " AND refCode = '" + refCode + "'";
            }

            if (name != null && !"".equals(name.trim())) {
                search += " AND loginname like '%" + name + "%'";
            }
            if (nickName != null && !"".equals(nickName.trim())) {
                search += " AND WorkingUser.name like '%" + nickName + "%'";
            }

            if (userId > 0) {
                search += " AND userId = " + userId;
            }

        } else if (orderType == 2) {
            if (fromdate != null && todate != null) {
                search += " AND registerDate >= '" + fromdate + "' AND registerDate <= '" + todate + "'";
            } else {
                search += " AND datediff(DAY, registerDate, GETDATE()) = 1";
            }
            orderBy = "order by registerDate DESC";
        } else if (orderType == 3) {
            if (fromdate != null && todate != null) {
                search += " AND lastLogin >= '" + fromdate + "' AND lastLogin <= '" + todate + "'";
            } else {
                search += " AND datediff(DAY, lastLogin, GETDATE()) = 1";

            }
            orderBy = "order by lastLogin DESC";
        } else if (orderType == 4) {
            if (fromdate != null && todate != null) {
                search += " AND lastSecondLogin >= '" + fromdate + "' AND lastSecondLogin <= '" + todate + "'";
            } else {
                search += " AND datediff(DAY, lastSecondLogin, GETDATE()) = 1";

            }
            orderBy = "order by lastSecondLogin DESC";
        }

        String sql1 = "SELECT COUNT(*) FROM WorkingUser, partner " + search;
        String sql2 = "select * from  (select WorkingUser.*, partner.name AS partnerName, row_number() over (" + orderBy + ") as r from WorkingUser, partner " + search + ") data_row where r >=? and r <= ?";

        System.out.println("sql " + sql1);

        try {
            conn = DBPoolConnection.getConnection();
            psmt = conn.prepareStatement(sql1);
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
                    psmt = conn.prepareStatement(sql2);
                    psmt.setInt(1, rowIdFirst);
                    psmt.setInt(2, rowIdLast);
                    rs = psmt.executeQuery();

                    while (rs.next()) {
                        WorkingUserBean wUBean = new WorkingUserBean();
                        wUBean.setUserId(rs.getInt(1));
                        String nickname = rs.getString(2);
                        if (nickname.length() > 15) {
                            wUBean.setName(nickname.substring(0, 14) + "..");
                        } else {
                            wUBean.setName(nickname);
                        }
                        wUBean.setPassword(rs.getString(3));
                        wUBean.setPhonenumber(rs.getString(4));
                        wUBean.setSessionId(rs.getString(5));
                        wUBean.setWonPlayNumber(rs.getInt(6));
                        wUBean.setCash(rs.getInt(7));
                        wUBean.setXeeng(rs.getInt("Xeeng"));
                        wUBean.setLockExpired(rs.getTimestamp("lockExpired"));
                        wUBean.setChatLockExpired(rs.getTimestamp("chatLockExpired"));
                        wUBean.setPlayNumber(rs.getInt(8));
                        wUBean.setLevel(rs.getInt(9));
                        wUBean.setExperience(rs.getInt(10));
                        wUBean.setVipId(rs.getInt(11));
                        boolean isOnline = rs.getBoolean(12);
                        if (isOnline) {
                            wUBean.setStateOnline("Online");
                        } else {
                            wUBean.setStateOnline("Offline");
                        }
                        wUBean.setAvartaId(rs.getInt(13));
                        wUBean.setLastLongin(rs.getTimestamp(14));
                        wUBean.setEmail(rs.getString(15));
                        wUBean.setStateActive(rs.getBoolean(16));
                        wUBean.setPartnerId(rs.getInt(17));
                        wUBean.setPartner(rs.getString("partnerName"));
                        wUBean.setAvartaFileId(rs.getInt(18));
                        wUBean.setBiaFileId(rs.getInt(19));
                        wUBean.setSex(rs.getInt(20));
                        wUBean.setTimeQuay(rs.getInt(21));
                        wUBean.setStatus(rs.getString(22));
                        wUBean.setMobileVersion(rs.getString(23));
                        wUBean.setRegisterDate(rs.getTimestamp(24));
                        wUBean.setFromDevice(rs.getString(25));
                        wUBean.setRefCode(rs.getString(26));
                        wUBean.setGameVersion(rs.getString(27));
                        wUBean.setLoginName(rs.getString(29));
                        wUBean.setCmnd(rs.getString(30));
                        wUBean.setLastSecondLogin(rs.getTimestamp(33));
                        wUBean.setTotalPage(totalPage);
                        wUBean.setTotalRecord(totalRc);

                        wUList.add(wUBean);
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
        return wUList;
    }

    public String exportData(int partnerId, String name, String nickName, long userId,
                             int refCode, int orderType, String fromdate, String todate) {

        List<WorkingUserBean> wUList = new ArrayList<WorkingUserBean>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        String search = " WHERE 1 = 1 ";

        if (partnerId > 0) {
            search += " AND partnerID = " + partnerId;
        }

        fromdate = fromdate + " 00:00:00";
        todate = todate + " 23:59:59";

        String orderBy = "";
        if (orderType == 1) {
            orderBy = "order by userId DESC";

            if (refCode > 0) {
                search += " AND refCode = '" + refCode + "'";
            }

            if (name != null && !"".equals(name.trim())) {
                search += " AND loginname like '%" + name + "%'";
            }
            if (nickName != null && !"".equals(nickName.trim())) {
                search += " AND name like '%" + nickName + "%'";
            }

            if (userId > 0) {
                search += " AND userId = " + userId;
            }

        } else if (orderType == 2) {
            if (fromdate != null && todate != null) {
                search += " AND registerDate >= '" + fromdate + "' AND registerDate <= '" + todate + "'";
            } else {
                search += " AND datediff(DAY, registerDate, GETDATE()) = 1";
            }
            orderBy = "order by registerDate DESC";
        } else if (orderType == 3) {
            if (fromdate != null && todate != null) {
                search += " AND lastLogin >= '" + fromdate + "' AND lastLogin <= '" + todate + "'";
            } else {
                search += " AND datediff(DAY, lastLogin, GETDATE()) = 1";

            }
            orderBy = "order by lastLogin DESC";
        } else if (orderType == 4) {
            if (fromdate != null && todate != null) {
                search += " AND lastSecondLogin >= '" + fromdate + "' AND lastSecondLogin <= '" + todate + "'";
            } else {
                search += " AND datediff(DAY, lastSecondLogin, GETDATE()) = 1";

            }
            orderBy = "order by lastSecondLogin DESC";
        }

        String sql = "Select * from WorkingUser " + search + " " + orderBy;

        System.out.println("sql " + sql);

        StringBuffer buf = new StringBuffer();
        String COMMA = ",";

        try {
            conn = DBPoolConnection.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                buf.append(CSVUtils.toCSV(rs.getString(1)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(2)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(29)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(7)) + COMMA);
                boolean isOnline = rs.getBoolean(12);
                if (isOnline) {
                    buf.append("Online" + COMMA);
                } else {
                    buf.append("Offline" + COMMA);
                }
                buf.append(CSVUtils.toCSV(rs.getString(24)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(26)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(17)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(14)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(33)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(30)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(4)) + COMMA);
                buf.append(CSVUtils.toCSV(rs.getString(20)));
                buf.append("\r\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return buf.toString();
    }

    public int changePass(int userId, String newPass) {
        int result = 0;
        String sql = "UPDATE WorkingUser SET password = ? WHERE userId = ?";
        try {
            Connection conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, newPass);
            psmt.setInt(2, userId);

            result = psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public boolean lockUser(int userId, int numOfDay, UserBean loggedInUser) {
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call  uspXELockUser(?, ?, ?) }";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, userId);
            cs.setInt(2, numOfDay);
            cs.setInt(3, (int)loggedInUser.getId());

            cs.executeUpdate();

            cs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean unlockUser(int userId, UserBean loggedInUser) {
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call  uspXEUnlockUser(?, ?) }";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, userId);
            cs.setInt(2, (int)loggedInUser.getId());

            cs.executeUpdate();

            cs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean lockChatUser(int userId, int numOfDay, UserBean loggedInUser) {
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call  uspXELockChatUser(?, ?, ?) }";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, userId);
            cs.setInt(2, numOfDay);
            cs.setInt(3, (int)loggedInUser.getId());

            cs.executeUpdate();

            cs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean unlockChatUser(int userId, UserBean loggedInUser) {
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call  uspXEUnlockChatUser(?, ?) }";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, userId);
            cs.setInt(2, (int)loggedInUser.getId());

            cs.executeUpdate();

            cs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean changeUserCash(int userId, int cashDiff, String message, UserBean user) {
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call  uspXEModifyCash(?, ?, ?, ?) }";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, userId);
            psmt.setInt(2, cashDiff);
            psmt.setObject(3, message);
            psmt.setLong(4, user.getId());

            psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean changeUserXeeng(int userId, int xeengDiff, String message, UserBean user) {
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call  uspXEModifyXeeng(?, ?, ?, ?) }";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, userId);
            psmt.setInt(2, xeengDiff);
            psmt.setObject(3, message);
            psmt.setLong(4, user.getId());

            psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public WorkingUserBean getAWorkingUser(int userId, int partnerId) {
        int totalRc0 = 0;
        WorkingUserBean wuBean = new WorkingUserBean();
        String sql0 = "SELECT QuestionId = COUNT(*) FROM WorkingUser";
        String sql = "SELECT * FROM WorkingUser  WHERE userId = ?";
        if (partnerId != 0) {
            sql0 = "SELECT QuestionId = COUNT(*) FROM WorkingUser WHERE partnerId = " + partnerId;
            sql = "SELECT * FROM WorkingUser  WHERE userId = ? AND partnerId = " + partnerId;
        }
        Connection conn;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt0 = conn.prepareStatement(sql0);
            ResultSet rs0 = psmt0.executeQuery();
            while (rs0.next()) {
                totalRc0 = rs0.getInt(1);
            }
            rs0.close();
            psmt0.close();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, userId);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                wuBean.setUserId(rs.getInt(1));
                wuBean.setName(rs.getString(2));
                wuBean.setPassword(rs.getString(3));
                wuBean.setPhonenumber(rs.getString(4));
                wuBean.setSessionId(rs.getString(5));
                wuBean.setWonPlayNumber(rs.getInt(6));
                wuBean.setCash(rs.getInt(7));
                wuBean.setXeeng(rs.getInt("Xeeng"));
                wuBean.setLockExpired(rs.getTimestamp("lockExpired"));
                wuBean.setChatLockExpired(rs.getTimestamp("chatLockExpired"));
                wuBean.setPlayNumber(rs.getInt(8));
                wuBean.setLevel(rs.getInt(9));
                wuBean.setExperience(rs.getInt(10));
                wuBean.setVipId(rs.getInt(11));
                boolean isOnline = rs.getBoolean(12);
                if (isOnline) {
                    wuBean.setStateOnline("Online");
                } else {
                    wuBean.setStateOnline("Offline");
                }
                wuBean.setAvartaId(rs.getInt(13));
                wuBean.setLastLongin(rs.getTimestamp(14));
                wuBean.setEmail(rs.getString(15));
                wuBean.setStateActive(rs.getBoolean(16));
                wuBean.setPartnerId(rs.getInt(17));
                wuBean.setAvartaFileId(rs.getInt(18));
                wuBean.setBiaFileId(rs.getInt(19));
                wuBean.setSex(rs.getInt(20));
                wuBean.setTimeQuay(rs.getInt(21));
                wuBean.setStatus(rs.getString(22));
                wuBean.setMobileVersion(rs.getString(23));
                wuBean.setRegisterDate(rs.getTimestamp(24));
                wuBean.setFromDevice(rs.getString(25));
                wuBean.setRefCode(rs.getString(26));
                wuBean.setGameVersion(rs.getString(27));
                wuBean.setTotalRecord(totalRc0);

            }
            rs.close();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return wuBean;
    }

    public List<WorkingUserBean> getAWorkingUserByName(int pageCurrent, String name, int partnerId) {
        List<WorkingUserBean> wUList = new ArrayList<WorkingUserBean>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        int rowNumDisplay = 20;
        int rowIdFirst = ((pageCurrent - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;

        String sql1 = "SELECT QuestionId = COUNT(*) FROM WorkingUser WHERE name LIKE ?";
        String sql2 = "select * from  (select *,row_number() over (order by userId) as r from WorkingUser WHERE name LIKE ?) data_row where r >=? and r <= ?";
        if (partnerId != 0) {
            sql1 = "SELECT QuestionId = COUNT(*) FROM WorkingUser WHERE name LIKE ? AND partnerId=" + partnerId;
            sql2 = "select * from  (select *,row_number() over (order by userId) as r from WorkingUser WHERE name LIKE ? AND partnerId=" + partnerId + " ) data_row where r >=? and r <= ?";
        }

        try {
            conn = DBPoolConnection.getConnection();
            psmt = conn.prepareStatement(sql1);
            psmt.setString(1, "%" + name + "%");
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
                    psmt = conn.prepareStatement(sql2);
                    psmt.setString(1, "%" + name + "%");
                    psmt.setInt(2, rowIdFirst);
                    psmt.setInt(3, rowIdLast);
                    rs = psmt.executeQuery();

                    while (rs.next()) {
                        WorkingUserBean wUBean = new WorkingUserBean();
                        wUBean.setUserId(rs.getInt(1));
                        wUBean.setName(rs.getString(2));
                        wUBean.setPassword(rs.getString(3));
                        wUBean.setPhonenumber(rs.getString(4));
                        wUBean.setSessionId(rs.getString(5));
                        wUBean.setWonPlayNumber(rs.getInt(6));
                        wUBean.setCash(rs.getInt(7));
                        wUBean.setXeeng(rs.getInt("Xeeng"));
                        wUBean.setLockExpired(rs.getTimestamp("lockExpired"));
                        wUBean.setChatLockExpired(rs.getTimestamp("chatLockExpired"));
                        wUBean.setPlayNumber(rs.getInt(8));
                        wUBean.setLevel(rs.getInt(9));
                        wUBean.setExperience(rs.getInt(10));
                        wUBean.setVipId(rs.getInt(11));
                        boolean isOnline = rs.getBoolean(12);
                        if (isOnline) {
                            wUBean.setStateOnline("Online");
                        } else {
                            wUBean.setStateOnline("Offline");
                        }
                        wUBean.setAvartaId(rs.getInt(13));
                        wUBean.setLastLongin(rs.getTimestamp(14));
                        wUBean.setEmail(rs.getString(15));
                        wUBean.setStateActive(rs.getBoolean(16));
                        wUBean.setPartnerId(rs.getInt(17));
                        wUBean.setAvartaFileId(rs.getInt(18));
                        wUBean.setBiaFileId(rs.getInt(19));
                        wUBean.setSex(rs.getInt(20));
                        wUBean.setTimeQuay(rs.getInt(21));
                        wUBean.setStatus(rs.getString(22));
                        wUBean.setMobileVersion(rs.getString(23));
                        wUBean.setRegisterDate(rs.getTimestamp(24));
                        wUBean.setFromDevice(rs.getString(25));
                        wUBean.setRefCode(rs.getString(26));
                        wUBean.setGameVersion(rs.getString(27));
                        wUBean.setTotalPage(totalPage);
                        wUBean.setTotalRecord(totalRc);
                        wUBean.setNameQ(name);
                        wUList.add(wUBean);
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
        return wUList;
    }

    public WorkingUserBean getAWorkingUserByIdAndName(int userId, String name) {
        WorkingUserBean wuBean = new WorkingUserBean();
        String sql = "SELECT * FROM WorkingUser  WHERE userId = ? AND name = ?";
        Connection conn;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, userId);
            psmt.setString(2, name);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                wuBean.setUserId(rs.getInt(1));
                wuBean.setName(rs.getString(2));
                wuBean.setPassword(rs.getString(3));
                wuBean.setPhonenumber(rs.getString(4));
                wuBean.setSessionId(rs.getString(5));
                wuBean.setWonPlayNumber(rs.getInt(6));
                wuBean.setCash(rs.getInt(7));
                wuBean.setXeeng(rs.getInt("Xeeng"));
                wuBean.setLockExpired(rs.getTimestamp("lockExpired"));
                wuBean.setChatLockExpired(rs.getTimestamp("chatLockExpired"));
                wuBean.setPlayNumber(rs.getInt(8));
                wuBean.setLevel(rs.getInt(9));
                wuBean.setExperience(rs.getInt(10));
                wuBean.setVipId(rs.getInt(11));
                boolean isOnline = rs.getBoolean(12);
                if (isOnline) {
                    wuBean.setStateOnline("Online");
                } else {
                    wuBean.setStateOnline("Offline");
                }
                wuBean.setAvartaId(rs.getInt(13));
                wuBean.setLastLongin(rs.getTimestamp(14));
                wuBean.setEmail(rs.getString(15));
                wuBean.setStateActive(rs.getBoolean(16));
                wuBean.setPartnerId(rs.getInt(17));
                wuBean.setAvartaFileId(rs.getInt(18));
                wuBean.setBiaFileId(rs.getInt(19));
                wuBean.setSex(rs.getInt(20));
                wuBean.setTimeQuay(rs.getInt(21));
                wuBean.setStatus(rs.getString(22));
                wuBean.setMobileVersion(rs.getString(23));
                wuBean.setRegisterDate(rs.getTimestamp(24));
                wuBean.setFromDevice(rs.getString(25));
                wuBean.setRefCode(rs.getString(26));
                wuBean.setGameVersion(rs.getString(27));
            }
            rs.close();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return wuBean;
    }


    public static WorkingUserBean getAWorkingUserById(long userId) {
        WorkingUserBean wuBean = new WorkingUserBean();
        String sql = "SELECT * FROM WorkingUser  WHERE userId = ?";
        Connection conn;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setLong(1, userId);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                wuBean.setUserId(rs.getInt(1));
                wuBean.setName(rs.getString(2));
                wuBean.setPassword(rs.getString(3));
                wuBean.setPhonenumber(rs.getString(4));
                wuBean.setSessionId(rs.getString(5));
                wuBean.setWonPlayNumber(rs.getInt(6));
                wuBean.setCash(rs.getInt(7));
                wuBean.setXeeng(rs.getInt("Xeeng"));
                wuBean.setLockExpired(rs.getTimestamp("lockExpired"));
                wuBean.setChatLockExpired(rs.getTimestamp("chatLockExpired"));
                wuBean.setPlayNumber(rs.getInt(8));
                wuBean.setLevel(rs.getInt(9));
                wuBean.setExperience(rs.getInt(10));
                wuBean.setVipId(rs.getInt(11));
                boolean isOnline = rs.getBoolean(12);
                if (isOnline) {
                    wuBean.setStateOnline("Online");
                } else {
                    wuBean.setStateOnline("Offline");
                }
                wuBean.setAvartaId(rs.getInt(13));
                wuBean.setLastLongin(rs.getTimestamp(14));
                wuBean.setEmail(rs.getString(15));
                wuBean.setStateActive(rs.getBoolean(16));
                wuBean.setPartnerId(rs.getInt(17));
                wuBean.setAvartaFileId(rs.getInt(18));
                wuBean.setBiaFileId(rs.getInt(19));
                wuBean.setSex(rs.getInt(20));
                wuBean.setTimeQuay(rs.getInt(21));
                wuBean.setStatus(rs.getString(22));
                wuBean.setMobileVersion(rs.getString(23));
                wuBean.setRegisterDate(rs.getTimestamp(24));
                wuBean.setFromDevice(rs.getString(25));
                wuBean.setRefCode(rs.getString(26));
                wuBean.setGameVersion(rs.getString(27));

                wuBean.setLoginName(rs.getString("loginName"));
                wuBean.setCmnd(rs.getString("cmnd"));
                wuBean.setPhonenumber(rs.getString("xePhoneNumber"));
                wuBean.setLastSecondLogin(rs.getTimestamp("lastSecondLogin"));

                wuBean.setAvatarLocked(!isUserCanUploadAvatar(wuBean.getUserId()));
            }
            rs.close();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (wuBean.getUserId() == 0) {
            return null;
        } else {
            return wuBean;
        }
    }

    public static List<Partner> getAllPartners() {
        List<Partner> partners = new ArrayList<>();

        String sql = "SELECT * FROM partner WHERE partnerId >= 0";
        Connection conn;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Partner partner = new Partner();

                partner.setId(rs.getInt("partnerId"));
                partner.setName(rs.getString("name"));

                partners.add(partner);
            }
            rs.close();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partners;
    }

    public static boolean updateWorkingUser(long userId, String displayName, String email, int sex, String cmnd, String phone, UserBean loggedInUser) {
        String query = "{call uspXEUpdateWorkingUser(?, ?, ?, ?, ?, ?, ?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setLong(1, userId);
            cs.setString(2, displayName);
            cs.setString(3, email);
            cs.setInt(4, sex);
            cs.setString(5, cmnd);
            cs.setString(6, phone);
            cs.setInt(7, (int)loggedInUser.getId());

            cs.executeUpdate();

            cs.close();

            return true;
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

        return false;
    }

    /**
     * Kiểm tra xem user có quyền upload avatar hay không (chức năng khóa avatar).
     * @param userId
     * @return
     */
    public static boolean isUserCanUploadAvatar(long userId) {
        String query = "{? = call uspXECanUserUploadAvatar(?) }";

        Connection conn = null;
        try {
            conn = DBPoolConnection.getConnection();
            CallableStatement cs = conn.prepareCall(query);

            cs.setLong(2, userId);
            cs.registerOutParameter(1, Types.INTEGER);

            cs.execute();
            int result = cs.getInt(1);

            cs.close();

            return (result != 0);
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

        return false;
    }

    /**
     * Khóa chức năng upload avatar của user.
     * @param userId
     * @return
     */
    public boolean lockUserAvatar(int userId, UserBean loggedInUser) {
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call  uspXELockUserAvatar(?, ?) }";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, userId);
            cs.setInt(2, (int)loggedInUser.getId());

            cs.executeUpdate();

            cs.close();
            conn.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * Mở khóa chức năng upload avatar của user.
     * @param userId
     * @return
     */
    public boolean unlockUserAvatar(int userId, UserBean loggedInUser) {
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "{call uspXEUnlockUserAvatar(?, ?) }";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, userId);
            cs.setInt(2, (int)loggedInUser.getId());

            cs.executeUpdate();

            cs.close();
            conn.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }
}
