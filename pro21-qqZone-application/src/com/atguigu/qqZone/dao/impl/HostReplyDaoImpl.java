package com.atguigu.qqZone.dao.impl;

import com.atguigu.myssm.baseDao.BaseDAO;
import com.atguigu.qqZone.dao.HostReplyDao;
import com.atguigu.qqZone.pojo.HostReply;

public class HostReplyDaoImpl extends BaseDAO<HostReply> implements HostReplyDao {
    @Override
    public HostReply selectHostReplyById(Integer id) {
        String sql = "select * from t_host_reply where reply = ?";
        HostReply load = super.load(sql, id);
        return load;
    }

    @Override
    public void deleteById(Integer id) {
        executeUpdate("delete from t_host_reply where id=?",id);
    }

    @Override
    public void deleteHostReplyByReplyId(Integer id) {
        String sql = "delete from t_host_reply where reply = ?";
        executeUpdate(sql,id);
    }
}
