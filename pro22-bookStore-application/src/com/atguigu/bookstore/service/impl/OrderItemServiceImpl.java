package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.pojo.CartItem;
import com.atguigu.bookstore.pojo.OrderBean;
import com.atguigu.bookstore.pojo.OrderItem;
import com.atguigu.bookstore.service.OrderItemService;

import java.util.ArrayList;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao;

    @Override
    public void addOrderItems(ArrayList<CartItem> cartItems, OrderBean orderBean) {
        if (cartItems == null || cartItems.size() == 0) return;
        OrderItem orderItem = new OrderItem();
        for (CartItem cartItem : cartItems) {
            orderItem.setOrderBean(orderBean);
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItemDao.addOrderItem(orderItem);
        }
    }
}
