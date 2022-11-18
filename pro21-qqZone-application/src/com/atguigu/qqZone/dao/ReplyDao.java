package com.atguigu.qqZone.dao;

import com.atguigu.qqZone.pojo.Reply;

import java.util.List;

public interface ReplyDao {
    //根据topicId获取reply
    List<Reply> selectReplyList(Integer id);

    void insertReply(Reply reply);

    Reply selectReplyById(Integer replyId);

    void delReply(Reply reply);

    List<Reply> selectReplyByTopicId(Integer id);
}
