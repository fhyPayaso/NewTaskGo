package com.hrsoft.taskgo.mvp.view.task.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.presenter.task.TaskListContract;
import com.hrsoft.taskgo.mvp.presenter.task.TaskListPresenter;
import com.hrsoft.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/4/27 15:29.
 * email fanhongyu@hrsoft.net.
 */

public class TaskListActivity extends BaseToolBarPresenterActivity<TaskListPresenter, BaseModel> implements
        TaskListContract.View {


    private String mTaskType;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mTaskType = getIntent().getStringExtra(TaskTypeConfig.KEY_TASK_TYPE);
        mPresenter.loadTaskList(mTaskType);
    }

    @Override
    protected void initView() {

    }


    @Override
    public void onLoadTaskListSuccess() {

    }

    @Override
    public void onLoadTaskListError(String error) {
        ToastUtil.showToast(error);
    }

    @Override
    protected TaskListPresenter getPresenter() {
        return new TaskListPresenter();
    }

    @Override
    protected BaseModel getModel() {
        return null;
    }


    public static void startActivity(Context context, String taskType) {
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra(TaskTypeConfig.KEY_TASK_TYPE, taskType);
        context.startActivity(intent);
    }
}
