package com.bjpowernode.javaweb.servlet.utils;

import java.sql.*;
import java.util.ResourceBundle;


//对JDBC代码实现封装
public class DBUtil {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("index");
    private static String driver = resourceBundle.getString("driver");
    private static String url = resourceBundle.getString("url") ;
    private static String user = resourceBundle.getString("user");
    private static String password = resourceBundle.getString("password");


    //注册驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


        public static Connection getConnection() throws SQLException {
           Connection conn = DriverManager.getConnection(url,user,password);
            return conn;
        }

        public static void close(ResultSet rs, PreparedStatement ps,Connection conn) throws SQLException {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }


        }


}
