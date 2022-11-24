package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.pojo.Book;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.myssm.baseDao.BaseDAO;

import java.util.List;

public class BookServiceImpl extends BaseDAO<Book> implements BookService {
    private BookDao bookDao;

    @Override
    public List<Book> queryAllBook() {
        List<Book> books = bookDao.selectAll();
        return books;
    }
}
