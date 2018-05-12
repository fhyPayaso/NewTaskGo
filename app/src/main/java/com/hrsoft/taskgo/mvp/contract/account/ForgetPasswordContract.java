package com.hrsoft.taskgo.mvp.contract.account;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.account.request.ForgetPasswordModel;

/**
 * @author heaijia
 * @since 2018/5/6 下午2:19
 * email 549044363@qq.com
 */

public interface ForgetPasswordContract {

    interface Presenter extends IBaseContract.IBasePresenter{
        /**
         * P层进行登录成功
         */
        void sendRequestNewInformation(ForgetPasswordModel forgetPasswordModel,String repeatPassword);

        void getCaptchato(String mobile);
    }

    interface View extends IBaseContract.IBaseView{
        /**
         * 通知V层登录成功，并进行相关的操作
         */

        void onSendInformationSuccess();

        /**
         * 失败回调
         *
         * @param error
         */
        void onError(String error);
    }
}
