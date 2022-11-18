package com.atguigu.qqZone.controller;

import com.atguigu.qqZone.pojo.Reply;
import com.atguigu.qqZone.pojo.Topic;
import com.atguigu.qqZone.pojo.UserBasic;
import com.atguigu.qqZone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class ReplyController {
    private ReplyService replyService;

    public String addReply(String content, Integer topicId, HttpSession session) {
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content, new Date(), userBasic, new Topic(topicId));
        replyService.addReply(reply);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }

    public String delReply(Integer replyId,Integer topicId){
        replyService.delReply(replyId);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
}
