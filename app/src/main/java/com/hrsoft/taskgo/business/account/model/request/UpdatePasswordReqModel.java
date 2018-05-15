package com.hrsoft.taskgo.business.account.model.request;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * 更新密码请求体
 *
 * @author heaijia
 * @since 2018/5/5 下午9:56
 * email 549044363@qq.com
 */

public class UpdatePasswordReqModel extends BaseBean {


    private String mobile;
    private String captcha;
    @SerializedName("new_password")
    private String newPassword;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public UpdatePasswordReqModel(String mobile, String captcha, String newPassword) {

        this.mobile = mobile;
        this.captcha = captcha;
        this.newPassword = newPassword;
    }


    @Override
    public boolean checkValue() {
        return false;
    }
}
