package com.hrsoft.taskgo.mvp.presenter.account.presenter;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.mvp.model.account.helper.ForgetpasswordHelper;
import com.hrsoft.taskgo.mvp.model.account.helper.RegisterHelper;
import com.hrsoft.taskgo.mvp.model.account.request.ForgetPasswordModel;
import com.hrsoft.taskgo.mvp.model.account.request.RegisterReqModel;
import com.hrsoft.taskgo.mvp.presenter.account.contract.ForgetPasswordContract;
import com.hrsoft.taskgo.utils.CacheUtil;
import com.hrsoft.taskgo.utils.RegexpUtils;
import com.hrsoft.taskgo.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author heaijia
 * @since 2018/5/6 下午2:19
 * email 549044363@qq.com
 */

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordContract.View> implements ForgetPasswordContract.Presenter {

    public ForgetPasswordPresenter(ForgetPasswordContract.View view) {
        super(view);
    }

    @Override
    public void sendRequestNewInformation(ForgetPasswordModel forgetPasswordModel, String repeatPassword) {
        if (isInformationTrue(forgetPasswordModel,repeatPassword)) {
            final IDataCallback.Callback<String> callback = new IDataCallback.Callback<String>() {


                @Override
                public void onFailedLoaded(String error) {
                    mView.onError(error);
                }

                @Override
                public void onDataLoaded(String s) {
                    CacheUtil.putString(CacheKey.TOKEN, s);
                    mView.onSendInformationSuccess();
                }
            };
            ForgetpasswordHelper.getInstance().requestNewInformation(this, forgetPasswordModel, callback);
        }
    }

    @Override
    public void getCaptchato(String mobile) {
        if(isPhoneTrue(mobile)){

            IDataCallback.Callback<String> callback=new IDataCallback.Callback<String>() {

                @Override
                public void onFailedLoaded(String error) {
                    mView.onError(error);
                }

                @Override
                public void onDataLoaded(String s) {
                    mView.onSendInformationSuccess();
                }
            };
            ForgetpasswordHelper.getInstance().getCaptcha(this,mobile,callback);
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
    private boolean isInformationTrue(ForgetPasswordModel forgetPasswordModel, String repeatPassword) {

        boolean flag = true;

        if(forgetPasswordModel.getMobile().equals(Config.EMPTY_FIELD)){
            ToastUtil.showToast("输入的账号格式不正确");
            flag = false;
        } else if (!RegexpUtils.checkMobile(forgetPasswordModel.getMobile())) {
            ToastUtil.showToast("账号个数有误，请重新输入");
            flag = false;
        } else if (forgetPasswordModel.getNew_password().length() < Config.PASSWORD_MIN) {
            ToastUtil.showToast("请输入6位以上的密码");
            flag = false;
        } else if (forgetPasswordModel.getNew_password().length() > Config.PASSWORD_MAX) {
            ToastUtil.showToast("密码位数最多不能超过20位");
            flag = false;
        }else if(forgetPasswordModel.getCaptcha().equals(Config.EMPTY_FIELD)){
            ToastUtil.showToast("验证码不能为空");
            flag = false;
        }
        else if(!forgetPasswordModel.getNew_password().equals(repeatPassword)){
            ToastUtil.showToast("两次密码输入不一致");
            flag = false;
        }
        else if((repeatPassword.equals(Config.EMPTY_FIELD))||(forgetPasswordModel.getNew_password().equals(Config.EMPTY_FIELD))){
            ToastUtil.showToast("两次输入密码不能为空");
            flag = false;
        }
        return flag;
    }
}
