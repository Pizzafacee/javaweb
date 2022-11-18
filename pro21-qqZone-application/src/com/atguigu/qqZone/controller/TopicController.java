package com.atguigu.qqZone.controller;

import com.atguigu.qqZone.pojo.Topic;
import com.atguigu.qqZone.pojo.UserBasic;
import com.atguigu.qqZone.service.TopicService;
import com.atguigu.qqZone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class TopicController {
    private TopicService topicService;

    //点击日志获取日志详情
    public String topicDetail(Integer id, HttpSession session) {
        Topic topic = topicService.getTopicDetailById(id);
        session.setAttribute("topic", topic);
        return "frames/detail";
    }

    //删除日志
    public String delTopic(Integer id) {
        topicService.delTopic(id);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session) {
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //再查询出Topic的信息
        List<Topic> topicList = topicService.getTopicList(userBasic);
        userBasic.setTopicList(topicList);
        session.setAttribute("userBasic",userBasic);
        return "frames/main";
    }
}
