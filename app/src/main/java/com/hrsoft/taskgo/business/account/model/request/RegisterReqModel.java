package com.hrsoft.taskgo.business.account.model.request;

import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * 注册请求体
 *
 * @author FanHongyu.
 * @since 18/5/3 19:00.
 * email fanhongyu@hrsoft.net.
 */

public class RegisterReqModel extends BaseBean {


    private String mobile;
    private String password;
    private String captcha;

    public RegisterReqModel(String mobile, String password, String captcha) {
        this.mobile = mobile;
        this.password = password;
        this.captcha = captcha;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public boolean checkValue() {

        return true;
    }
}
