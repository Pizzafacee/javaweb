package com.atguigu.qqZone.service;

import com.atguigu.qqZone.pojo.HostReply;

public interface HostReplyService {
    //根据replyId查找hostReply
    HostReply getHostReplyByReplyId(Integer id);

    void delHostReply(Integer id);

    void delHostReplyByReplyId(Integer id);
}
