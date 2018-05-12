package com.hrsoft.taskgo.mvp.presenter.account.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:58.
 * email fanhongyu@hrsoft.net.
 */

public interface LoginContract {


    interface Presenter extends IBaseContract.IBasePresenter {

        void login(LoginReqModel loginReqModel);
    }


    interface View extends IBaseContract.IBaseView{

        void onLoginSuccess(String token);

        void onLoginFailed(String error);

        void onWriteFailed(String showError);

    }
}
