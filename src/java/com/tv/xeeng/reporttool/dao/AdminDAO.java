/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tv.xeeng.reporttool.beans.AdminAdvBean;

/**
 *
 * @author PHUCTV
 */
public class AdminDAO {

    public AdminAdvBean getAdvDetail(int advIndex) throws SQLException {
        String query = "{call uspReportGetAdvDetail(?) }";
        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            conn = DBPoolConnection.getConnection();
            cs = conn.prepareCall(query);
            cs.clearParameters();
            cs.setInt(1, advIndex);
            rs = cs.executeQuery();
            if (rs != null && rs.next()) {
                AdminAdvBean bean = new AdminAdvBean();
                bean.setAdvertisingId(rs.getLong("advertisingId"));
                bean.setContent(rs.getString("content"));
                bean.setPartnerId(rs.getInt("partnerId"));
                bean.setStartDate(rs.getString("startDate"));
                bean.setEndDate(rs.getString("endDate"));
                return bean;
            }
            return null;
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
    }

    public List<Object> getAdvList(int pageIndex) throws SQLException {
        ArrayList<Object> ret = new ArrayList<Object>();
        ArrayList<AdminAdvBean> data = new ArrayList<AdminAdvBean>();
        String query = "{call uspReportGetAdvList(?,?,?,?) }";
        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        int totalrecord;
        int totalpage;
        try {
            conn = DBPoolConnection.getConnection();
            cs = conn.prepareCall(query);
            cs.clearParameters();
            cs.setInt(1, pageIndex);
            cs.setInt(2, 10);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            rs = cs.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    AdminAdvBean bean = new AdminAdvBean();
                    bean.setOrder(rs.getLong("row"));
                    bean.setAdvertisingId(rs.getLong("advertisingId"));
                    bean.setContent(rs.getString("content"));
                    bean.setIsDisplay(rs.getBoolean("isDisplay"));
                    bean.setPartnerId(rs.getInt("partnerId"));
                    bean.setStartDate(rs.getString("startDate"));
                    bean.setEndDate(rs.getString("endDate"));
                    data.add(bean);
                }
            }
            totalrecord = cs.getInt(3);
            totalpage = cs.getInt(4);
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
}
