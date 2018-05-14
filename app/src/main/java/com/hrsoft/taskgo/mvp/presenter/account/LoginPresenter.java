package com.hrsoft.taskgo.mvp.presenter.account;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.mvp.model.account.AccountHelper;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;
import com.hrsoft.taskgo.mvp.contract.account.LoginContract;
import com.hrsoft.taskgo.utils.RegexpUtils;

/**
 * @author FanHongyu.
 * @since 18/4/23 21:13.
 * email fanhongyu@hrsoft.net.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    protected void unBindModel() {
    }

    /**
     * 验证数据是否正确
     *
     * @return
     */
    private boolean isDataTrue(LoginReqModel loginRequestModel) {

        boolean flag = true;
        if (Config.EMPTY_FIELD.equals(loginRequestModel.getMobile())) {
            mView.onLoginFailed("账号不可为空");
            flag = false;
        } else if (!RegexpUtils.checkMobile(loginRequestModel.getMobile())) {
            mView.onLoginFailed("账号有误，请重新输入");
            flag = false;
        } else if (loginRequestModel.getPassword().length() < Config.PASSWORD_MIN) {
            mView.onLoginFailed("请输入6位以上的密码");
            flag = false;
        } else if (loginRequestModel.getPassword().length() > Config.PASSWORD_MAX) {
            mView.onLoginFailed("密码位数最多不能超过20位");
            flag = false;
        }
        return flag;
    }

    @Override
    public void login(LoginReqModel loginReqModel) {

        if (isDataTrue(loginReqModel)) {
            mView.showDialog();
            IDataCallback.Callback<String> callback = new IDataCallback.Callback<String>() {
                @Override
                public void onFailedLoaded(String error) {
                    mView.onLoginFailed(error);
                }

                @Override
                public void onDataLoaded(String s) {
                    App.getInstance().getCacheUtil().putString(CacheKey.TOKEN, s);
                    mView.onLoginSuccess(s);
                }
            };
            AccountHelper.getInstance().login(this, loginReqModel, callback);
        }
    }

}
