package com.atguigu.bookstore.service;

import com.atguigu.bookstore.pojo.Book;

import java.util.List;

public interface BookService {
     List<Book> queryAllBook();

    Book getBookByCartItemId(Integer id);
}
