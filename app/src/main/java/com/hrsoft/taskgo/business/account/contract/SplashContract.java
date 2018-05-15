package com.hrsoft.taskgo.business.account.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;

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
