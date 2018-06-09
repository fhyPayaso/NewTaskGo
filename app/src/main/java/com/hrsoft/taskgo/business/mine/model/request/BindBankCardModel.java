package com.hrsoft.taskgo.business.mine.model.request;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;

import java.io.Serializable;

/**
 * @author FanHongyu.
 * @since 18/6/7 19:07.
 * email fanhongyu@hrsoft.net.
 */

public class BindBankCardModel extends BaseBean {

    /**
     * 银行卡号
     */
    @SerializedName("backCard_id")
    private String bankCradId;
    /**
     * 银行卡用户名
     */
    @SerializedName("bankCard_userName")
    private String bankCradUserName;

    public String getBankCradId() {
        return bankCradId;
    }

    public void setBankCradId(String bankCradId) {
        this.bankCradId = bankCradId;
    }

    public String getBankCradUserName() {
        return bankCradUserName;
    }

    public void setBankCradUserName(String bankCradUserName) {
        this.bankCradUserName = bankCradUserName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public BindBankCardModel(String bankCradId, String bankCradUserName, String bankType) {

        this.bankCradId = bankCradId;
        this.bankCradUserName = bankCradUserName;
        this.bankType = bankType;
    }

    /**
     * 银行卡类型

     */
    @SerializedName("bank_type")
    private String bankType;


    @Override
    public boolean checkValue() {
        return false;
    }
}
