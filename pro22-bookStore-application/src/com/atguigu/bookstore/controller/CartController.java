package com.atguigu.bookstore.controller;

import com.atguigu.bookstore.pojo.Book;
import com.atguigu.bookstore.pojo.Cart;
import com.atguigu.bookstore.pojo.CartItem;
import com.atguigu.bookstore.pojo.User;
import com.atguigu.bookstore.service.CartItemService;

import javax.servlet.http.HttpSession;

public class CartController {
    private CartItemService cartItemService;

    //加载购物车
    public String index(HttpSession session) {
        User currUser = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(currUser);
        currUser.setCart(cart);
        session.setAttribute("currUser", currUser);
        return "cart/cart";
    }

    /**
     * 新增购物车
     *
     * @param bookId
     * @return
     */
    public String addCart(Integer bookId, HttpSession session) {
        User currUser = (User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem();
        cartItem.setBook(new Book(bookId));
        cartItem.setBuyCount(1);
        cartItemService.addOrUpdateCartItem(cartItem, currUser.getCart());
        return "redirect:cart.do";
    }

    public String editCart(Integer cartItemId,Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
        return "redirect:cart.do";
    }
}
