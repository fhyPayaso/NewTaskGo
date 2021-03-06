package com.hrsoft.taskgo.base.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.utils.ThreadUtil;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author FanHongyu.
 * @since 18/4/23 18:08.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BaseFragment extends Fragment {


    /**
     * 当前Fragment RootView
     */
    protected View mView;

    /**
     * 进度条
     */
    protected ProgressDialog mProgressDialog;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, mView);
        initFragment();
        return mView;
    }


    protected void initFragment() {
        initData();
        initView();
    }

    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化View.
     */
    protected abstract void initView();

    /**
     * 初始数据
     */
    protected abstract void initData();


    protected void showError(String msg) {
        ToastUtil.showToast(msg);
    }


    public void showProgressDialog() {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage("请稍后...");
        }

        ThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.show();
            }
        });
    }


    public void dismissProgressDialog() {
        ThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    /**
     * 获取当前Fragment的RootView
     *
     * @return RootView
     */
    protected View getRootView() {
        return mView;
    }


    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}