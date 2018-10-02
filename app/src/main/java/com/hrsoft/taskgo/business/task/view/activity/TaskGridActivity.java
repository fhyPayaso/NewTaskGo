package com.hrsoft.taskgo.business.task.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.business.task.contract.TaskGridContract;
import com.hrsoft.taskgo.business.task.model.bean.TaskGridModel;
import com.hrsoft.taskgo.business.task.presenter.TaskGridPresenter;
import com.hrsoft.taskgo.business.task.view.fragment.TaskGridFragment;
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

    /**
     * 当前模块标题
     */
    private String mToolBarTitle;


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

        changeAppBarChange();
    }


    private void changeAppBarChange() {

        switch (mModelType) {
            case TaskTypeConfig.MODEL_COLLEGE:
                imgGirdTitle.setImageResource(R.drawable.bg_banner_school);
                mToolBarTitle = "大学生创业团队";
                break;
            case TaskTypeConfig.MODEL_DIY:
                mToolBarTitle = "自定义任务";
                break;
            case TaskTypeConfig.MODEL_MONEY:
                imgGirdTitle.setImageResource(R.drawable.bg_banner_money);
                mToolBarTitle = "赏金任务";
                break;
            case TaskTypeConfig.MODEL_HELP:
                imgGirdTitle.setImageResource(R.drawable.bg_banner_help);
                mToolBarTitle = "校园互助";
                break;
            case TaskTypeConfig.MODEL_PROMOTION:
                imgGirdTitle.setImageResource(R.drawable.bg_banner_promotion);
                mToolBarTitle = "校园推广";
                break;
            case TaskTypeConfig.MODEL_OFFER:
                imgGirdTitle.setImageResource(R.drawable.bg_banner_offer);
                mToolBarTitle = "校园招聘";
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
                    txtHomeToolbarTitle.setText(mToolBarTitle);
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
}
