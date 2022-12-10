package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.pojo.OrderBean;
import com.atguigu.bookstore.pojo.User;
import com.atguigu.myssm.baseDao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDaoImpl extends BaseDAO<OrderBean> implements OrderDao {
    @Override
    public int addOrderBean(OrderBean orderBean) {
        String sql = "insert into t_order values(0,?,?,?,?,?)";
        int i = super.executeUpdate(sql, orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        return i;
    }

    @Override
    public List<OrderBean> getOrderList(User currUser) {
        String sql = "select * from t_order where orderUser = ?";
        List<OrderBean> orderBeanList = super.executeQuery(sql, currUser.getId());
        return orderBeanList;
    }

    @Override
    public Integer getTotalCookCount(OrderBean orderBean) {
        String sql = "SELECT SUM(t3.buyCount) AS totalBookCount , t3.orderBean FROM " +
                "(" +
                "SELECT t1.id , t2.buyCount , t2.orderBean FROM t_order t1 INNER JOIN t_order_item t2 " +
                "ON t1.id = t2.orderBean WHERE t1.orderUser = ? " +
                ") t3 WHERE t3.orderBean = ? GROUP BY t3.orderBean" ;
        Object[] objects = super.executeComplexQuery(sql, orderBean.getOrderUser().getId(), orderBean.getId());
        int totalBookCount = ((BigDecimal) objects[0]).intValue();
        return totalBookCount;
    }
}
