package com.atguigu.myssm.baseDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


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
        List<T> list = new ArrayList<>();
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
                T object = (T) entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    setValue(object, columnName, columnValue);
                }
                list.add(object);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    protected void setValue(T object, String colName, Object columnValue1) {
        Class<?> aClass = object.getClass();
        //Field[] declaredFields = aClass.getDeclaredFields();
        try {
            Field declaredField = aClass.getDeclaredField(colName);
            if (declaredField != null) {
                declaredField.setAccessible(true);
                declaredField.set(object, columnValue1);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    //执行更新，返回影响行数
    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        try {
            conn = getConn();
            if (insertFlag) {
                psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                psmt = conn.prepareStatement(sql);
            }
            setParams(psmt, params);
            int count = psmt.executeUpdate();

            if (insertFlag) {
                rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    return ((Long) rs.getLong(1)).intValue();
                }
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, conn);
        }
        return 0;
    }

    //执行查询，返回单个实体对象
    protected T load(String sql, Object... params) {
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            if (rs.next()) {
                T entity = (T) entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);            //fid   fname   price
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, conn);
        }
        return null;
    }
}
