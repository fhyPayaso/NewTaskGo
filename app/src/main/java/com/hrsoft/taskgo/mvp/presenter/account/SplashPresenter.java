package com.hrsoft.taskgo.mvp.presenter.account;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.mvp.model.account.helper.SplashHelper;
import com.hrsoft.taskgo.mvp.contract.account.SplashContract;

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
    @SuppressWarnings("unchecked")
    public void checkToken(String token) {

        IDataCallback.Callback<String> callback = new IDataCallback.Callback<String>() {
            @Override
            public void onFailedLoaded(String error) {
                mView.noneffectiveToken(error);
            }

            @Override
            public void onDataLoaded(String s) {

                String token = App.getInstance().getCacheUtil().getString(CacheKey.TOKEN);
                if(!s.equals(token)){
                    App.getInstance().getCacheUtil().putString(CacheKey.TOKEN, s);
                }
                mView.effectiveToken(s);
            }
        } ;
        SplashHelper.getInstance().updatetoken(this,callback);
    }
}
