
package com.quanr.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by kingsley.zhang on 2017/3/12.
 */
public class DataBaseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseUtil.class);
    private static Properties myProperties = new Properties();
    private static String DRIVER_NAME = null;
    private static String URL = null;
    private static String USER_NAME = null;
    private static String PASSWORD = null;

    static {
        try {
            myProperties.load(DataBaseUtil.class.getResourceAsStream("/jdbc.properties"));
        } catch (IOException e) {
            LOGGER.error("无法加载JDBC配置文件", e);
        }
        DRIVER_NAME = myProperties.getProperty("jdbc.driver");
        URL = myProperties.getProperty("jdbc.url");
        USER_NAME = myProperties.getProperty("jdbc.username");
        PASSWORD = myProperties.getProperty("jdbc.password");
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            LOGGER.error("找不到数据库", e);
        }
    }

    public static Connection getConnection() {
        java.sql.Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("获取connection发生错误，请检查配置", e);
        }
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error("关闭resultSet失败");
            }
        }
        if (statement != null) {
            try {
                if (!statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error("关闭statement失败");
            }
        }
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("关闭connection失败");
            }
        }
    }

}

