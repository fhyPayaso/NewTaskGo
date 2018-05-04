package com.hrsoft.taskgo.mvp.view.task.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.RecyclerScrollListener;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;
import com.hrsoft.taskgo.mvp.contract.TaskListContract;
import com.hrsoft.taskgo.mvp.presenter.task.TaskListPresenter;
import com.hrsoft.taskgo.mvp.view.task.adapter.TaskListRecyclerAdapter;
import com.hrsoft.taskgo.mvp.view.task.adapter.TaskListRecyclerAdapter.OnItemViewClickListener;
import com.hrsoft.taskgo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author FanHongyu.
 * @since 18/4/27 15:29.
 * email fanhongyu@hrsoft.net.
 */

public class TaskListActivity extends BaseToolBarPresenterActivity<TaskListContract.Presenter> implements
        TaskListContract.View, OnItemViewClickListener, SwipeRefreshLayout
        .OnRefreshListener, RecyclerScrollListener.LoadMoreListener {


    @BindView(R.id.recycler_task_list)
    RecyclerView mRecyclerTaskList;
    @BindView(R.id.btn_release_task)
    FloatingActionButton mBtnReleaseTask;
    @BindView(R.id.sl_refresh_task)
    SwipeRefreshLayout mRefreshLayout;


    private String mTaskType;
    private TaskListRecyclerAdapter mRecyclerAdapter;
    private RecyclerScrollListener mScrollListener;
    private List<BaseTaskModel> mTaskModelList = new ArrayList<>();


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
        initRecyclerView();

    }


    private void initRecyclerView() {

        mRecyclerAdapter = new TaskListRecyclerAdapter(mTaskModelList, this, R.layout.item_recycler_task);
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
        mPresenter.loadTaskList(mTaskType);
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
        mRecyclerAdapter.addItems(taskModelList);
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
        FillTaskInfoActivity.startActivity(this, mTaskType);
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
    public void onBtnClick(int position) {
        mPresenter.acceptTask(mTaskModelList.get(position), position);
    }

    /**
     * 下拉刷新事件监听
     */
    @Override
    public void onRefresh() {
        mPresenter.loadTaskList(mTaskType);
    }


    public static void startActivity(Context context, String taskType) {
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra(TaskTypeConfig.KEY_TASK_TYPE, taskType);
        context.startActivity(intent);
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadTaskList(mTaskType);
    }
}
