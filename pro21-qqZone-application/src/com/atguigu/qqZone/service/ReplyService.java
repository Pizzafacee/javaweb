package com.atguigu.qqZone.service;

import com.atguigu.qqZone.pojo.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> getReplyByTopicId(Integer id);
}
