package com.hrsoft.taskgo.mvp.presenter.account;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.INotifyListener;
import com.hrsoft.taskgo.base.mvp.presenter.IBasePresenter;
import com.hrsoft.taskgo.base.mvp.view.IBaseView;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:58.
 * email fanhongyu@hrsoft.net.
 */

public interface LoginContract {


    interface Presenter extends IBasePresenter, IDataCallback {

        void login(String username, String password);
    }


    interface View extends IBaseView {

        void onLoginSuccess();

        void onLoginFailed(String error);

        void hideProgress();

        void showProgress();
    }
}
