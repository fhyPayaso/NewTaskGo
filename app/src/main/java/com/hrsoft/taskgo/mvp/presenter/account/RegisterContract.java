package com.hrsoft.taskgo.mvp.presenter.account;

import com.hrsoft.taskgo.base.mvp.IBaseContract;

/**
 * @author FanHongyu.
 * @since 18/5/3 18:58.
 * email fanhongyu@hrsoft.net.
 */

public interface RegisterContract {


    interface Presenter extends IBaseContract.IBasePresenter {


        /**
         * 获取验证码
         *
         * @param phoneNumber
         */
        void getCaptcha(String phoneNumber);


        /**
         * 核对验证码
         *
         * @param phoneNumber
         * @param captcha
         */
        void checkCaptcha(String phoneNumber, String captcha);


        /**
         * 注册
         *
         * @param phoneNumber
         * @param password
         */
        void register(String phoneNumber, String password);
    }


    interface View extends IBaseContract.IBaseView {


        /**
         * 获取验证码成功
         */
        void onGetCaptchaSuccess();


        /**
         * 核对验证码成功
         */
        void onCheckCaptchaError();


        /**
         * 注册成功
         */
        void onRegisterSucess();


        /**
         * 失败回调
         *
         * @param error
         */
        void onError(String error);

    }
}
