package com.atguigu.qqZone.service.impl;

import com.atguigu.qqZone.dao.ReplyDao;
import com.atguigu.qqZone.pojo.HostReply;
import com.atguigu.qqZone.pojo.Reply;
import com.atguigu.qqZone.service.HostReplyService;
import com.atguigu.qqZone.service.ReplyService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDao replyDao;
    private HostReplyService hostReplyService;

    @Override
    public List<Reply> getReplyByTopicId(Integer id) {
        List<Reply> replies = replyDao.selectReplyList(id);
        //遍历查找每条reply的主人回复
        for (Reply reply : replies) {
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replies;
    }
}
