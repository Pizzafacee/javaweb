package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.pojo.OrderBean;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    @Override
    public void addOrder(OrderBean orderBean) {
        int addOrderBean = orderDao.addOrderBean(orderBean);
        orderBean.setId(addOrderBean);
    }
}
