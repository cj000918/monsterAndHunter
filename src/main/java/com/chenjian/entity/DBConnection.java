package com.chenjian.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接
 */
public class DBConnection {

    private static String DRVIER = "oracle.jdbc.OracleDriver";

    // 创建一个数据库连接
    protected static Connection con = null;

    /**
     * 获取Connection对象
     *
     * @return
     */
    public static Connection getConnection(String userName, String passWord, String url) {
        try {
            Class.forName(DRVIER);
            con = DriverManager.getConnection(url, userName, passWord);
            System.out.println("成功连接数据库");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return con;
    }

}
