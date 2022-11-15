package com.atguigu.qqZone.service;

import com.atguigu.qqZone.pojo.HostReply;

public interface HostReplyService {
    //根据replyId查找hostReply
    HostReply getHostReplyByReplyId(Integer id);
}
