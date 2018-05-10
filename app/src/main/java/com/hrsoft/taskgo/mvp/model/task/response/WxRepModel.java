package com.hrsoft.taskgo.mvp.model.task.response;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * @author fhyPayaso
 * @since 2018/5/4 on 上午12:57
 * fhyPayaso@qq.com
 */
public class WxRepModel extends BaseBean{



    private String appid;
    private String partnerid;
    private String prepayid;
    @SerializedName("package")
    private String packageName;
    private String noncestr;
    private String timestamp;
    private String sign;
    private int taskId = -1;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }




    @Override
    public boolean checkValue() {


        return true;
    }
}
