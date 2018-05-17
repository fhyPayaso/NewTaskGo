package com.hrsoft.taskgo.business.app.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.business.app.contract.MainContract;
import com.hrsoft.taskgo.business.app.model.AppInfoModel;
import com.hrsoft.taskgo.business.app.presenter.MainPresenter;
import com.hrsoft.taskgo.business.message.view.fragment.MessageFragment;
import com.hrsoft.taskgo.business.mine.view.fragment.MineFragment;
import com.hrsoft.taskgo.business.task.view.fragment.HomeFragment;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.utils.DialogUtil;
import com.hrsoft.taskgo.utils.FragmentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author FanHongyu.
 * @since 18/4/27 11:49.
 * email fanhongyu@hrsoft.net.
 */

public class MainActivity extends BasePresenterActivity<MainContract.Presenter> implements MainContract.View,
        MessageFragment.OnMsgNumberListener {


    @BindView(R.id.img_main_tab_home)
    ImageView mImgTabHome;
    @BindView(R.id.txt_main_tab_home)
    TextView mTxtTabHome;
    @BindView(R.id.img_main_tab_message)
    ImageView mImgTabMessage;
    @BindView(R.id.txt_main_tab_message)
    TextView mTxtTabMessage;
    @BindView(R.id.img_main_tab_mine)
    ImageView mImgTabMine;
    @BindView(R.id.txt_main_tab_mine)
    TextView mTxtTabMine;
    @BindView(R.id.txt_tab_unread_msg_num)
    TextView mTxtTabUnreadMsgNum;

    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //每次进入检查是否有新版本
        App.getInstance().getCacheUtil().putSerializableObj(CacheKey.APP_INFORMATION,new AppInfoModel(Config.APP_VERSION));
        mPresenter.checkAppVersion();
    }

    @Override
    protected void initView() {

        mTxtTabUnreadMsgNum.setVisibility(View.GONE);
        onLlBottomTabMessageClicked();
        onLlBottomTabHomeClicked();
    }


    @OnClick(R.id.ll_bottom_tab_home)
    public void onLlBottomTabHomeClicked() {
        mHomeFragment = mPresenter.showHomeFragment(this, mHomeFragment);
    }

    @OnClick(R.id.ll_bottom_tab_message)
    public void onLlBottomTabMessageClicked() {
        mMessageFragment = mPresenter.showMessageFragment(this, mMessageFragment, this);
    }

    @OnClick(R.id.ll_bottom_tab_mine)
    public void onLlBottomTabMineClicked() {
        mMineFragment = mPresenter.showMineFragment(this, mMineFragment);
    }


    /**
     * 获取Presenter实例
     *
     * @return
     */
    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }


    /**
     * 清除所有标签状态
     */
    @Override
    public void clearChoiceStatus() {

        mImgTabHome.setSelected(false);
        mImgTabMessage.setSelected(false);
        mImgTabMine.setSelected(false);

        mTxtTabHome.setSelected(false);
        mTxtTabMessage.setSelected(false);
        mTxtTabMine.setSelected(false);
    }

    /**
     * 隐藏所有fragment
     */
    @Override
    public void hideAllFragments() {
        if (mHomeFragment != null) {
            FragmentUtil.hideFragment(this, mHomeFragment);
        }
        if (mMessageFragment != null) {
            FragmentUtil.hideFragment(this, mMessageFragment);
        }
        if (mMineFragment != null) {
            FragmentUtil.hideFragment(this, mMineFragment);
        }
    }

    /**
     * 标签更新至home选中
     */
    @Override
    public void changeToHomeStatus() {
        mTxtTabHome.setSelected(true);
        mImgTabHome.setSelected(true);
    }

    /**
     * 标签更新至message选中
     */
    @Override
    public void changeToMessageStatus() {
        mTxtTabMessage.setSelected(true);
        mImgTabMessage.setSelected(true);
    }

    /**
     * 标签更新至mine选中
     */
    @Override
    public void changeToMineStatus() {
        mTxtTabMine.setSelected(true);
        mImgTabMine.setSelected(true);
    }

    @Override
    public void needUpdateApk(final AppInfoModel appInfoModel) {

        DialogUtil.NativeDialog dialog = new DialogUtil().new NativeDialog();
        dialog.singleInit(this)
                .setMessage("发现新版本，请前往下载")
                .setPositiveButton("去下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openBrowserUpdate(appInfoModel.getAppLoadUrl());
                    }
                })
                .showNativeDialog();
    }

    @Override
    public void onBackPressed() {
        App.getInstance().exitAppWithTwiceClick();
    }


    /**
     * 打开浏览器下载安装包
     *
     * @param apkUrl apk下载位置
     */
    private void openBrowserUpdate(String apkUrl) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(apkUrl));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 更新
     *
     * @param number
     */
    @Override
    public void updateUnreadMsgNum(int number) {
        if (number == 0) {
            mTxtTabUnreadMsgNum.setVisibility(View.GONE);
        } else {
            mTxtTabUnreadMsgNum.setVisibility(View.VISIBLE);
            mTxtTabUnreadMsgNum.setText(String.valueOf(number));
        }
    }
}
