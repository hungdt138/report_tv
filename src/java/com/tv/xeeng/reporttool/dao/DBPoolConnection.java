package com.tv.xeeng.reporttool.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.log4j.Logger;

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

    //////////////////////////////////////////////////////////////
    /**
     * Close objects which are used to interact with database
     *
     * @param obj Object need to be closed such as Statment, PrepareStatment,
     * Connection, ResuletSet, CallableStatment, BatchStatement
     * @author Do Tien Hung - Date: 02/08/2014
     */
    // ///////////////////////////////////////////////////////////////
    public static void closeObject(CallableStatement obj) {
        try {
            if (obj != null) {
                obj.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ///////////////////////////////////////////////////////////////
    /**
     * Close objects which are used to interact with database
     *
     * @param obj Object need to be closed such as Statment, PrepareStatment,
     * Connection, ResuletSet, CallableStatment, BatchStatement
     * @author Do Tien Hung - Date: 02/08/2014
     */
    // ///////////////////////////////////////////////////////////////
    public static void closeObject(Statement obj) {
        try {
            if (obj != null) {
                obj.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ///////////////////////////////////////////////////////////////
    /**
     * Close objects which are used to interact with database
     *
     * @param obj Object need to be closed such as Statment, PrepareStatment,
     * Connection, ResuletSet, CallableStatment, BatchStatement
     * @author Do Tien Hung - Date: 02/08/2014
     */
    // ///////////////////////////////////////////////////////////////
    public static void closeObject(ResultSet obj) {
        try {
            if (obj != null) {
                obj.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ///////////////////////////////////////////////////////////////
    /**
     * Close objects which are used to interact with database
     *
     * @param obj Object need to be closed such as Statment, PrepareStatment,
     * Connection, ResuletSet, CallableStatment, BatchStatement
     * @author Do Tien Hung - Date: 02/08/2014
     */
    // ///////////////////////////////////////////////////////////////
    public static void closeObject(Connection obj) {
        try {
            if ((obj != null)) {
                if (!obj.isClosed() && !obj.getAutoCommit()) {
                    try {
                        obj.rollback();
                    } catch (Exception e) {
                         e.printStackTrace();
                    }
                }

                obj.setAutoCommit(true);

                obj.close();
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}
