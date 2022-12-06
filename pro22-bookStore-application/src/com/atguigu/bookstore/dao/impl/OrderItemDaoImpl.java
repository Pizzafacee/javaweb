package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.pojo.OrderItem;
import com.atguigu.myssm.baseDao.BaseDAO;

public class OrderItemDaoImpl extends BaseDAO<OrderItem> implements OrderItemDao {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item values(0,?,?,?)";
        super.executeUpdate(sql,orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }
}
