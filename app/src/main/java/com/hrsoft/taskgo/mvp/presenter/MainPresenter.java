package com.hrsoft.taskgo.mvp.presenter;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseActivity;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.mvp.contract.MainContract;
import com.hrsoft.taskgo.mvp.model.app.AppHelper;
import com.hrsoft.taskgo.mvp.model.app.AppInfoModel;
import com.hrsoft.taskgo.mvp.view.message.MessageFragment;
import com.hrsoft.taskgo.mvp.view.mine.MineFragment;
import com.hrsoft.taskgo.mvp.view.task.fragment.HomeFragment;
import com.hrsoft.taskgo.utils.CacheUtil;
import com.hrsoft.taskgo.utils.FragmentUtil;

/**
 * @author FanHongyu.
 * @since 18/4/27 12:00.
 * email fanhongyu@hrsoft.net.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        super(view);
    }

    /**
     * 展示home页面
     *
     * @param context
     * @param homeFragment
     */
    @Override
    public HomeFragment showHomeFragment(BaseActivity context, HomeFragment homeFragment) {
        mView.clearChoiceStatus();
        mView.changeToHomeStatus();
        mView.hideAllFragments();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            FragmentUtil.addFragment(context, R.id.fl_main_contain, homeFragment, null);
        } else {
            FragmentUtil.showFragment(context, homeFragment);

        }
        return homeFragment;
    }

    /**
     * 展示message页面
     *
     * @param context
     * @param messageFragment
     */
    @Override
    public MessageFragment showMessageFragment(BaseActivity context, MessageFragment messageFragment) {
        mView.clearChoiceStatus();
        mView.changeToMessageStatus();
        mView.hideAllFragments();
        if (messageFragment == null) {
            messageFragment = new MessageFragment();
            FragmentUtil.addFragment(context, R.id.fl_main_contain, messageFragment, null);
        } else {
            FragmentUtil.showFragment(context, messageFragment);
        }
        return messageFragment;
    }

    /**
     * 展示mine页面
     *
     * @param context
     * @param mineFragment
     */
    @Override
    public MineFragment showMineFragment(BaseActivity context, MineFragment mineFragment) {
        mView.clearChoiceStatus();
        mView.changeToMineStatus();
        mView.hideAllFragments();
        if (mineFragment == null) {
            mineFragment = new MineFragment();
            FragmentUtil.addFragment(context, R.id.fl_main_contain, mineFragment, null);
        } else {
            FragmentUtil.showFragment(context, mineFragment);
        }
        return mineFragment;
    }

    @Override
    public void checkAppVersion() {

        CacheUtil cacheUtil = App.getInstance().getCacheUtil();
        final AppInfoModel localAppInfo = (AppInfoModel) cacheUtil.getSerializableObj(CacheKey.APP_INFORMATION);
        IDataCallback.Callback<AppInfoModel> callback = new IDataCallback.Callback<AppInfoModel>() {
            @Override
            public void onFailedLoaded(String error) {

            }

            @Override
            public void onDataLoaded(AppInfoModel appInfoModel) {

                //线上版本号和本地不一致
                if (!localAppInfo.getAppVersion().equals(appInfoModel.getAppVersion())) {
                    mView.needUpdateApk(appInfoModel);
                }
            }
        };

        if (localAppInfo != null) {
            AppHelper.getInstance().checkAppVersion(this, callback);
        } else {
            cacheUtil.putSerializableObj(CacheKey.APP_INFORMATION, new AppInfoModel(Config.APP_VERSION));
        }
    }
}
