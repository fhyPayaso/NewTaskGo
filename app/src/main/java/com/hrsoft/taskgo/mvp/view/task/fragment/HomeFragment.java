package com.hrsoft.taskgo.mvp.view.task.fragment;

import android.widget.Button;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.fragment.BaseFragment;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.view.task.activity.TaskGridActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
        TaskGridActivity.startActivity(getContext(), TaskTypeConfig.COLLEGE);
    }
}
