package com.hrsoft.taskgo.mvp.model.account.helper;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.mvp.model.account.request.UpdatePasswordReqModel;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;
import com.hrsoft.taskgo.mvp.model.account.request.RegisterReqModel;
import com.hrsoft.taskgo.mvp.model.account.response.AccountRespModel;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:57.
 * email fanhongyu@hrsoft.net.
 */
@SuppressWarnings("unchecked")
public class AccountHelper extends BaseModel {

    private AccountHelper() {

    }

    public static class AccountHelperHolder {
        private static final AccountHelper INSTANCE = new AccountHelper();
    }

    public static AccountHelper getInstance() {
        return AccountHelperHolder.INSTANCE;
    }

    /**
     * 登录
     *
     * @param presenter
     * @param reqModel
     * @param callback
     */
    public void login(IBaseContract.IBasePresenter presenter, LoginReqModel reqModel, final IDataCallback
            .Callback<String> callback) {

        addNotifyListener(presenter, callback);

        NetworkFactory
                .getService()
                .login(reqModel)
                .compose(BaseObserver.<ApiResponse<AccountRespModel>>setThread())
                .subscribe(new BaseObserver<AccountRespModel>() {
                    @Override
                    public void onSuccess(ApiResponse<AccountRespModel> response) {
                        callback.onDataLoaded(response.getData().getToken());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    /**
     * 注册
     *
     * @param presenter
     * @param reqModel
     * @param callback
     */
    public void register(IBaseContract.IBasePresenter presenter, RegisterReqModel reqModel, final IDataCallback
            .Callback<String> callback) {
        addNotifyListener(presenter, callback);

        NetworkFactory
                .getService()
                .register(reqModel)
                .compose(BaseObserver.<ApiResponse<AccountRespModel>>setThread())
                .subscribe(new BaseObserver<AccountRespModel>() {
                    @Override
                    public void onSuccess(ApiResponse<AccountRespModel> response) {
                        callback.onDataLoaded(response.getData().getToken());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    @SuppressWarnings("unchecked")
    public void updatePassword(IBaseContract.IBasePresenter presenter, final UpdatePasswordReqModel reqModel, final
    IDataCallback
            .Callback callback) {


        NetworkFactory
                .getService()
                .updatePassword(reqModel)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {

                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {

                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    @SuppressWarnings("unchecked")
    public void getCaptcha(IBaseContract.IBasePresenter presenter, String phoneNumber, final IDataCallback
            .Callback callback) {
        NetworkFactory
                .getService()
                .sendCaptcha(phoneNumber)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }
}
