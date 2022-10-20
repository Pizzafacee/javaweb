package com.atguigu.myssm.baseDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.List;


public abstract class BaseDao<T> {
    public final String driver = "com.mysql.cj.jdbc.Driver";
    public final String username = "root";
    public final String pwd = "conanshwk";
    public final String url = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useTimezone=true";


    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rs;

    //T的Class对象
    private Class entityClass;

    //无参数构造方法，子类在创建对象的时候会调用父类的构造方法
    public BaseDao() {
        //获取父类的即 BaseDao的class对象
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        //获取所有的参数化类型因，即泛型类型
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        //获取第一个泛型类型,即T
        Type actualTypeArgument = actualTypeArguments[0];
        String typeName = actualTypeArgument.getTypeName();
        try {
            entityClass = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    private Connection getConn() {
        try {
            Class<?> aClass = Class.forName(driver);
            conn = DriverManager.getConnection(url, username, pwd);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (psmt != null) {
                psmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //给预处理的语句设置参数
    private void setParams(PreparedStatement psmt, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
        }
    }

    //查询list
    protected List<T> executeQuery(String sql, Object... params) {
        try {
            //获取连接
            conn = this.getConn();
            //预编译的数据库操作对象
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //获取结果集的列数
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                //创建泛型对象
                T object = (T)entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    setValue(object,columnValue,columnValue);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected  void setValue(T object, Object columnValue, Object columnValue1){
        Class<?> aClass = object.getClass();
    }
}
