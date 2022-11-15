package com.atguigu.qqZone.service;

import com.atguigu.qqZone.pojo.UserBasic;
import com.atguigu.qqZone.pojo.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getTopicList(UserBasic userBasic);

    Topic getTopicDetailById(Integer id);
}
