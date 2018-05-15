package com.hrsoft.taskgo.business.account.presenter;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.business.account.model.AccountHelper;
import com.hrsoft.taskgo.business.account.model.request.RegisterReqModel;
import com.hrsoft.taskgo.business.account.contract.RegisterContract;

/**
 * @author FanHongyu.
 * @since 18/5/3 19:14.
 * email fanhongyu@hrsoft.net.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    public RegisterPresenter(RegisterContract.View mview) {
        super(mview);
    }

    @Override
    public void getCaptcha(String mobile) {

        if (isPhoneTrue(mobile)) {
            mView.startTimer();
            IDataCallback.Callback callback = new IDataCallback.Callback() {
                @Override
                public void onFailedLoaded(String error) {
                    mView.getCaptchaError(error);
                }

                @Override
                public void onDataLoaded(Object o) {
                    mView.getCaptchaSuccess();
                }
            };
            AccountHelper.getInstance().getCaptcha(this, mobile, callback);
        }
    }


    @Override
    public void register(RegisterReqModel registerRespModel, String repeatPassword) {

        if (isSecretTrue(registerRespModel, repeatPassword)) {

            mView.showDialog();
            IDataCallback.Callback<String> callback = new IDataCallback.Callback<String>() {
                @Override
                public void onFailedLoaded(String error) {
                    mView.onRegisterError(error);
                }

                @Override
                public void onDataLoaded(String s) {
                    App.getInstance().getCacheUtil().putString(CacheKey.TOKEN, s);
                    mView.onRegisterSuccess();
                }
            };
            AccountHelper.getInstance().register(this, registerRespModel, callback);
        }
    }

    /**
     * 验证四个数据是否正确
     *
     * @return
     */
    private boolean isSecretTrue(RegisterReqModel registerRespModel, String repeatPassword) {

        boolean flag = true;

        if (Config.EMPTY_FIELD.equals(registerRespModel.getMobile())) {
            mView.onRegisterError("输入的账号格式不正确");
            flag = false;
        } else if (Config.EMPTY_FIELD.equals(registerRespModel.getCaptcha())) {
            mView.onRegisterError("验证码不能为空");
            flag = false;
        } else if (!registerRespModel.getPassword().equals(repeatPassword)) {
            mView.onRegisterError("两次密码输入不一致");
            flag = false;
        } else if ((Config.EMPTY_FIELD.equals(repeatPassword)) || Config.EMPTY_FIELD.equals(registerRespModel
                .getPassword())) {
            mView.onRegisterError("输入密码不能为空");
            flag = false;
        }
        return flag;
    }


    /**
     * 验证电话数据是否正确
     *
     * @return
     */
    private boolean isPhoneTrue(String phoneNumber) {

        boolean flag = true;
        // TODO: 18/5/14 细节格式判断
        if (phoneNumber.equals(Config.EMPTY_FIELD)) {
            mView.getCaptchaError("输入的账号格式不正确");
            flag = false;
        }
        return flag;
    }
}
