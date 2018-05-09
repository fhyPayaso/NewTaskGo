package com.hrsoft.taskgo.mvp.view.task.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskGridModel;
import com.hrsoft.taskgo.mvp.contract.TaskGridContract;
import com.hrsoft.taskgo.mvp.presenter.task.TaskGridPresenter;
import com.hrsoft.taskgo.mvp.view.task.fragment.TaskGridFragment;
import com.hrsoft.taskgo.utils.FragmentUtil;
import com.hrsoft.taskgo.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author FanHongyu.
 * @since 18/4/27 13:42.
 * email fanhongyu@hrsoft.net.
 */

public class TaskGridActivity extends BaseToolBarPresenterActivity<TaskGridContract.Presenter> implements
        TaskGridContract.View {


    @BindView(R.id.ll_task_type_root_view)
    LinearLayout llRootView;

    private TextView mToolBarTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_type;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

        getToolBar().setVisibility(View.GONE);

        String moduleType = getIntent().getStringExtra(TaskTypeConfig.KEY_MODULE_TYPE);
        if (moduleType != null) {
            mPresenter.getGridList(moduleType);
        }
    }


    @Override
    protected void initView() {
        mToolBarTitle = (TextView) findViewById(R.id.txt__home_toolbar_title);
        AppBarLayout app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id
                .toolbar_layout);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    mToolBarTitle.setText("");
                } else {
                    mToolBarTitle.setText("大学生团队");
                }
            }
        });
    }


    @Override
    protected TaskGridPresenter getPresenter() {
        return new TaskGridPresenter(this);
    }


    @Override
    public void onLoadGridListSuccess(List<TaskGridModel> gridList) {
        for (TaskGridModel taskGridModel : gridList) {
            TaskGridFragment fragment = TaskGridFragment.getNewInstance(taskGridModel.getTitle(), taskGridModel
                    .getTaskTypeList());
            FragmentUtil.addFragment(this, R.id.ll_task_type_root_view, fragment, null);
        }
    }


    public static void startActivity(Context context, String moduleType) {
        Intent intent = new Intent(context, TaskGridActivity.class);
        intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, moduleType);
        context.startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
