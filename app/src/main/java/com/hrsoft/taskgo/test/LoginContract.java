package com.hrsoft.taskgo.test;

import com.hrsoft.taskgo.base.mvp.view.IBaseView;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:58.
 * email fanhongyu@hrsoft.net.
 */

public interface LoginContract {


    interface Presenter {

        void login(String username, String password);
    }


    interface View extends IBaseView{

        void onLoginSuccess();

        void onLoginFailed(String error);

        void hideProgress();

        void showProgress();
    }
}
