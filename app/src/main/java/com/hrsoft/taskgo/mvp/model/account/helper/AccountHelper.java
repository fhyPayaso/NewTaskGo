package com.hrsoft.taskgo.mvp.model.account.helper;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;
import com.hrsoft.taskgo.mvp.model.account.request.TokenResponse;
import com.hrsoft.taskgo.mvp.presenter.account.contract.LoginContract;
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

    public void requestLogin(final LoginContract.Presenter presenter, final LoginReqModel reqModel, final IDataCallback.Callback<String> callback) {

        addNotifyListener(presenter,callback);

        NetworkFactory
                .getService()
                .login(reqModel)
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
}
