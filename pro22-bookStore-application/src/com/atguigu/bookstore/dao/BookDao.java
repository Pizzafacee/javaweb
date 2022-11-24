package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.pojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> selectAll();
}
