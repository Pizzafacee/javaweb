package com.atguigu.qqZone.service.impl;


import com.atguigu.qqZone.dao.TopicDao;
import com.atguigu.qqZone.pojo.Reply;
import com.atguigu.qqZone.pojo.Topic;
import com.atguigu.qqZone.pojo.UserBasic;
import com.atguigu.qqZone.service.ReplyService;
import com.atguigu.qqZone.service.TopicService;
import com.atguigu.qqZone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao;
    private ReplyService replyService;
    private UserBasicService userBasicService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDao.getTopicList(userBasic);
    }

    private Topic getTopicById(Integer id) {
        Topic topicById = topicDao.getTopicById(id);
        UserBasic userBasicById = userBasicService.getUserBasicById(topicById.getAuthor().getId());
        topicById.setAuthor(userBasicById);
        List<Reply> replyByTopicId = replyService.getReplyByTopicId(id);
        topicById.setReplyList(replyByTopicId);
        return topicById;
    }

    @Override
    public Topic getTopicDetailById(Integer id) {
        Topic topicById = this.getTopicById(id);
        return topicById;
    }

    @Override
    public void delTopic(Integer id) {
        //根据topicID删除回复和主人回复
        replyService.delReplyByTopicId(id);
        //再删除日志
        topicDao.delTopic(new Topic(id));
    }
}
