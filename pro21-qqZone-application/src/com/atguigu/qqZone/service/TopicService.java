package com.atguigu.qqZone.service;

import com.atguigu.qqZone.pojo.Topic;
import com.atguigu.qqZone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    List<Topic> getTopicList(UserBasic userBasic);
}
