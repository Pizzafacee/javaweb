package com.atguigu.bookstore.service;

import com.atguigu.bookstore.pojo.User;

public interface UserService {
     User login(String uname, String pwd);

    void regist(User user);
}
