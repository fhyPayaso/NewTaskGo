package com.hrsoft.taskgo.business.account.presenter;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.business.account.model.AccountHelper;
import com.hrsoft.taskgo.business.account.contract.SplashContract;
import com.hrsoft.taskgo.utils.CacheUtil;

/**
 * @author heaijia
 * @since 2018/5/7 下午10:19
 * email 549044363@qq.com
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter{

    public SplashPresenter(SplashContract.View view) {
        super(view);
    }

    @Override
    public void checkToken() {

        IDataCallback.Callback<String> callback = new IDataCallback.Callback<String>() {
            @Override
            public void onFailedLoaded(String error) {
                mView.checkTokenError(error);
            }

            @Override
            public void onDataLoaded(String s) {
                CacheUtil cacheUtil = App.getInstance().getCacheUtil();
                String token = cacheUtil.getString(CacheKey.TOKEN);
                if(!s.equals(token)) {
                    cacheUtil.putString(CacheKey.TOKEN,s);
                }
                mView.checkTokenSuccess(s);
            }
        };
        AccountHelper.getInstance().checkToken(this,callback);
    }
}
