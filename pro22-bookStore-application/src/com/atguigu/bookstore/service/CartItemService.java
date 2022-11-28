package com.atguigu.bookstore.service;

import com.atguigu.bookstore.pojo.Cart;
import com.atguigu.bookstore.pojo.CartItem;
import com.atguigu.bookstore.pojo.User;

import java.util.List;

public interface CartItemService {
    //新增购物车项
    void addCartItem(CartItem cartItem);
    //修改购物车项
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    Cart getCart(User user);

    List<CartItem> getCartItemList(User user);

}

