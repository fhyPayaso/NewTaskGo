package com.hrsoft.taskgo.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.mvp.presenter.MainContarct;
import com.hrsoft.taskgo.mvp.presenter.MainPresenter;
import com.hrsoft.taskgo.mvp.view.message.MessageFragment;
import com.hrsoft.taskgo.mvp.view.mine.MineFragment;
import com.hrsoft.taskgo.mvp.view.task.HomeFragment;
import com.hrsoft.taskgo.utils.FragmentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BasePresenterActivity<MainPresenter, BaseModel> implements MainContarct.View {


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

    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        onLlBottomTabHomeClicked();
    }


    @OnClick(R.id.ll_bottom_tab_home)
    public void onLlBottomTabHomeClicked() {
        mHomeFragment = mPresenter.showHomeFragment(this, mHomeFragment);
    }

    @OnClick(R.id.ll_bottom_tab_message)
    public void onLlBottomTabMessageClicked() {
        mMessageFragment = mPresenter.showMessageFragment(this, mMessageFragment);
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
        return new MainPresenter();
    }

    /**
     * 获取Presenter实例
     *
     * @return
     */
    @Override
    protected BaseModel getModel() {
        return null;
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
    public void onBackPressed() {
        App.getInstance().exitAppWithTwiceClick();
    }
}
