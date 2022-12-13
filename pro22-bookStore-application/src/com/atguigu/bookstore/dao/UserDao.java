package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.pojo.User;

public interface UserDao {
    User selectUser(String uname, String pwd);

    void addUser(User user);
}
