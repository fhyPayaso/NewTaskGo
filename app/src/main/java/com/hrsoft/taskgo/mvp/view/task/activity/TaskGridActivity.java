package com.hrsoft.taskgo.mvp.view.task.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskGridModel;
import com.hrsoft.taskgo.mvp.presenter.task.TaskGridContract;
import com.hrsoft.taskgo.mvp.presenter.task.TaskGridPresenter;
import com.hrsoft.taskgo.mvp.view.task.fragment.TaskGridFragment;
import com.hrsoft.taskgo.utils.FragmentUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author FanHongyu.
 * @since 18/4/27 13:42.
 * email fanhongyu@hrsoft.net.
 */

public class TaskGridActivity extends BasePresenterActivity<TaskGridPresenter, BaseModel> implements TaskGridContract.View {


    @BindView(R.id.ll_task_type_root_view)
    LinearLayout llRootView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_type;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

        String moduleType = getIntent().getStringExtra(TaskTypeConfig.KEY_MODULE_TYPE);
        if (moduleType != null) {
            mPresenter.getGridList(moduleType);
        }
    }


    @Override
    protected void initView() {

    }


    @Override
    protected TaskGridPresenter getPresenter() {
        return new TaskGridPresenter();
    }

    @Override
    protected BaseModel getModel() {
        return null;
    }


    @Override
    public void onLoadGridListSuccess(List<TaskGridModel> gridList) {
        for (TaskGridModel taskGridModel : gridList) {
            TaskGridFragment fragment = TaskGridFragment.getNewInstance(taskGridModel.getTitle(), taskGridModel.getTaskTypeList());
            FragmentUtil.addFragment(this, R.id.ll_task_type_root_view, fragment, null);
        }
    }


    public static void startActivity(Context context, String moduleType) {
        Intent intent = new Intent(context, TaskGridActivity.class);
        intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, moduleType);
        context.startActivity(intent);
    }
}
