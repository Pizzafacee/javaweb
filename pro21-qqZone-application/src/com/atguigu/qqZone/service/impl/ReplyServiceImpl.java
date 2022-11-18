package com.atguigu.qqZone.service.impl;

import com.atguigu.qqZone.dao.ReplyDao;
import com.atguigu.qqZone.pojo.HostReply;
import com.atguigu.qqZone.pojo.Reply;
import com.atguigu.qqZone.pojo.UserBasic;
import com.atguigu.qqZone.service.HostReplyService;
import com.atguigu.qqZone.service.ReplyService;
import com.atguigu.qqZone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDao replyDao;
    private HostReplyService hostReplyService;
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyByTopicId(Integer id) {
        List<Reply> replies = replyDao.selectReplyList(id);
        //遍历查找每条reply的主人回复
        for (Reply reply : replies) {
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            //获取每条回复的作者信息
            UserBasic userBasicById = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(userBasicById);
            reply.setHostReply(hostReply);
        }
        return replies;
    }

    //新增
    @Override
    public void addReply(Reply reply) {
        replyDao.insertReply(reply);
    }

    //删除
    @Override
    public void delReply(Integer replyId) {
        //先删除主人回复
        Reply reply = replyDao.selectReplyById(replyId);
        if (reply != null) {
            HostReply hostReplyByReplyId = hostReplyService.getHostReplyByReplyId(replyId);
            if (hostReplyByReplyId != null) {
                hostReplyService.delHostReply(hostReplyByReplyId.getId());
            }
        }
        replyDao.delReply(reply);
    }

    @Override
    public void delReplyByTopicId(Integer id) {
        //先查询所有的回复
        List<Reply> replyList = replyDao.selectReplyByTopicId(id);
        for (Reply reply : replyList) {
            //删除回复及主人回复
            replyDao.delReply(reply);
            hostReplyService.delHostReplyByReplyId(id);
        }
    }
}
