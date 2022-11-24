package com.atguigu.bookstore.controller;

import com.atguigu.bookstore.pojo.User;
import com.atguigu.bookstore.service.UserService;

import javax.servlet.http.HttpSession;

public class UserController {
    private UserService userService;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if (user != null) {
            session.setAttribute("user",user);
            return "redirect:book.do";
        }
        return "user/login";
    }
}
