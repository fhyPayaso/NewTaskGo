package com.hrsoft.taskgo.mvp.model.account.request;

import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * 登录请求体
 *
 * @author FanHongyu.
 * @since 18/4/23 20:01.
 * email fanhongyu@hrsoft.net.
 */

public class LoginReqModel extends BaseBean {


    private String mobile;
    private String password;


    public LoginReqModel(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
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

    @Override
    protected void checkValue() {

    }
}
