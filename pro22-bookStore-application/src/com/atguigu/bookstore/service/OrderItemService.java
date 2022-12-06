package com.atguigu.bookstore.service;

import com.atguigu.bookstore.pojo.CartItem;
import com.atguigu.bookstore.pojo.OrderBean;

import java.util.ArrayList;

public interface OrderItemService {
    void addOrderItems(ArrayList<CartItem> cartItems, OrderBean orderBean);
}
