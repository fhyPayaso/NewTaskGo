package com.hrsoft.taskgo.test;

import android.os.Handler;
import android.os.Looper;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.utils.ThreadUtil;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:57.
 * email fanhongyu@hrsoft.net.
 */

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
    }



    public static class AccountHelperHolder {
        private static final AccountHelper INSTANCE = new AccountHelper();
    }

    public static AccountHelper getAccountHelper() {
        return AccountHelperHolder.INSTANCE;
    }
}
