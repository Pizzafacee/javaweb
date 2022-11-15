package com.atguigu.qqZone.controller;

import com.atguigu.qqZone.pojo.Topic;
import com.atguigu.qqZone.service.TopicService;

import javax.servlet.http.HttpSession;

public class TopicController {
    private TopicService topicService;

    //点击日志获取日志详情
    public String topicDetail(Integer id, HttpSession session) {
        Topic topic = topicService.getTopicDetailById(id);
        session.setAttribute("topic", topic);
        return "frames/detail";
    }
}
