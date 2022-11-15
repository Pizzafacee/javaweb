package com.atguigu.qqZone.service.impl;

import com.atguigu.qqZone.dao.UserBasicDao;
import com.atguigu.qqZone.pojo.UserBasic;
import com.atguigu.qqZone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

public class UserBasicServiceImpl implements UserBasicService {
    private UserBasicDao userBasicDao;

    @Override
    public UserBasic login(String loginId, String pwd) {
        return userBasicDao.getUerBasic(loginId, pwd);
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> userBasicList = userBasicDao.getUserBasicList(userBasic);
        List<UserBasic> friendList = new ArrayList<>(userBasicList.size());
        for (UserBasic user : userBasicList) {
            UserBasic userBasicById = userBasicDao.getUserBasicById(user.getId());
            friendList.add(userBasicById);
        }
        return friendList;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        UserBasic userBasicById = userBasicDao.getUserBasicById(id);
        return userBasicById;
    }
}
