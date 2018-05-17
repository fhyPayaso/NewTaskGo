package com.hrsoft.taskgo.business.mine.model.response;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;

/**
 * @author：lszr on 2018/5/7 20:53
 * @email：1085963811@qq.com
 */
public class MineInformationModel extends BaseBean{
    private String name;
    private String id;
    private String mobile;
    private String avatar;
    private String sex;
    private String role;
    @SerializedName("id_pic_p")
    private String facedeOfID;
    @SerializedName("id_pic_n")
    private String backOfID;
    @SerializedName("true_name")
    private String trueName;
    @SerializedName("bank_card")
    private String bankCard;
    @SerializedName("bank_card_name")
    private String bankCardName;
    private int level;
    private int exp;
    private int balance;
    private int status;
    private int bindwx;
    @SerializedName("followers_count")
    private int fansCount;
    @SerializedName("followings_count")
    private int followCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFacedeOfID() {
        return facedeOfID;
    }

    public void setFacedeOfID(String facedeOfID) {
        this.facedeOfID = facedeOfID;
    }

    public String getBackOfID() {
        return backOfID;
    }

    public void setBackOfID(String backOfID) {
        this.backOfID = backOfID;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBindwx() {
        return bindwx;
    }

    public void setBindwx(int bindwx) {
        this.bindwx = bindwx;
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


    @Override
    public boolean checkValue() {
        return false;
    }
}
