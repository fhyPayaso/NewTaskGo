package com.hrsoft.taskgo.business.mine.model.request;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * @author FanHongyu.
 * @since 18/6/7 19:07.
 * email fanhongyu@hrsoft.net.
 */

public class BindBankCardModel extends BaseBean {

    /**
     * 银行卡号
     */
    @SerializedName("bankCard_id")
    private String bankCardId;
    /**
     * 银行卡用户名
     */
    @SerializedName("bankCard_userName")
    private String bankCardUserName;

    /**
     * 银行卡类型

     */
    @SerializedName("bankCard_type")
    private String bankType;


    public BindBankCardModel(String bankCardId, String bankCardUserName, String bankType) {
        this.bankCardId = bankCardId;
        this.bankCardUserName = bankCardUserName;
        this.bankType = bankType;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getBankCardUserName() {
        return bankCardUserName;
    }

    public void setBankCardUserName(String bankCardUserName) {
        this.bankCardUserName = bankCardUserName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }


    @Override
    public boolean checkValue() {
        return false;
    }
}
