package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.pojo.OrderBean;
import com.atguigu.myssm.baseDao.BaseDAO;

public class OrderDaoImpl extends BaseDAO<OrderBean> implements OrderDao {
    @Override
    public int addOrderBean(OrderBean orderBean) {
        String sql = "insert into t_order values(0,?,?,?,?,?)";
        int i = super.executeUpdate(sql, orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        return i;
    }
}
