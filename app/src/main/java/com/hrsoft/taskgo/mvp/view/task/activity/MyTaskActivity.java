package com.hrsoft.taskgo.mvp.view.task.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author FanHongyu.
 * @since 18/5/9 22:00.
 * email fanhongyu@hrsoft.net.
 */

public class MyTaskActivity extends BaseToolBarActivity {


    @BindView(R.id.tl_my_task)
    TabLayout mTabLayout;
    @BindView(R.id.vp_my_task)
    ViewPager mViewPager;


    //
    private String mTaskType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_task;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

        setActivityTitle("我接受的任务");

//        mViewPager.setOffscreenPageLimit(2);
//        mTabLayout.setupWithViewPager(mViewPager);

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyTaskActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }













}
