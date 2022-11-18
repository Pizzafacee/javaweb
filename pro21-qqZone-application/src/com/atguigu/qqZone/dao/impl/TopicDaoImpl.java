package com.atguigu.qqZone.dao.impl;

import com.atguigu.myssm.baseDao.BaseDAO;
import com.atguigu.qqZone.dao.TopicDao;
import com.atguigu.qqZone.pojo.Topic;
import com.atguigu.qqZone.pojo.UserBasic;

import java.util.List;

public class TopicDaoImpl extends BaseDAO<Topic> implements TopicDao {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        String sql = "select id,title,content,topicDate,author from t_topic where author = ? ";
        List<Topic> topics = super.executeQuery(sql, userBasic.getId());
        return topics;
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public Topic getTopicById(Integer id) {
        String sql = "select * from t_topic where id=?";
        return super.load(sql,id);
    }
}
