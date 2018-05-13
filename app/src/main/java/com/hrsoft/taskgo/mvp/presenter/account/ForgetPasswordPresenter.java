package com.hrsoft.taskgo.mvp.presenter.account;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.mvp.model.account.helper.AccountHelper;
import com.hrsoft.taskgo.mvp.model.account.request.UpdatePasswordReqModel;
import com.hrsoft.taskgo.mvp.contract.account.ForgetPasswordContract;
import com.hrsoft.taskgo.utils.RegexpUtils;
import com.hrsoft.taskgo.utils.ToastUtil;

/**
 * @author heaijia
 * @since 2018/5/6 下午2:19
 * email 549044363@qq.com
 */

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordContract.View> implements
        ForgetPasswordContract.Presenter {

    public ForgetPasswordPresenter(ForgetPasswordContract.View view) {
        super(view);
    }

    @Override
    public void updatePassWord(UpdatePasswordReqModel updatePasswordReqModel, String repeatPassword) {

        if (isInformationTrue(updatePasswordReqModel, repeatPassword)) {

            mView.showDialog();
            IDataCallback.Callback callback = new IDataCallback.Callback() {
                @Override
                public void onFailedLoaded(String error) {
                    mView.onUpdatePasswordError(error);
                }

                @Override
                public void onDataLoaded(Object o) {
                    mView.onUpdatePasswordSuccess();
                }
            };

            AccountHelper.getInstance().updatePassword(this,updatePasswordReqModel,callback);
        }

    }

    @Override
    public void getCaptcha(String mobile) {
        if (isPhoneTrue(mobile)) {
            IDataCallback.Callback callback = new IDataCallback.Callback() {
                @Override
                public void onFailedLoaded(String error) {
                    mView.onGetCaptchaError(error);
                }

                @Override
                public void onDataLoaded(Object o) {
                    mView.onGetCaptchaSuccess();
                }
            };
            AccountHelper.getInstance().getCaptcha(this, mobile, callback);
        }
    }


    private boolean isPhoneTrue(String phoneNumber) {

        boolean flag = true;

        if (phoneNumber.equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast("输入的账号格式不正确");
            flag = false;
        }
        return flag;
    }

    /**
     * 验证四个数据是否正确
     *
     * @return
     */
    private boolean isInformationTrue(UpdatePasswordReqModel updatePasswordReqModel, String repeatPassword) {

        boolean flag = true;

        if (updatePasswordReqModel.getMobile().equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast("输入的账号格式不正确");
            flag = false;
        } else if (!RegexpUtils.checkMobile(updatePasswordReqModel.getMobile())) {
            ToastUtil.showToast("账号个数有误，请重新输入");
            flag = false;
        } else if (updatePasswordReqModel.getNew_password().length() < Config.PASSWORD_MIN) {
            ToastUtil.showToast("请输入6位以上的密码");
            flag = false;
        } else if (updatePasswordReqModel.getNew_password().length() > Config.PASSWORD_MAX) {
            ToastUtil.showToast("密码位数最多不能超过20位");
            flag = false;
        } else if (updatePasswordReqModel.getCaptcha().equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast("验证码不能为空");
            flag = false;
        } else if (!updatePasswordReqModel.getNew_password().equals(repeatPassword)) {
            ToastUtil.showToast("两次密码输入不一致");
            flag = false;
        } else if ((repeatPassword.equals(Config.EMPTY_FIELD)) || (updatePasswordReqModel.getNew_password().equals
                (Config.EMPTY_FIELD))) {
            ToastUtil.showToast("两次输入密码不能为空");
            flag = false;
        }
        return flag;
    }
}
