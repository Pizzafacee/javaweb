package com.atguigu.qqZone.dao;

import com.atguigu.qqZone.pojo.UserBasic;

import java.util.List;

public interface UserBasicDao {
    //登录
    UserBasic getUerBasic(String loginId, String pwd);

    //获取所有好友列表
    List<UserBasic> getUserBasicList(UserBasic userBasic);

    //根据id获取用户信息
    UserBasic getUserBasicById(Integer id);

}
