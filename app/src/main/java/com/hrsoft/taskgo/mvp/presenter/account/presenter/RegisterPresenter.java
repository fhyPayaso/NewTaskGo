package com.hrsoft.taskgo.mvp.presenter.account.presenter;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.mvp.model.account.helper.AccountHelper;
import com.hrsoft.taskgo.mvp.model.account.helper.RegisterHelper;
import com.hrsoft.taskgo.mvp.model.account.request.CheckCaptchaModel;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;
import com.hrsoft.taskgo.mvp.model.account.request.RegisterReqModel;
import com.hrsoft.taskgo.mvp.presenter.account.contract.RegisterContract;
import com.hrsoft.taskgo.utils.CacheUtil;
import com.hrsoft.taskgo.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author FanHongyu.
 * @since 18/5/3 19:14.
 * email fanhongyu@hrsoft.net.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    /**
     * P层构造方法;
     * 创建P层时就将P和V进行双向绑定
     *
     */
    public RegisterPresenter(RegisterContract.View mview) {
        super(mview);
    }


    @Override
    public void loginAgain(LoginReqModel loginReqModel) {

    }

    @Override
    public void getCaptcha(String mobile) {

        if(isPhoneTrue(mobile)){
            IDataCallback.Callback<String> callback=new IDataCallback.Callback<String>() {


                @Override
                public void onFailedLoaded(String error) {
                    mView.onError(error);
                }

                @Override
                public void onDataLoaded(String s) {
                    mView.onGetCaptchaSuccess();
                }
            };

            RegisterHelper.getInstance().getCaptcha(this,mobile,callback);

        }
    }

    @Override
    public void checkCaptcha(CheckCaptchaModel loginRequestModel) {



    }

    @Override
    public void register(RegisterReqModel registerReqModel, String repeatPassword) {

        if (isSecretTrue(registerReqModel,repeatPassword)) {

            IDataCallback.Callback<String> callback = new IDataCallback.Callback<String>() {
                @Override
                public void onFailedLoaded(String error) {
                    mView.onError(error);
                }

                @Override
                public void onDataLoaded(String s) {
                    CacheUtil.putString(CacheKey.TOKEN, s);
                    mView.onRegisterSucess(s);
                }
            } ;
            RegisterHelper.getInstance().requestRegister(this, registerReqModel, callback);
        }
    }

    /**
     * 验证四个数据是否正确
     *
     * @return
     */
    private boolean isSecretTrue(RegisterReqModel registerReqModel,String repeatPassword) {

        boolean flag = true;

        if(registerReqModel.getMobile().equals(Config.EMPTY_FIELD)){
            ToastUtil.showToast("输入的账号格式不正确");
            flag = false;
        }else if(registerReqModel.getCaptcha().equals(Config.EMPTY_FIELD)){
            ToastUtil.showToast("验证码不能为空");
            flag = false;
        }
        else if(!registerReqModel.getPassword().equals(repeatPassword)){
            ToastUtil.showToast("两次密码输入不一致");
            flag = false;
        }
        else if((repeatPassword.equals(Config.EMPTY_FIELD))||(registerReqModel.getPassword().equals(Config.EMPTY_FIELD))){
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
