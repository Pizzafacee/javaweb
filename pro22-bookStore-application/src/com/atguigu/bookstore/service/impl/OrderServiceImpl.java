package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.pojo.OrderBean;
import com.atguigu.bookstore.pojo.User;
import com.atguigu.bookstore.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;

    @Override
    public void addOrder(OrderBean orderBean) {
        int addOrderBean = orderDao.addOrderBean(orderBean);
        orderBean.setId(addOrderBean);
    }

    @Override
    public List<OrderBean> getOrderList(User currUser) {
        List<OrderBean> orderBeanList = orderDao.getOrderList(currUser);
        for (OrderBean orderBean : orderBeanList) {
            //根据订单号和用户id查询每个订单的总数量
            Integer totalCount = orderDao.getTotalCookCount(orderBean);
            orderBean.setTotalBookCount(totalCount);
        }
        return orderBeanList;
    }
}
