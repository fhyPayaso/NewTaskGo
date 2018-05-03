package com.hrsoft.taskgo.mvp.view.task.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterFragment;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.view.task.fragment.information.WaterSchoolFragment;
import com.hrsoft.taskgo.utils.FragmentUtil;

/**
 * @author fhyPayaso
 * @since 2018/4/29 on 下午11:20
 * fhyPayaso@qq.com
 */
public class FillTaskInfoActivity extends BaseToolBarActivity {


    private String taskType;
    private BasePresenterFragment mFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fill_task_info;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        setActivityTitle("填写任务信息");
        taskType = getIntent().getStringExtra(TaskTypeConfig.KEY_TASK_TYPE);


    }

    @Override
    protected void initView() {

        switch (taskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL:
                if (mFragment == null || !(mFragment instanceof WaterSchoolFragment)) {
                    mFragment = new WaterSchoolFragment();
                }
                break;
            default:
                break;
        }
        FragmentUtil.addFragment(this, R.id.fl_task_info_root_view, mFragment, null);
    }


    public static void startActivity(Context context, String taskType) {
        Intent intent = new Intent(context, FillTaskInfoActivity.class);
        intent.putExtra(TaskTypeConfig.KEY_TASK_TYPE, taskType);
        context.startActivity(intent);
    }
}
