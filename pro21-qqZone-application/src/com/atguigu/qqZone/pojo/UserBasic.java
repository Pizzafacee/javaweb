package com.atguigu.qqZone.pojo;

import java.io.Serializable;
import java.util.List;

public class UserBasic implements Serializable {
    private Integer id;
    private String loginId;
    private String nickName;
    private String pwd;
    private String headImg;


    private UserDetail userDetail;
    private List<Topic> topicList;

    public UserBasic(Integer id) {
        this.id = id;
    }

    private List<UserBasic> friendList;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<UserBasic> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserBasic> friendList) {
        this.friendList = friendList;
    }

    public UserBasic() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongId() {
        return loginId;
    }

    public void setLongId(String longId) {
        this.loginId = longId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
