package atguigu.myssm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectUtil {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useTimezone=true";
    public static final String USER = "root";
    public static final String PWD = "conanshwk";

    public static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection() {
        //从ThreadLocal获取连接
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                //1.加载驱动
                Class.forName(DRIVER);
                //2.通过驱动管理器获取连接对象
                Connection connect = DriverManager.getConnection(URL, USER, PWD);
                threadLocal.set(connect);
                return connect;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    //关闭connection
    public static void closeConnection() throws SQLException {
        //从ThreadLocal获取连接
        Connection connection = threadLocal.get();
        if (connection == null) {
            return;
        }
        connection.close();
        threadLocal.set(null);
    }
}
