package com.hrsoft.taskgo.mvp.model.mine.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author：lszr on 2018/5/9 18:10
 * @email：1085963811@qq.com
 */
public class MyFollowFansModel {
    @SerializedName("user_id")
    private int userId;
    private String name;
    @SerializedName("follower_id")
    private int fansId;

    public MyFollowFansModel(String name, int fansId, String avatar) {
        this.name = name;
        this.fansId = fansId;
        this.avatar = avatar;
    }

    public MyFollowFansModel(int userId, String name, String avatar) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
    }

    public int getFansId() {
        return fansId;
    }

    public void setFansId(int fansId) {
        this.fansId = fansId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }



    private String avatar;

}
