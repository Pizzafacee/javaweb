package com.atguigu.bookstore.controller;

import com.atguigu.bookstore.pojo.Book;
import com.atguigu.bookstore.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    private BookService bookService;

    public String index(HttpSession session) {
        List<Book> books = bookService.queryAllBook();
        session.setAttribute("bookList", books);
        return "index";
    }
}
