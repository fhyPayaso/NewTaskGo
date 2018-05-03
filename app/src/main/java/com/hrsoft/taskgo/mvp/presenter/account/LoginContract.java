package com.hrsoft.taskgo.mvp.presenter.account;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:58.
 * email fanhongyu@hrsoft.net.
 */

public interface LoginContract {


    interface Presenter extends IBaseContract.IBasePresenter {

        void login(String username, String password);
    }


    interface View extends IBaseContract.IBaseView{

        void onLoginSuccess();

        void onLoginFailed(String error);

        void hideProgress();

        void showProgress();
    }
}
