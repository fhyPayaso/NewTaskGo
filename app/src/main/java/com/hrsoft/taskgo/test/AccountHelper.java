package com.hrsoft.taskgo.test;

import android.os.Handler;
import android.os.Looper;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.network.ApiService;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetWorkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;
import com.hrsoft.taskgo.utils.ThreadUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.BlockingBaseObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:57.
 * email fanhongyu@hrsoft.net.
 */
@SuppressWarnings("unchecked")
public class AccountHelper extends BaseModel {


    public void login(final LoginReqModel reqModel, final IDataCallback.Callback<String> callback) {
        addNotifyListener(callback);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if ("15076035390".equals(reqModel.getUsername()) && "123456".equals(reqModel.getPassword())) {
                    callback.onDataLoaded("token");
                } else {
                    callback.onFailedLoaded("密码错误");
                }
            }
        }, 1000);


        NetWorkFactory
                .getService()
                .login(reqModel)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {

                    }

                    @Override
                    public void onError(ApiException exception) {

                    }
                });
    }


    public static class AccountHelperHolder {
        private static final AccountHelper INSTANCE = new AccountHelper();
    }

    public static AccountHelper getAccountHelper() {
        return AccountHelperHolder.INSTANCE;
    }
}
