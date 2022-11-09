package atguigu.myssm.trans;

import atguigu.myssm.util.ConnectUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务管理
 */
public class TransactionManager {
    //开启事务
    public static void startTransaction() throws SQLException {
        //获取数据库连接对象
        Connection connection = ConnectUtil.getConnection();
        connection.setAutoCommit(false);

    }

    //提交事务
    public static void commitTransaction() throws SQLException {
        Connection connection = ConnectUtil.getConnection();
        connection.commit();
        ConnectUtil.closeConnection();
    }

    //回滚事务
    public static void rollbackTransaction() throws SQLException {
        Connection connection = ConnectUtil.getConnection();
        connection.rollback();
        ConnectUtil.closeConnection();
    }
}
