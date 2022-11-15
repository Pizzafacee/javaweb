package com.atguigu.qqZone.pojo;

import java.util.Date;

public class Reply {
    private Integer id;
    private String content;
    private Date replyDate;
    private Integer author;
    private Integer topic;

    private HostReply hostReply;

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getTopic() {
        return topic;
    }

    public void setTopic(Integer topic) {
        this.topic = topic;
    }

    public Reply() {
    }
}

