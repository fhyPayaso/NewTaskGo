package com.hrsoft.taskgo.mvp.model.mine.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author：lszr on 2018/5/12 11:00
 * @email：1085963811@qq.com
 */
public class RealNameModel {
    @SerializedName("id_pic_p")
    private String idCardFront;
    @SerializedName("id_pic_n")
    private String idCardBack;
    @SerializedName("true_name")
    private String trueName;

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public RealNameModel(String idCardFront, String idCardBack, String trueName) {

        this.idCardFront = idCardFront;
        this.idCardBack = idCardBack;
        this.trueName = trueName;
    }
}
