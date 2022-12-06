package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.pojo.Book;
import com.atguigu.myssm.baseDao.BaseDAO;

import java.util.List;

public class BookDaoImpl extends BaseDAO<Book> implements BookDao {
    @Override
    public List<Book> selectAll() {
        String sql = "select * from t_book";
        List<Book> books = super.executeQuery(sql);
        return books;
    }

    @Override
    public Book selectBookById(Integer id) {
        String sql = "select * from t_book where id = ?";
        Book load = super.load(sql, id);
        return load;
    }
}
