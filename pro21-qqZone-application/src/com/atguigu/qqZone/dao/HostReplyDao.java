package com.atguigu.qqZone.dao;

import com.atguigu.qqZone.pojo.HostReply;

public interface HostReplyDao {
    HostReply selectHostReplyById(Integer id);

    void deleteById(Integer id);

    void deleteHostReplyByReplyId(Integer id);
}
