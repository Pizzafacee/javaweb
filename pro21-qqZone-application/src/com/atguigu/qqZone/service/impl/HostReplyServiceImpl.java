package com.atguigu.qqZone.service.impl;

import com.atguigu.qqZone.dao.HostReplyDao;
import com.atguigu.qqZone.pojo.HostReply;
import com.atguigu.qqZone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDao hostReplyDao;

    @Override
    public HostReply getHostReplyByReplyId(Integer id) {
        return hostReplyDao.selectHostReplyById(id);
    }
}
