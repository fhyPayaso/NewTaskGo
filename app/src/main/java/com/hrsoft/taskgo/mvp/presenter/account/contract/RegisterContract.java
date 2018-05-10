package com.hrsoft.taskgo.mvp.presenter.account.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.account.request.CheckCaptchaModel;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;
import com.hrsoft.taskgo.mvp.model.account.request.RegisterReqModel;

/**
 * @author FanHongyu.
 * @since 18/5/3 18:58.
 * email fanhongyu@hrsoft.net.
 */

public interface RegisterContract {


    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 注册成功回调，直接调用登录接口
         */
        void loginAgain(LoginReqModel loginReqModel);

        /**
         * 获取验证码
         *
         * @param phoneNumber
         */
        void getCaptcha(String phoneNumber);


        /**
         * 核对验证码
         *
         */
        void checkCaptcha(CheckCaptchaModel loginRequestModel);


        /**
         * 注册

         */
        void register(RegisterReqModel registerReqModel, String repeatPassword);
    }


    interface View extends IBaseContract.IBaseView {


        /**
         * 获取验证码成功
         */
        void onGetCaptchaSuccess();


        /**
         * 核对验证码成功
         */
        void onCheckCaptchaError(String Token);


        /**
         * 注册成功
         */
        void onRegisterSucess(String token);


        /**
         * 失败回调
         *
         * @param error
         */
        void onError(String error);

        /**
         * 直接登录成功回调，缓存token
         */
        void onLoginAgainSuccess(String Token);


    }
}
