package com.hrsoft.taskgo.mvp.model.account.helper;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;
import com.hrsoft.taskgo.mvp.model.account.request.RegisterReqModel;
import com.hrsoft.taskgo.mvp.model.account.request.TokenResponse;
import com.hrsoft.taskgo.mvp.presenter.account.contract.LoginContract;
import com.hrsoft.taskgo.mvp.presenter.account.contract.RegisterContract;
import com.hrsoft.taskgo.mvp.presenter.account.presenter.RegisterPresenter;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author heaijia
 * @since 2018/5/5 下午10:07
 * email 549044363@qq.com
 */

public class RegisterHelper extends BaseModel {

    private RegisterHelper() {

    }

    public static class RegisterHelperHolder {
        private static final RegisterHelper INSTANCE = new RegisterHelper();
    }

    public static RegisterHelper getInstance() {
        return RegisterHelper.RegisterHelperHolder.INSTANCE;
    }

    public void requestRegister(RegisterContract.Presenter presenter, final RegisterReqModel registerReqModel, final IDataCallback.Callback<String> callback) {

        addNotifyListener(presenter,callback);

        NetworkFactory
                .getService()
                .register(registerReqModel)
                .compose(BaseObserver.<ApiResponse<TokenResponse>>setThread())
                .subscribe(new BaseObserver<TokenResponse>() {
                    @Override
                    public void onSuccess(ApiResponse<TokenResponse> response) {
                        callback.onDataLoaded(response.getData().getToken());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }

    @SuppressWarnings("unchecked")
    public void getCaptcha(RegisterContract.Presenter presenter,final String mobile, final IDataCallback.Callback<String> callback){

        addNotifyListener(presenter,callback);

        NetworkFactory
                .getService()
                .sendCaptcha(mobile)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onSuccess(ApiResponse response) {
                        callback.onDataLoaded(response.getData().toString());
                    }

                    @Override
                    public void onError(ApiException exception) {

                        callback.onFailedLoaded(exception.getMsg());
                    }
                });

    }

}
