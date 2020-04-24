package com.chenjian.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接
 */
public class DBConnection {

    private static String ORACLE_DRVIER = "oracle.jdbc.OracleDriver";
    private static String MYSQL_DRVIER = "com.mysql.jdbc.Driver";

    // 创建一个数据库连接
    protected static Connection con = null;

    /**
     * 获取Connection对象
     *
     * @return
     */
    public static Connection getConnection(String userName, String passWord, String url) {
        try {
            Class.forName(MYSQL_DRVIER);
            con = DriverManager.getConnection(url, userName, passWord);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return con;
    }

}
