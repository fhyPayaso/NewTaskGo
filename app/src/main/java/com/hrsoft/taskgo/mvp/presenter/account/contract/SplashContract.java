package com.hrsoft.taskgo.mvp.presenter.account.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;
import com.hrsoft.taskgo.network.response.ApiResponse;

import retrofit2.Response;

/**
 * @author heaijia
 * @since 2018/5/7 下午10:03
 * email 549044363@qq.com
 */

public interface SplashContract  {

    interface Presenter extends IBaseContract.IBasePresenter {

        void checkToken(String token);
    }


    interface View extends IBaseContract.IBaseView{


        void effectiveToken(String s);

        void noneffectiveToken(String s);
    }

}
