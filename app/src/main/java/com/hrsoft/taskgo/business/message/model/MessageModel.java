package com.hrsoft.taskgo.business.message.model;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * @author FanHongyu.
 * @since 18/5/12 14:38.
 * email fanhongyu@hrsoft.net.
 */

public class MessageModel extends BaseBean {


    /**
     * id : 2
     * senderID : 2
     * receiverId : 2
     * content : 你的水订单已被接取！请注意查看
     * createdAt : 2018-05-12 06:37:46
     * status : 0
     * avatar : http://oz3rf0wt0.bkt.clouddn.com/18-1-22/15799237.jpg
     * name : 15076035390
     */
    @SerializedName("id")
    private int id;
    @SerializedName("from")
    private int senderID;
    @SerializedName("receiverId")
    private int receiverId;
    private String content;
    @SerializedName("created_at")
    private String createdAt;
    private int status;
    private String avatar;
    @SerializedName("name")
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean hasRead() {
        return status == 1;
    }

    public void setHasRead(boolean hasRead) {
        if (hasRead) {
            this.status = 1;
        } else {
            this.status = 0;
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }


    @Override
    public boolean checkValue() {
        return false;
    }

}
