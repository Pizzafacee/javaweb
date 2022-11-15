package com.atguigu.qqZone.service.impl;


import com.atguigu.qqZone.dao.TopicDao;
import com.atguigu.qqZone.pojo.Reply;
import com.atguigu.qqZone.pojo.Topic;
import com.atguigu.qqZone.pojo.UserBasic;
import com.atguigu.qqZone.service.ReplyService;
import com.atguigu.qqZone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao;
    private ReplyService replyService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDao.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicDetailById(Integer id) {
        Topic topicById = topicDao.getTopicById(id);
        List<Reply> replyByTopicId = replyService.getReplyByTopicId(id);
        topicById.setReplyList(replyByTopicId);
        return topicById;
    }
}
