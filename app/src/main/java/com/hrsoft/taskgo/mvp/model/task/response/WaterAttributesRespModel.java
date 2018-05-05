package com.hrsoft.taskgo.mvp.model.task.response;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * 送水任务详细信息相应体
 *
 * @author fhyPayaso
 * @since 2018/5/4 on 上午8:04
 * fhyPayaso@qq.com
 */
public class WaterAttributesRespModel extends BaseBean {


    /**
     * 送水金额
     */
    @SerializedName("fee")
    private Integer money;

    /**
     * 送水类型
     */
    @SerializedName("send")
    private String sendType;


    /**
     * 任务状态
     */
    private String status;

    /**
     * 宿舍地址
     */
    private String address;


    /**
     * 公寓楼号
     */
    private String apartment;

    /**
     * 支付状态
     */
    @SerializedName("pay_status")
    private Integer payStatus;


    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public boolean checkValue() {

        return true;
    }
}
