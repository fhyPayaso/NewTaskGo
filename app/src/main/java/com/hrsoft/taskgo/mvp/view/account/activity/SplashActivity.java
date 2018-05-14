package com.hrsoft.taskgo.mvp.view.account.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.mvp.model.app.AppInfoModel;
import com.hrsoft.taskgo.mvp.contract.account.SplashContract;
import com.hrsoft.taskgo.mvp.presenter.account.SplashPresenter;
import com.hrsoft.taskgo.mvp.view.MainActivity;
import com.hrsoft.taskgo.utils.CacheUtil;

/**
 * @author heaijia
 * @since 2018/5/7 下午10:01
 * email 549044363@qq.com
 */

public class SplashActivity extends BasePresenterActivity<SplashContract.Presenter> implements SplashContract.View {


    /**
     * 起始页停留时间
     */
    public static final int SPLASH_DELAYED_TIME = 1000;


    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, SplashActivity.class));
    }

    @Override
    protected SplashContract.Presenter getPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkToken();
            }
        }, SPLASH_DELAYED_TIME);
    }

    /**
     * 检查token
     */
    private void checkToken() {
        CacheUtil cacheUtil = App.getInstance().getCacheUtil();
        //获取本地缓存的版本信息
        AppInfoModel infoModel = (AppInfoModel) cacheUtil.getSerializableObj(CacheKey.APP_INFORMATION);
        //没有版本信息说明第一次进入app
        if (infoModel == null) {
            //生成版本号,如1.0.0
            cacheUtil.putSerializableObj(CacheKey.APP_INFORMATION, new AppInfoModel(Config.APP_VERSION));
            GuideActivity.startActivity(this);
            finish();

        } else {
            String token = cacheUtil.getString(CacheKey.TOKEN);
            if (token == null) {
                checkTokenError("本地无缓存token");
            } else {
                mPresenter.checkToken();
            }
        }
    }


    @Override
    public void checkTokenSuccess(String s) {

        if (Config.DEBUG) {
            Log.i(TAG, "checkTokenSuccess: " + s);
        }
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void checkTokenError(String error) {
        LoginActivity.startActivity(this);
        finish();
    }
}
