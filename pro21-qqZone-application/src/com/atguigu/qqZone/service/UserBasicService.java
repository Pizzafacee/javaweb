package com.atguigu.qqZone.service;

import com.atguigu.qqZone.dao.UserBasicDao;
import com.atguigu.qqZone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {
    UserBasic login(String loginId,String pwd);
    List<UserBasic> getFriendList(UserBasic userBasic);
}
