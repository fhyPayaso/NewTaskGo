package com.hrsoft.taskgo.mvp.model.mine.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author：lszr on 2018/5/10 21:59
 * @email：1085963811@qq.com
 */
public class OtherUserPageModel {
    private String name;
    private String mobile;
    private String avatar;
    private String sex;
    @SerializedName("followers_count")
    private int fansCount;
    @SerializedName("followings_count")
    private int followCount;
    @SerializedName("is_following")
    private Boolean isFollowing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public Boolean getFollowing() {
        return isFollowing;
    }

    public void setFollowing(Boolean following) {
        isFollowing = following;
    }

    public OtherUserPageModel(String name, String mobile, String avatar, String sex, int fansCount, int followCount, Boolean isFollowing) {

        this.name = name;
        this.mobile = mobile;
        this.avatar = avatar;
        this.sex = sex;
        this.fansCount = fansCount;
        this.followCount = followCount;
        this.isFollowing = isFollowing;
    }
}
