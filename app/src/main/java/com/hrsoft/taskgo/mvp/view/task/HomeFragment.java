package com.hrsoft.taskgo.mvp.view.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.fragment.BaseFragment;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author FanHongyu.
 * @since 18/4/27 11:49.
 * email fanhongyu@hrsoft.net.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.btn_student_company)
    Button btnStudentCompany;


    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
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


    @OnClick(R.id.btn_student_company)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), TaskTypeActivity.class));
    }
}
