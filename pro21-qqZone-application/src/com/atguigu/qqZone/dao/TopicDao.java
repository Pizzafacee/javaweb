package com.atguigu.qqZone.dao;

import com.atguigu.qqZone.pojo.Topic;
import com.atguigu.qqZone.pojo.UserBasic;

import java.util.List;

public interface TopicDao {
    List<Topic> getTopicList(UserBasic userBasic);

    //添加日志
    void addTopic(Topic topic);

    //删除日志
    void delTopic(Topic topic);

    //根据id获取日志信息
    Topic getTopicById(Integer id);
}
