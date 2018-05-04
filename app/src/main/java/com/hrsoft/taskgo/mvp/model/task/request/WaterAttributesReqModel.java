package com.hrsoft.taskgo.mvp.model.task.request;

/**
 * 送水任务详细信息请求体
 *
 * @author fhyPayaso
 * @since 2018/5/4 on 上午12:53
 * fhyPayaso@qq.com
 */
public class WaterAttributesReqModel {

    private String apartment;
    private String address;
    private String send;


    public WaterAttributesReqModel(String apartment, String address, String send) {
        this.apartment = apartment;
        this.address = address;
        this.send = send;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }
}
