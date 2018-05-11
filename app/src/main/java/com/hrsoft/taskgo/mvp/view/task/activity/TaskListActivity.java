package com.hrsoft.taskgo.mvp.view.task.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.RecyclerScrollListener;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.contract.TaskListContract;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;
import com.hrsoft.taskgo.mvp.presenter.task.TaskListPresenter;
import com.hrsoft.taskgo.mvp.view.task.adapter.TaskListRecyclerAdapter;
import com.hrsoft.taskgo.mvp.view.task.adapter.TaskListRecyclerAdapter.OnItemViewClickListener;
import com.hrsoft.taskgo.utils.DialogUtil;
import com.hrsoft.taskgo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author FanHongyu.
 * @since 18/4/27 15:29.
 * email fanhongyu@hrsoft.net.
 */

public class TaskListActivity extends BaseToolBarPresenterActivity<TaskListContract.Presenter> implements
        TaskListContract.View, OnItemViewClickListener, SwipeRefreshLayout.OnRefreshListener, RecyclerScrollListener
        .LoadMoreListener {


    @BindView(R.id.recycler_task_list)
    RecyclerView mRecyclerTaskList;
    @BindView(R.id.btn_release_task)
    FloatingActionButton mBtnReleaseTask;
    @BindView(R.id.sl_refresh_task)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.txt_task_number)
    TextView mTxtTaskNumber;
    @BindView(R.id.btn_accept_all_task)
    TextView mBtnAcceptAllTask;


    private String mTaskType;
    private TaskListRecyclerAdapter mRecyclerAdapter;
    private RecyclerScrollListener mScrollListener;
    private List<BaseTaskModel> mTaskModelList = new ArrayList<>();
    private Integer mCurrentPage = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        setActivityTitle("任务列表");
        mTaskType = getIntent().getStringExtra(TaskTypeConfig.KEY_TASK_TYPE);
    }

    @Override
    protected void initView() {
        mTxtTaskNumber.setText("0");
        initRecyclerView();

        switch (mTaskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL:
                mBtnAcceptAllTask.setVisibility(View.VISIBLE);
                break;
            default:
                mBtnAcceptAllTask.setVisibility(View.GONE);
                break;
        }
    }


    private void initRecyclerView() {


        mRecyclerAdapter = new TaskListRecyclerAdapter(mTaskModelList, this
                , R.layout.item_recycler_task, TaskListRecyclerAdapter.BTN_ACCEPT);
        //设置上拉加载更多
        mRecyclerAdapter.setWithFooter(true);
        //内部控件点击事件
        mRecyclerAdapter.setOnItemViewClickListener(this);
        //设置adapter
        mRecyclerTaskList.setAdapter(mRecyclerAdapter);
        mRecyclerTaskList.setLayoutManager(new LinearLayoutManager(this));

        //设置滑动事件监听
        mScrollListener = new RecyclerScrollListener(mRecyclerTaskList, mRecyclerAdapter);
        //动态显示悬浮按钮
        mScrollListener.setFloatingButton(mBtnReleaseTask);
        //添加加载更多事件监听
        mScrollListener.setLoadMoreListener(this);

        //添加下拉刷新事件监听
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
        //初始化加载数据
        mPresenter.loadTaskList(mTaskType, mCurrentPage + 1);
    }

    @Override
    protected TaskListPresenter getPresenter() {
        return new TaskListPresenter(this);
    }

    /**
     * 拉取任务列表成功回调
     *
     * @param taskModelList
     */
    @Override
    public void onLoadTaskListSuccess(List<BaseTaskModel> taskModelList) {
        mRefreshLayout.setRefreshing(false);
        if (mScrollListener.isLoading()) {
            mScrollListener.setLoadMoreFinish();
        }
        mCurrentPage++;
        if (mCurrentPage == 1) {
            mRecyclerAdapter.reSetDataList(taskModelList);
        } else {
            mRecyclerAdapter.addItems(taskModelList);
        }
        mTxtTaskNumber.setText(String.valueOf(mTaskModelList.size()));
    }

    /**
     * 拉取任务列表失败回调
     *
     * @param error
     */
    @Override
    public void onLoadTaskListError(String error) {
        mRefreshLayout.setRefreshing(false);
        if (mScrollListener.isLoading()) {
            mScrollListener.setLoadMoreFinish();
        }
        ToastUtil.showToast(error);
    }

    /**
     * 接受任务成功回调
     */
    @Override
    public void onAcceptTaskSuccess(int position) {
        mRecyclerAdapter.removeItem(position);
    }

    @Override
    public void onAcceptAllTaskSuccess() {
        mRecyclerAdapter.removeAllItem();
    }

    /**
     * 接受任务失败回调
     *
     * @param error
     */
    @Override
    public void onAcceptTaskError(String error) {
        ToastUtil.showToast(error);
    }


    /**
     * 发布任务按钮点击事件
     */
    @OnClick(R.id.btn_release_task)
    public void onViewClicked() {
        mBtnReleaseTask.setClickable(false);
        FillTaskInfoActivity.startActivity(this, mTaskType);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mBtnReleaseTask.setClickable(true);
    }

    /**
     * 头像点击事件回调
     */
    @Override
    public void onAvatarClick(int position) {
        ToastUtil.showToast(mTaskModelList.get(position).getUserName());
    }


    /**
     * 接受按钮点击事件回调
     */
    @Override
    public void onBtnClick(final int position) {

        DialogUtil.QuickDialog dialog = new DialogUtil.QuickDialog(this)
                .setClickListener(new DialogUtil.QuickDialog.DialogPositiveButtonListener() {
                    @Override
                    public void onPositiveButtonClick() {
                        mPresenter.acceptTask(mTaskModelList.get(position), position);
                    }
                })
                .showDialog("是否确认接受该任务");
    }

    /**
     * 下拉刷新事件监听
     */
    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        mPresenter.loadTaskList(mTaskType, mCurrentPage + 1);
    }


    public static void startActivity(Context context, String taskType) {
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra(TaskTypeConfig.KEY_TASK_TYPE, taskType);
        context.startActivity(intent);
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadTaskList(mTaskType, mCurrentPage + 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_accept_all_task)
    public void onBtnAcceptAllClicked() {
        new DialogUtil.QuickDialog(this)
                .setClickListener(new DialogUtil.QuickDialog.DialogPositiveButtonListener() {
                    @Override
                    public void onPositiveButtonClick() {
                        mPresenter.acceptAllTask(mTaskModelList);
                    }
                })
                .showDialog("是否确认接受全部任务");
    }
}
