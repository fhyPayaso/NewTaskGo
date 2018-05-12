package com.hrsoft.taskgo.mvp.model.account.helper;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.mvp.contract.account.SplashContract;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

/**
 * @author heaijia
 * @since 2018/5/9 下午9:10
 * email 549044363@qq.com
 */

public class SplashHelper extends BaseModel {


    private SplashHelper() {

    }

    public static class SplashHelperHolder {
        private static final SplashHelper INSTANCE = new SplashHelper();
    }

    public static SplashHelper getInstance() {
        return SplashHelperHolder.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public void updatetoken(final SplashContract.Presenter presenter, final IDataCallback.Callback<String> callback) {

        addNotifyListener(presenter,callback);
        NetworkFactory
                .getService()
                .updateToke()
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {

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
