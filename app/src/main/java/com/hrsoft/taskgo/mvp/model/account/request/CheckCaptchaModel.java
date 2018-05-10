package com.hrsoft.taskgo.mvp.model.account.request;

import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * @author heaijia
 * @since 2018/5/5 下午9:49
 * email 549044363@qq.com
 */

public class CheckCaptchaModel  extends BaseBean {

    private String mobile;
    private String captcha;


    public String getMobile() {
        return mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CheckCaptchaModel(String mobile, String captcha) {
        this.mobile = mobile;
        this.captcha = captcha;
    }

    @Override
    protected void checkValue() {

    }
}
