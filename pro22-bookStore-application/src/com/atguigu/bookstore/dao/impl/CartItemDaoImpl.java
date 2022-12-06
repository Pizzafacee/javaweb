package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.dao.CartItemDao;
import com.atguigu.bookstore.pojo.CartItem;
import com.atguigu.bookstore.pojo.User;
import com.atguigu.myssm.baseDao.BaseDAO;

import java.util.List;

public class CartItemDaoImpl extends BaseDAO<CartItem> implements CartItemDao {
    @Override
    public void addCartItem(CartItem cartItem) {
        String sql = "insert into t_cart_item values(0,?,?,?)";
        super.executeUpdate(sql,cartItem.getBook().getId(),cartItem.getBook(),cartItem.getUser().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        String sql = "update t_cart_item set buyCount = ? where id = ?";
        super.executeUpdate(sql,cartItem.getBuyCount(),cartItem.getId());
    }

    @Override
    public List<CartItem> selectCartItemList(User user) {
        String sql = "select * from t_cart_item where userBeam=?";
        List<CartItem> cartItems = super.executeQuery(sql, user.getId());
        return cartItems;
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        String sql = "delete from t_cart_item where id=?";
        super.executeUpdate(sql,cartItem.getId());
    }
}
