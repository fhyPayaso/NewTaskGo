package com.hrsoft.taskgo.business.account.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.account.model.request.LoginReqModel;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:58.
 * email fanhongyu@hrsoft.net.
 */

public interface LoginContract {


    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 登录账户
         *
         * @param loginReqModel
         */
        void login(LoginReqModel loginReqModel);
    }


    interface View extends IBaseContract.IBaseView {

        /**
         * 登录成功
         *
         * @param token
         */
        void onLoginSuccess(String token);

        /**
         * 登录失败
         *
         * @param error
         */
        void onLoginFailed(String error);


        /**
         * 显示dialog
         */
        void showDialog();

    }
}
