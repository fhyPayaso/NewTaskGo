package com.hrsoft.taskgo.mvp.view.initiate.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.mvp.model.app.AppInfoModel;
import com.hrsoft.taskgo.mvp.presenter.account.contract.SplashContract;
import com.hrsoft.taskgo.mvp.presenter.account.presenter.SplashPresenter;
import com.hrsoft.taskgo.mvp.view.MainActivity;
import com.hrsoft.taskgo.mvp.view.account.LoginActivity;
import com.hrsoft.taskgo.utils.ToastUtil;

/**
 * @author heaijia
 * @since 2018/5/7 下午10:01
 * email 549044363@qq.com
 */

public class SplashActivity extends BasePresenterActivity<SplashContract.Presenter> implements SplashContract.View {


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
        }, 1000);

    }

    /**
     * 检查token
     */
    private void checkToken() {


        AppInfoModel infoModel = (AppInfoModel) App.getInstance().getCacheUtil().getSerializableObj(CacheKey
                .APP_INFORMATION);


        //没有版本信息说明第一次进入app
        if (infoModel == null) {
            //生成版本信息
            App.getInstance()
                    .getCacheUtil()
                    .putSerializableObj(CacheKey.APP_INFORMATION, new AppInfoModel(Config.APP_VERSION));
            GuideActivity.startActivity(this);
            finish();

        } else {
            String token = App.getInstance().getCacheUtil().getString(CacheKey.TOKEN);
            Log.i(TAG, "checkToken: " + token);

            if (token == null) {
                noneffectiveToken("无效token");
            } else {
                mPresenter.checkToken(token);
            }
        }
    }


    @Override
    public void effectiveToken(String s) {
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void noneffectiveToken(String error) {
        LoginActivity.startActivity(this);
        finish();
    }
}
