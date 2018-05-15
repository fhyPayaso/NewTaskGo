package com.hrsoft.taskgo.business.task.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hrsoft.taskgo.common.MyTaskConfig;
import com.hrsoft.taskgo.business.task.view.fragment.MyTaskListFragment;

import java.util.List;

/**
 * @author fhyPayaso
 * @since 2018/5/9 on 下午11:33
 * fhyPayaso@qq.com
 */
public class MyTaskPagerAdapter extends FragmentPagerAdapter {


    private String[] mReleaseTitles = {"未接受", "被接受", "已完成"};

    private String[] mAcceptTitles = {"未完成", "已完成"};

    private String mTaskType;

    private List<MyTaskListFragment> mFragmentList;


    public MyTaskPagerAdapter(FragmentManager fm, String taskType, List<MyTaskListFragment> fragmentList) {
        super(fm);
        this.mTaskType = taskType;
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (MyTaskConfig.MY_ACCEPT_TASK.equals(mTaskType)) {
            return mAcceptTitles[position];
        } else {
            return mReleaseTitles[position];
        }
    }

    @Override
    public int getCount() {
        return MyTaskConfig.MY_ACCEPT_TASK.equals(mTaskType) ? 2 : 3;
    }
}
