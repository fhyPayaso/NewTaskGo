package com.hrsoft.taskgo.mvp.model.account.helper;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.mvp.model.account.request.ForgetPasswordModel;
import com.hrsoft.taskgo.mvp.presenter.account.contract.ForgetPasswordContract;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

/**
 * @author heaijia
 * @since 2018/5/6 下午3:17
 * email 549044363@qq.com
 */

public class ForgetpasswordHelper extends BaseModel {

    private ForgetpasswordHelper() {

    }

    public static class ForgetpasswordHelperHolder {
        private static final ForgetpasswordHelper INSTANCE = new ForgetpasswordHelper();
    }

    public static ForgetpasswordHelper getInstance() {
        return ForgetpasswordHelper.ForgetpasswordHelperHolder.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public void requestNewInformation(final ForgetPasswordContract.Presenter presenter, final ForgetPasswordModel forgetPasswordModel, final IDataCallback.Callback<String> callback) {


        addNotifyListener(presenter,callback);

        NetworkFactory
                .getService()
                .updatePassword(forgetPasswordModel)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {

                    @Override
                    public void onSuccess(ApiResponse response) {
                        callback.onDataLoaded(response.getMsg());
                    }

                    @Override
                    public void onError(ApiException exception) {

                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }

    @SuppressWarnings("unchecked")
    public void getCaptcha(ForgetPasswordContract.Presenter presenter, final String mobile, final IDataCallback.Callback<String> callback){

        addNotifyListener(presenter,callback);

        NetworkFactory
                .getService()
                .sendCaptcha(mobile)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {
//                        callback.onDataLoaded(response.getData().toString());
                    }

                    @Override
                    public void onError(ApiException exception) {

                        callback.onFailedLoaded(exception.getMsg());
                    }
                });

    }
}
