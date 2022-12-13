package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.pojo.User;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public User login(String uname, String pwd) {
        User user = userDao.selectUser(uname, pwd);
        return user;
    }

    @Override
    public void regist(User user) {
        userDao.addUser(user);
    }
}
