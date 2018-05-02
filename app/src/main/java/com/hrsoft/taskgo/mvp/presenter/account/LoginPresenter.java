package com.hrsoft.taskgo.mvp.presenter.account;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.model.account.helper.AccountHelper;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;

/**
 * @author FanHongyu.
 * @since 18/4/23 21:13.
 * email fanhongyu@hrsoft.net.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract
        .Presenter{

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    protected void unBindModel() {
    }

    @Override
    public void login(String username, String password) {


        if ("".equals(username)) {
            mView.onLoginFailed("用户名不能为空");
        } else if ("".equals(password)) {
            mView.onLoginFailed("密码不能为空");
        } else {
            mView.showProgress();



            //getModel().login(new LoginReqModel(username, password), logiCallback);
        }
    }

    private IDataCallback.Callback<String> logiCallback= new IDataCallback.Callback<String>() {

        @Override
        public void onFailedLoaded(String error) {

            mView.onLoginFailed(error);
            mView.hideProgress();

        }

        @Override
        public void onDataLoaded(String s) {
            mView.onLoginSuccess();
            mView.hideProgress();
        }
    };
}
