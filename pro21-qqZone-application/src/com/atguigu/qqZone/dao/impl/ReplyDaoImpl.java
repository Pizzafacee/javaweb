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
}
