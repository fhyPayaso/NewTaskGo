package com.hrsoft.taskgo.business.account.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.account.model.request.RegisterReqModel;

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
         * 注册请求
         *
         * @param registerRespModel
         * @param repeatPassword
         */
        void register(RegisterReqModel registerRespModel, String repeatPassword);
    }


    interface View extends IBaseContract.IBaseView {


        /**
         * 开启验证码倒计时
         */
        void startTimer();

        /**
         * 获取验证码成功
         */
        void getCaptchaSuccess();


        /**
         * 获取验证码失败
         */
        void getCaptchaError(String error);


        /**
         * 注册成功
         */
        void onRegisterSuccess();


        /**
         * 注册失败回调
         *
         * @param error
         */
        void onRegisterError(String error);


        void showDialog();

    }
}
