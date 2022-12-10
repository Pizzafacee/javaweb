package com.atguigu.bookstore.pojo;

import java.math.BigDecimal;

/**
 * 购物车项
 */
public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User user;

    private Double xj;

    public Double getXj() {
        Double price = this.book.getPrice();
        BigDecimal bigDecimal = new BigDecimal(price + "");
        BigDecimal bigDecimal1 = new BigDecimal(buyCount + "");
        BigDecimal multiply = bigDecimal.multiply(bigDecimal1);
        this.xj = multiply.doubleValue();
        return this.xj;
    }

    public CartItem() {
    }

    public CartItem(Integer cartItemId, Integer buyCount) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CartItem(Integer id) {
        this.id = id;
    }
}
