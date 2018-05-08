package com.hrsoft.taskgo.mvp.model.task.response;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.mvp.model.task.bean.CardPackageModel;

/**
 * 任务列表相应信息
 *
 * @author fhyPayaso
 * @since 2018/5/4 on 上午8:11
 * fhyPayaso@qq.com
 */
public class TasListRespModel<T> {

    /**
     * 任务id
     */
    private Integer id;

    /**
     * 用户id
     */
    @SerializedName("user_id")
    private Integer userId;


    /**
     * 任务类型
     */
    private String type;


    /**
     * 卡片信息
     */
    @SerializedName("cards")
    private CardPackageModel mCardsModel;


    /**
     * 任务特有信息
     */
    private T attributes;


    @SerializedName("out_trade_no")
    private String outTradeNo;


    @SerializedName("pre_pay")
    private String prePay;


    /**
     * 任务创建时间
     */
    @SerializedName("create_at")
    private String createAt;

    /**
     * 任务发布者
     */
    @SerializedName("name")
    private String userName;

    /**
     * 任务头像url
     */
    @SerializedName("avatar")
    private String avatarImgUrl;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarImgUrl() {
        return avatarImgUrl;
    }

    public void setAvatarImgUrl(String avatarImgUrl) {
        this.avatarImgUrl = avatarImgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CardPackageModel getCardsModel() {
        return mCardsModel;
    }

    public void setCardsModel(CardPackageModel cardsModel) {
        this.mCardsModel = cardsModel;
    }

    public T getAttributes() {
        return attributes;
    }

    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPrePay() {
        return prePay;
    }

    public void setPrePay(String prePay) {
        this.prePay = prePay;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
