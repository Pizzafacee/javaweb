package com.atguigu.mysqlSSM.basedao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class BaseDao<T> {
    //数据库的连接信息和对象
    public final String driver = "com.mysql.cj.jdbc.Driver";
    public final String username = "root";
    public final String pwd = "conanshwk";
    public final String url = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useTimezone=true";

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //T的class对象
    private Class TEntity;


    //在创建子类对象时会调用父类的无参数构造方法
    public BaseDao() {
        //getClass()获取的是子类的类对象，要获取baseDao的类对象需要再使用getGenericSuperclass()方法
        Type genericSuperclass = getClass().getGenericSuperclass();
        //参数化类型，指的是获取BaseDao中的所有泛型的类型，得到的是一个包含所有泛型类型的数组
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        try {
            //这样就获取到了T的Class对象
            TEntity = Class.forName(actualTypeArgument.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConn() {
        try {
            //1.加载驱动
            Class.forName(driver);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(url, username, pwd);
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

    //给预处理命令对象设置参数
    private void setParams(PreparedStatement psmt, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
        }
    }

    //查询所有的水果
    protected List<T> executeQueryAll(String sql, Object... params) {
        List<T> list = new ArrayList<>();
        try {
            connection = getConn();
            preparedStatement = connection.prepareStatement(sql);
            setParams(preparedStatement, params);
            resultSet = preparedStatement.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = resultSet.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            while (resultSet.next()) {
                T entity = (T) TEntity.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);            //fid   fname   price
                    Object columnValue = resultSet.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close(resultSet, preparedStatement, connection);
        }
        return list;
    }

    //新增或者修
    protected int addOrUpdate(String sql, Object... params) {
        //判断是新增还是修改
        boolean insert = sql.trim().toUpperCase().startsWith("INSERT");
        connection = getConn();
        try {
            if (insert) {
                preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            } else {
                 preparedStatement = connection.prepareStatement(sql);
            }
            setParams(preparedStatement, params);
            int i = preparedStatement.executeUpdate();
            if(insert){
                 resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    int intValue = ((Long) resultSet.getLong(1)).intValue();
                    return intValue;
                }
            }
            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    //通过反射技术给obj对象的property属性赋propertyValue值
    private void setValue(Object obj, String property, Object propertyValue) {
        Class clazz = obj.getClass();
        try {
            //获取property这个字符串对应的属性名 ， 比如 "fid"  去找 obj对象中的 fid 属性
            Field field = clazz.getDeclaredField(property);
            if (field != null) {
                field.setAccessible(true);
                field.set(obj, propertyValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected  void delete(String sql, long parseLong){
        try {
            connection = getConn();
            preparedStatement = connection.prepareStatement(sql);
           setParams(preparedStatement,parseLong);
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

}
