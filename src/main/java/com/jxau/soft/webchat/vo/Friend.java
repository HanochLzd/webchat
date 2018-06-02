package com.jxau.soft.webchat.vo;

import java.util.Date;

/**
 * @author Hanoch
 */
public class Friend {
    /*
      select
        r.owner_user,u.user_id friend_id,u.user_nick_name friend_nickName,r.group,u.user_profilehead,u.user_login_status
      from
        tb_user u,tb_relation r
      where
        u.user_id=r.friend_user and owner_user='lzd';
     */
    /**
     * 朋友id
     */
    private String friendId;
    /**
     * friend昵称
     */
    private String friendNickName;

    /**
     * friend所属群组
     */
    private String group;
    /**
     * friend头像lujing
     */
    private String friendProfileHead;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 好友等级
     */
    private Integer friendLelvel;

    /**
     * friend登陆状态
     */
    private Integer friendLoginStatus;

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
    }

    public String getFriendProfileHead() {
        return friendProfileHead;
    }

    public void setFriendProfileHead(String friendProfileHead) {
        this.friendProfileHead = friendProfileHead;
    }

    public Integer getFriendLoginStatus() {
        return friendLoginStatus;
    }

    public void setFriendLoginStatus(Integer friendLoginStatus) {
        this.friendLoginStatus = friendLoginStatus;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFriendLelvel() {
        return friendLelvel;
    }

    public void setFriendLelvel(Integer friendLelvel) {
        this.friendLelvel = friendLelvel;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendId='" + friendId + '\'' +
                ", friendNickName='" + friendNickName + '\'' +
                ", group='" + group + '\'' +
                ", friendProfileHead='" + friendProfileHead + '\'' +
                ", createTime=" + createTime +
                ", friendLelvel=" + friendLelvel +
                ", friendLoginStatus=" + friendLoginStatus +
                '}';
    }
}
