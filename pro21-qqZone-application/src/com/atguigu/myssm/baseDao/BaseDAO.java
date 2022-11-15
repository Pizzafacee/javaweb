package com.atguigu.myssm.baseDao;




import com.atguigu.myssm.exception.DaoException;
import com.atguigu.myssm.utils.ConnectUtil;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {


    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rs;

    //T的Class对象
    private Class entityClass;

    public BaseDAO() throws DaoException {
        //getClass() 获取Class对象，当前我们执行的是new FruitDAOImpl() , 创建的是FruitDAOImpl的实例
        //那么子类构造方法内部首先会调用父类（BaseDAO）的无参构造方法
        //因此此处的getClass()会被执行，但是getClass获取的是FruitDAOImpl的Class
        //所以getGenericSuperclass()获取到的是BaseDAO的Class
        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType 参数化类型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //获取到的<T>中的T的真实的类型
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DaoException("baseDao中的构造方法出错，可能的原因是没有<>中的类型丢失");
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

    //执行更新，返回影响行数
    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        try {
            conn = ConnectUtil.getConnect();
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
            throw new DaoException("执行executeUpdate()出错了");
        }
    }

    //通过反射技术给obj对象的property属性赋propertyValue值
    private void setValue(Object obj, String property, Object propertyValue) {
        Class clazz = obj.getClass();
        try {
            //获取property这个字符串对应的属性名 ， 比如 "fid"  去找 obj对象中的 fid 属性
            Field field = clazz.getDeclaredField(property);
            if (field != null) {
                field.setAccessible(true);
                String name = field.getType().getName();
                if (isMyType(name)) {
                    Class<?> aClass = Class.forName(name);
                    Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(java.lang.Integer.class);
                    propertyValue = declaredConstructor.newInstance(propertyValue);
                }
                field.set(obj, propertyValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new DaoException("执行setValue()方法出错了");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    //判断是否是自定义属性
    protected boolean isMyType(String name) {
        return !isNotMyType(name);
    }

    private boolean isNotMyType(String name) {
        return "java.lang.Integer".equals(name)
                ||"java.lang.String".equals(name)
                ||"java.util.Date".equals(name)
                ||"java.sql.Date".equals(name);
    }


    //执行复杂查询，返回例如统计结果
    protected Object[] executeComplexQuery(String sql, Object... params) {
        try {
            conn = ConnectUtil.getConnect();
            System.out.println("executeComplexQuery->" + conn);
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //6.解析rs
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("执行复杂查询出错了");
        }
        return null;
    }

    //执行查询，返回单个实体对象
    protected T load(String sql, Object... params) {
        try {
            conn = ConnectUtil.getConnect();
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
                    String columnName = rsmd.getColumnLabel(i + 1);            //fid   fname   price
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                return entity;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("执行查找单个对象出错了");
        }
        return null;
    }


    //执行查询，返回List
    protected List<T> executeQuery(String sql, Object... params) {
        List<T> list = new ArrayList<>();
        try {
            conn = ConnectUtil.getConnect();
            System.out.println("executeQuery->" + conn);
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            while (rs.next()) {
                T entity = (T) entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnLabel(i + 1);            //fid   fname   price
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("返回复数对象的方法出错了");
        }
        return list;
    }
}
