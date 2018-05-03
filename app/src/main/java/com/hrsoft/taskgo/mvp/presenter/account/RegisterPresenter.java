package com.hrsoft.taskgo.mvp.presenter.account;

import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;

/**
 * @author FanHongyu.
 * @since 18/5/3 19:14.
 * email fanhongyu@hrsoft.net.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {


    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void getCaptcha(String phoneNumber) {

    }

    @Override
    public void checkCaptcha(String phoneNumber, String captcha) {

    }

    @Override
    public void register(String phoneNumber, String password) {

    }
}
