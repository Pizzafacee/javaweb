package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.dao.CartItemDao;
import com.atguigu.bookstore.pojo.Book;
import com.atguigu.bookstore.pojo.Cart;
import com.atguigu.bookstore.pojo.CartItem;
import com.atguigu.bookstore.pojo.User;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.CartItemService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDao cartItemDao;
    private BookService bookService;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDao.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDao.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //先查找出是否有该购物车项
        if (cart != null) {
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null) {
                cartItemMap = new HashMap<>();
            }
            if (cartItemMap.containsKey(cartItem.getBook().getId())) {
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount() + 1);
                this.updateCartItem(cartItemTemp);
            } else {
                this.addCartItem(cartItem);
            }
        } else {
            this.addCartItem(cartItem);
        }
    }

    @Override
    public Cart getCart(User currUser) {
        List<CartItem> cartItemList = this.getCartItemList(currUser);
        Cart cart = new Cart();
        Map<Integer, CartItem> map = new HashMap<>();
        if (cartItemList != null && cartItemList.size() > 0) {
            for (CartItem cartItem : cartItemList) {
                map.put(cartItem.getBook().getId(), cartItem);
            }
        }
        cart.setCartItemMap(map);
        return cart;
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItems = cartItemDao.selectCartItemList(user);
        for (CartItem cartItem : cartItems) {
            Book book = bookService.getBookByCartItemId(cartItem.getBook().getId());
            cartItem.setBook(book);
        }
        return cartItems;
    }

    //删除购物车项
    @Override
    public void delCartItems(ArrayList<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            cartItemDao.delCartItem(cartItem);
        }
    }
}
