package com.tv.xeeng.reporttool.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBPoolConnection {

    private static Logger logger = Logger.getLogger(DBPoolConnection.class);
    private static DataSource ds = getDataSource();

    public static DataSource getDataSource() {
        ComboPooledDataSource ds = null;
        try {
            //init pool data source
            ds = new ComboPooledDataSource();
            ds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ds.setJdbcUrl("jdbc:sqlserver://192.168.50.108:1433;DatabaseName=xeeng;");
            ds.setUser("xeeng");
            ds.setPassword("gamexeeng");
            ds.setMinPoolSize(5);
            ds.setMaxPoolSize(30);
            ds.setAcquireIncrement(8);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
