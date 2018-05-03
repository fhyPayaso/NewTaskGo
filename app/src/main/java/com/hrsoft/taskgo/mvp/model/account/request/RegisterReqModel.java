package com.hrsoft.taskgo.mvp.model.account.request;

import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * @author FanHongyu.
 * @since 18/5/3 19:00.
 * email fanhongyu@hrsoft.net.
 */

public class RegisterReqModel extends BaseBean{


    private String phoneNumber;
    private String password;


    public RegisterReqModel(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
