package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.pojo.CartItem;
import com.atguigu.bookstore.pojo.User;

import java.util.List;

public interface CartItemDao {
    //新增
    void addCartItem(CartItem cartItem);

    //修改
    void updateCartItem(CartItem cartItem);

    List<CartItem> selectCartItemList(User user);
}
