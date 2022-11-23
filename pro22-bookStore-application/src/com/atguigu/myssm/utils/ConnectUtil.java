package com.atguigu.myssm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectUtil {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/bookstoredb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useTimezone=true";
    public static final String USER = "root";
    public static final String PWD = "conanshwk";

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //创建连接
    private static Connection createConnect() {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PWD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获得连接
    public static Connection getConnect() {
        //从ThreadLocal获得连接
        Connection connection = threadLocal.get();
        if (connection == null) {
            connection = createConnect();
            threadLocal.set(connection);
        }
        return connection;
    }

    //关闭连接
    public static void closeConnect() {
        try {
            Connection connection = threadLocal.get();
            if (connection == null) {
                return;
            }
            if (!connection.isClosed()) {
                connection.close();
                threadLocal.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
