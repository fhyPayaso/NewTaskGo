package com.hrsoft.taskgo.mvp.view.task.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.contract.TaskGridContract;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskGridModel;
import com.hrsoft.taskgo.mvp.presenter.task.TaskGridPresenter;
import com.hrsoft.taskgo.mvp.view.task.fragment.TaskGridFragment;
import com.hrsoft.taskgo.utils.FragmentUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author FanHongyu.
 * @since 18/4/27 13:42.
 * email fanhongyu@hrsoft.net.
 */

public class TaskGridActivity extends BaseToolBarPresenterActivity<TaskGridContract.Presenter> implements
        TaskGridContract.View {


    @BindView(R.id.ll_task_type_root_view)
    LinearLayout llRootView;
    @BindView(R.id.img_gird_title)
    ImageView imgGirdTitle;
    @BindView(R.id.txt_home_toolbar_title)
    TextView txtHomeToolbarTitle;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;


    /**
     * 当前模块类型
     */
    private String mModelType;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_type;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

        getToolBar().setVisibility(View.GONE);
        mModelType = getIntent().getStringExtra(TaskTypeConfig.KEY_MODULE_TYPE);
        if (mModelType != null) {
            mPresenter.getGridList(mModelType);
        }
    }


    @Override
    protected void initView() {


        switch (mModelType) {
            case TaskTypeConfig.MODEL_COLLEGE:
                imgGirdTitle.setImageResource(R.drawable.bg_card_college);
                break;
            case TaskTypeConfig.MODEL_DIY:
                imgGirdTitle.setImageResource(R.drawable.bg_card_diy_task);
                break;
            case TaskTypeConfig.MODEL_MONEY:
                imgGirdTitle.setImageResource(R.drawable.bg_card_money_task);
                break;
            case TaskTypeConfig.MODEL_HELP:
                imgGirdTitle.setImageResource(R.drawable.bg_card_help_task);
                break;
            case TaskTypeConfig.MODEL_PROMOTION:
                imgGirdTitle.setImageResource(R.drawable.bg_card_promotion);
                break;
            case TaskTypeConfig.MODEL_OFFER:
                imgGirdTitle.setImageResource(R.drawable.bg_card_offer);
                break;
            default:
                break;
        }


        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    txtHomeToolbarTitle.setText("");
                } else {
                    txtHomeToolbarTitle.setText("大学生团队");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
