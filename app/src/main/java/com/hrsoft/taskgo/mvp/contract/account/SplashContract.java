package com.hrsoft.taskgo.mvp.contract.account;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;
import com.hrsoft.taskgo.network.response.ApiResponse;

import retrofit2.Response;

/**
 * @author heaijia
 * @since 2018/5/7 下午10:03
 * email 549044363@qq.com
 */

public interface SplashContract {

    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 检查token是否有效
         */
        void checkToken();
    }


    interface View extends IBaseContract.IBaseView {


        /**
         * 有效token
         *
         * @param token
         */
        void checkTokenSuccess(String token);

        /**
         * 无效token
         *
         * @param error
         */
        void checkTokenError(String error);
    }
}
