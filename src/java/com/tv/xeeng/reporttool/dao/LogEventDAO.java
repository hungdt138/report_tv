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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hungdt
 */
public class LogEventDAO {

    public List<LogEventItemHistory> getAllLogEventItemHistory(int currentPage) {
        List<LogEventItemHistory> lst = new ArrayList<LogEventItemHistory>();

        int rowNumDisplay = 20;
        int rowIdFirst = ((currentPage - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql_count = "select COUNT(id) from xeLogJoinItemHistory";
            String sqlStr = "select * from (select ROW_NUMBER() over (order by time desc) as row, x.*, w.name "
                    + "from xeLogJoinItemHistory x "
                    + "inner join workinguser w "
                    + "on x.userId = w.userId) as log "
                    + "where row between ? and ?";

            connection = DBPoolConnection.getConnection();
            stmt = connection.prepareStatement(sql_count);
            rs = stmt.executeQuery();
            if (rs.next()) {
                totalRc = rs.getInt(1);
                totalPage = totalRc / rowNumDisplay;
                if (totalRc > (totalPage * rowNumDisplay)) {
                    totalPage = totalPage + 1;
                }
            }
            
            if(totalPage > 0)
            {
                
            }
        } catch (Exception e) {
        }

        return lst;
    }

    public List<LogEventItemHistory> getLogEventItemHistoryByUserId(int currentPage, int userId) {
        List<LogEventItemHistory> lst = new ArrayList<LogEventItemHistory>();

        return lst;
    }

    public List<LogEventItemHistory> getLogEventItemHistoryByItem(int currentPage, String itemCode) {
        List<LogEventItemHistory> lst = new ArrayList<LogEventItemHistory>();

        return lst;
    }

    public List<LogEventItemHistory> getLogEventItemHistoryByDate(int currentPage, String fromDate, String toDate) {
        List<LogEventItemHistory> lst = new ArrayList<LogEventItemHistory>();

        return lst;
    }

    public List<LogEventGiftHistory> getAllLogEventGiftHistory(int currentPage) {
        List<LogEventGiftHistory> lst = new ArrayList<LogEventGiftHistory>();

        return lst;
    }

    public List<LogEventGiftHistory> getLogEventGiftHistoryByUserId(int currentPage, int userId) {
        List<LogEventGiftHistory> lst = new ArrayList<LogEventGiftHistory>();

        return lst;
    }

    public List<LogEventGiftHistory> getLogEventGiftHistoryByEvgfCode(int currentPage, String evgfCode) {
        List<LogEventGiftHistory> lst = new ArrayList<LogEventGiftHistory>();

        return lst;
    }

    public List<LogEventGiftHistory> getLogEventGiftHistoryByValue(int currentPage, int value) {
        List<LogEventGiftHistory> lst = new ArrayList<LogEventGiftHistory>();

        return lst;
    }

    public List<LogEventGiftHistory> getLogEventGiftHistoryByDate(int currentPage, String fromDate, String toDate) {
        List<LogEventGiftHistory> lst = new ArrayList<LogEventGiftHistory>();

        return lst;
    }
}
