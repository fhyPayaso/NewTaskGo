package com.hrsoft.taskgo.mvp.contract.account;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.account.request.UpdatePasswordReqModel;

/**
 * @author heaijia
 * @since 2018/5/6 下午2:19
 * email 549044363@qq.com
 */

public interface ForgetPasswordContract {

    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 更新密码
         *
         * @param updatePasswordReqModel
         * @param repeatPassword
         */
        void updatePassWord(UpdatePasswordReqModel updatePasswordReqModel, String repeatPassword);


        /**
         * 获取验证码
         *
         * @param mobile
         */
        void getCaptcha(String mobile);
    }

    interface View extends IBaseContract.IBaseView {


        void onGetCaptchaSuccess();


        void onGetCaptchaError(String error);


        void onUpdatePasswordSuccess();


        void onUpdatePasswordError(String error);

        void showDialog();
    }
}
