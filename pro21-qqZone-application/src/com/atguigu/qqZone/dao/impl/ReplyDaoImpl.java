package com.atguigu.qqZone.dao.impl;

import com.atguigu.myssm.baseDao.BaseDAO;
import com.atguigu.qqZone.dao.ReplyDao;
import com.atguigu.qqZone.pojo.Reply;

import java.util.List;

public class ReplyDaoImpl extends BaseDAO<Reply> implements ReplyDao {
    @Override
    public List<Reply> selectReplyList(Integer id) {
        String sql = "select * from t_reply where topic = ?";
        List<Reply> replies = super.executeQuery(sql, id);
        return replies;
    }

    @Override
    public void insertReply(Reply reply) {
        executeUpdate("insert into t_reply values(0,?,?,?,?)", reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());
    }

    @Override
    public Reply selectReplyById(Integer replyId) {
        String sql = "select * from t_reply where id = ?";
        Reply load = super.load(sql, replyId);
        return load;
    }

    @Override
    public void delReply(Reply reply) {
        executeUpdate("delete from t_reply where id =?",reply.getId());
    }

    @Override
    public List<Reply> selectReplyByTopicId(Integer id) {
        String sql = "select * from t_reply where topic = ?";
        List<Reply> replyList = super.executeQuery(sql, id);
        return replyList;
    }
}
