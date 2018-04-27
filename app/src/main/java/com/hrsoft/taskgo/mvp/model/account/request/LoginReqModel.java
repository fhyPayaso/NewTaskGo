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


    private String username;
    private String password;


    public LoginReqModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
