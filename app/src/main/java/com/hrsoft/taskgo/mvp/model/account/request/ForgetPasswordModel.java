package com.hrsoft.taskgo.mvp.model.account.request;

import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * @author heaijia
 * @since 2018/5/5 下午9:56
 * email 549044363@qq.com
 */

public class ForgetPasswordModel extends BaseBean {


    private String mobile;
    private String captcha;
    private String new_password;


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

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public ForgetPasswordModel(String mobile, String captcha, String new_password) {

        this.mobile = mobile;
        this.captcha = captcha;
        this.new_password = new_password;
    }







    @Override
    protected void checkValue() {

    }
}
