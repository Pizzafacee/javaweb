package com.atguigu.myssm.trans;



import com.atguigu.myssm.utils.ConnectUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务管理
 */
public class TransactionManager {
    //开启事务
    public static void startTransaction() throws SQLException {
        //获取数据库连接对象
        Connection connection = ConnectUtil.getConnect();
        connection.setAutoCommit(false);

    }

    //提交事务
    public static void commitTransaction() throws SQLException {
        Connection connection = ConnectUtil.getConnect();
        connection.commit();
        ConnectUtil.closeConnect();
    }

    //回滚事务
    public static void rollbackTransaction() throws SQLException {
        Connection connection = ConnectUtil.getConnect();
        connection.rollback();
        ConnectUtil.closeConnect();
    }
}
