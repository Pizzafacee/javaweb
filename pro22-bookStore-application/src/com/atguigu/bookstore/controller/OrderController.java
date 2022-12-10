package com.atguigu.bookstore.controller;

import com.atguigu.bookstore.pojo.CartItem;
import com.atguigu.bookstore.pojo.OrderBean;
import com.atguigu.bookstore.pojo.OrderItem;
import com.atguigu.bookstore.pojo.User;
import com.atguigu.bookstore.service.CartItemService;
import com.atguigu.bookstore.service.OrderItemService;
import com.atguigu.bookstore.service.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

public class OrderController {

    private OrderService orderService;
    private OrderItemService orderItemService;
    private CartItemService cartItemService;

    /**
     * 结账功能
     * 1、生成一个订单
     * 2、生成订单中的订单项s
     * 3、删除购物车中的购物车项
     *
     * @param session
     * @return
     */
    public String checkout(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        Double totalMoney = user.getCart().getTotalMoney();
        if (totalMoney == 0) {
            throw new RuntimeException("请再购物车中添加商品");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Date date = new Date();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int hour = date.getHours();
        int min = date.getMinutes();
        int sec = date.getSeconds();
        OrderBean orderBean = new OrderBean();
        orderBean.setOrderNo(uuid + "_" + year + month + day + hour + min + sec);
        orderBean.setOrderDate(date);
        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(totalMoney);
        orderBean.setOrderStatus(0);
        //新增订单
        orderService.addOrder(orderBean);

        //新增订单项
        Map<Integer, CartItem> cartItemMap = user.getCart().getCartItemMap();
        ArrayList<CartItem> cartItems = null;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            Collection<CartItem> values = cartItemMap.values();
            cartItems = new ArrayList<>(values);
            orderItemService.addOrderItems(cartItems, orderBean);
        }
        //删除购物车
        cartItemService.delCartItems(cartItems);
        return "index";
    }

    /**
     * 展示订单列表
     * @param session
     * @return
     */
    public String getOrderList(HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        List<OrderBean> orderBeanList  = orderService.getOrderList(currUser);
        currUser.setOrderBeanList(orderBeanList);
        session.setAttribute("currUser",currUser);
        return "order/order";
    }
}
