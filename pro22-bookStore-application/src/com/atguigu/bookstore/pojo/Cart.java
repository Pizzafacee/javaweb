package com.atguigu.bookstore.pojo;

import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> cartItemMap;
    private Double totalMoney; //购物车中的总金额
    private Integer totalCount;//购物车中的购物车项数
    private Integer totalBookCount; //购物车中总的书本数量

    public Integer getTotalBookCount() {
        this.totalBookCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            for (Map.Entry<Integer, CartItem> entry : cartItemMap.entrySet()) {
                CartItem cartItem = entry.getValue();
                Integer buyCount = cartItem.getBuyCount();
                totalMoney += buyCount;
            }
        }
        return totalBookCount;
    }

    public Cart(Map<Integer, CartItem> cartItemMap, Double totalMoney, Integer totalCount) {
        this.cartItemMap = cartItemMap;
        this.totalMoney = totalMoney;
        this.totalCount = totalCount;
    }

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        this.totalMoney = 0.0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            for (Map.Entry<Integer, CartItem> entry : cartItemMap.entrySet()) {
                CartItem cartItem = entry.getValue();
                Double bookSale = cartItem.getBook().getPrice() * cartItem.getBuyCount();
                totalMoney += bookSale;
            }
        }
        return totalMoney;
    }


    public Integer getTotalCount() {
        this.totalCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }


}
