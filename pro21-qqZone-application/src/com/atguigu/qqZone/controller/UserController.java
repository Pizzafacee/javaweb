package com.atguigu.qqZone.controller;


import com.atguigu.qqZone.pojo.Topic;
import com.atguigu.qqZone.pojo.UserBasic;
import com.atguigu.qqZone.service.TopicService;
import com.atguigu.qqZone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;


public class UserController {
    private UserBasicService userBasicService;
    private TopicService topicService;

    //登录
    public String login(String loginId, String pwd, HttpSession session) {
        UserBasic login = userBasicService.login(loginId, pwd);
        if (login != null) {
            List<Topic> topicList = topicService.getTopicList(login);
            List<UserBasic> friendList = userBasicService.getFriendList(login);
            login.setTopicList(topicList);
            login.setFriendList(friendList);
            session.setAttribute("userBasic", login);
            session.setAttribute("friend",login);
            return "index";
        } else {
            return "login";
        }
    }

    /**
     * 进入好友的空间
     */
    public String friend(Integer id,HttpSession session){
       UserBasic userBasic = userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(userBasic);
        userBasic.setTopicList(topicList);
        session.setAttribute("friend",userBasic);
        return "index";
    }
}
