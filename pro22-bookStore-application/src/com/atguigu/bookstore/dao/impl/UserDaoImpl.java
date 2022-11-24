package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.pojo.User;
import com.atguigu.myssm.baseDao.BaseDAO;

public class UserDaoImpl extends BaseDAO<User> implements UserDao {
    @Override
    public User selectUser(String uname, String pwd) {
        String sql = "select * from t_user where uname = ? and pwd = ?";
        User user = super.load(sql, uname, pwd);
        return user;
    }
}
