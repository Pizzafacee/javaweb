package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.pojo.OrderBean;
import com.atguigu.bookstore.pojo.User;

import java.util.List;

public interface OrderDao {
    int addOrderBean(OrderBean orderBean);

    List<OrderBean> getOrderList(User currUser);

    Integer getTotalCookCount(OrderBean orderBean);
}
