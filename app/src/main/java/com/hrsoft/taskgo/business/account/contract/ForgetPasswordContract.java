package com.hrsoft.taskgo.business.account.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.account.model.request.UpdatePasswordReqModel;

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


        /**
         * 获取验证码成功
         */
        void onGetCaptchaSuccess();

        /**
         * 获取验证码失败
         *
         * @param error
         */
        void onGetCaptchaError(String error);

        /**
         * 重设密码成功
         */
        void onUpdatePasswordSuccess();

        /**
         * 重设密码失败
         *
         * @param error
         */
        void onUpdatePasswordError(String error);

        /**
         * 显示dialog
         */
        void showDialog();

        /**
         * 开启验证码倒计时
         */
        void startTimer();
    }
}
