package com.hrsoft.taskgo.business.task.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;
import com.hrsoft.taskgo.common.MyTaskConfig;
import com.hrsoft.taskgo.business.task.view.adapter.MyTaskPagerAdapter;
import com.hrsoft.taskgo.business.task.view.fragment.MyTaskListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fhyPayaso
 * @since 2018/5/9 on 下午11:15
 * fhyPayaso@qq.com
 */
public class MyTaskListActivity extends BaseToolBarActivity {


    @BindView(R.id.tl_my_task)
    TabLayout mTabMyTask;
    @BindView(R.id.vp_my_task)
    ViewPager mVpMyTask;


    private String mTaskType;

    private List<MyTaskListFragment> mFragmentList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_task_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTaskType = getIntent().getStringExtra(MyTaskConfig.KEY_MY_TASK_TYPE);
    }

    @Override
    protected void initView() {
        switch (mTaskType) {
            case MyTaskConfig.MY_ACCEPT_TASK:
                initAcceptView();
                break;
            case MyTaskConfig.MY_RELEASE_TASK:
                initReleaseView();
                break;
            default:
                break;
        }
        MyTaskPagerAdapter myTaskPagerAdapter = new MyTaskPagerAdapter(getSupportFragmentManager(), mTaskType, mFragmentList);
        mVpMyTask.setAdapter(myTaskPagerAdapter);
        mTabMyTask.setupWithViewPager(mVpMyTask);
    }

    private void initAcceptView() {
        setActivityTitle("我接受的任务");
        mVpMyTask.setOffscreenPageLimit(2);
        mFragmentList.add(MyTaskListFragment.getNewInstance(MyTaskConfig.MY_ACCEPT_NOT_FINISHED));
        mFragmentList.add(MyTaskListFragment.getNewInstance(MyTaskConfig.MY_ACCEPT_HAS_FINISHED));
    }

    private void initReleaseView() {
        setActivityTitle("我发布的任务");
        mVpMyTask.setOffscreenPageLimit(3);
        mFragmentList.add(MyTaskListFragment.getNewInstance(MyTaskConfig.MY_RELEASE_NOT_ACCEPTED));
        mFragmentList.add(MyTaskListFragment.getNewInstance(MyTaskConfig.MY_RELEASE_HAS_ACCEPTED));
        mFragmentList.add(MyTaskListFragment.getNewInstance(MyTaskConfig.MY_RELEASE_HAS_FINISHED));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public static void startActivity(Context context, String taskType) {
        Intent intent = new Intent(context, MyTaskListActivity.class);
        intent.putExtra(MyTaskConfig.KEY_MY_TASK_TYPE, taskType);
        context.startActivity(intent);
    }


}
