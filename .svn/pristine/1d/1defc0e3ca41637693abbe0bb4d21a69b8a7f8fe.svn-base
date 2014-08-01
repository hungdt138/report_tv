package com.tv.xeeng.reporttool.cron;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tv.xeeng.reporttool.util.BlahBlahUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    public static Connection getConnection() {

        ComboPooledDataSource ds = null;
        try {
            //init pool data source
            ds = new ComboPooledDataSource();
            ds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ds.setJdbcUrl("jdbc:sqlserver://192.168.95.252:1433;DatabaseName=xeeng;");
            ds.setUser("gameadmin");
            ds.setPassword("troidatoi");
            ds.setMinPoolSize(5);
            ds.setMaxPoolSize(30);
            ds.setAcquireIncrement(8);
        } catch (Exception ex) {
        }

        try {
            return ds.getConnection();
        } catch (SQLException ex) {
            System.out.println(BlahBlahUtil.getLogString("    Exception - " + ex.getMessage()));
        }

        return null;
    }

}
