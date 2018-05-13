package com.hrsoft.taskgo.mvp.presenter.account;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.mvp.model.account.helper.AccountHelper;
import com.hrsoft.taskgo.mvp.model.account.request.RegisterReqModel;
import com.hrsoft.taskgo.mvp.contract.account.RegisterContract;
import com.hrsoft.taskgo.utils.ToastUtil;

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
            AccountHelper.getInstance().register(this, registerRespModel,callback);
        }
    }

    /**
     * 验证四个数据是否正确
     *
     * @return
     */
    private boolean isSecretTrue(RegisterReqModel registerRespModel, String repeatPassword) {

        boolean flag = true;

        if (registerRespModel.getMobile().equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast("输入的账号格式不正确");
            flag = false;
        } else if (registerRespModel.getCaptcha().equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast("验证码不能为空");
            flag = false;
        } else if (!registerRespModel.getPassword().equals(repeatPassword)) {
            ToastUtil.showToast("两次密码输入不一致");
            flag = false;
        } else if ((repeatPassword.equals(Config.EMPTY_FIELD)) || (registerRespModel.getPassword().equals(Config
                .EMPTY_FIELD))) {
            ToastUtil.showToast("两次输入密码不能为空");
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

        if (phoneNumber.equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast("输入的账号格式不正确");
            flag = false;
        }
        return flag;
    }


}
