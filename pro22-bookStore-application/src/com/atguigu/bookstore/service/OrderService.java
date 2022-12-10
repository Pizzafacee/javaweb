package com.atguigu.bookstore.service;

import com.atguigu.bookstore.pojo.OrderBean;
import com.atguigu.bookstore.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrder(OrderBean orderBean);


    List<OrderBean> getOrderList(User currUser);
}
