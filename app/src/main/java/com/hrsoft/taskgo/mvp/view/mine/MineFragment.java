package com.hrsoft.taskgo.mvp.view.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.fragment.BaseFragment;
import com.hrsoft.taskgo.common.MyTaskConfig;
import com.hrsoft.taskgo.mvp.view.task.activity.MyTaskListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author FanHongyu.
 * @since 18/4/27 11:49.
 * email fanhongyu@hrsoft.net.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.release)
    Button release;
    @BindView(R.id.accept)
    Button accept;
    Unbinder unbinder;

    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    /**
     * 初始化View.
     */
    @Override
    protected void initView() {

    }

    /**
     * 初始数据
     */
    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.release)
    public void onReleaseClicked() {
        MyTaskListActivity.startActivity(getContext(), MyTaskConfig.MY_RELEASE_TASK);
    }

    @OnClick(R.id.accept)
    public void onAcceptClicked() {
        MyTaskListActivity.startActivity(getContext(), MyTaskConfig.MY_ACCEPT_TASK);
    }







}
