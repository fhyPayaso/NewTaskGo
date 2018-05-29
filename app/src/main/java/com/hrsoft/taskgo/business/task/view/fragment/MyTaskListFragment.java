package com.hrsoft.taskgo.business.task.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.RecyclerScrollListener;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterFragment;
import com.hrsoft.taskgo.business.mine.view.activity.OtherUserPageActivity;
import com.hrsoft.taskgo.business.task.contract.MyTaskListContract;
import com.hrsoft.taskgo.business.task.model.bean.BaseTaskModel;
import com.hrsoft.taskgo.business.task.presenter.MyTaskListPresenter;
import com.hrsoft.taskgo.business.task.view.adapter.TaskListRecyclerAdapter;
import com.hrsoft.taskgo.common.MyTaskConfig;
import com.hrsoft.taskgo.utils.DialogUtil;
import com.hrsoft.taskgo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author fhyPayaso
 * @since 2018/5/9 on 下午11:15
 * fhyPayaso@qq.com
 */
public class MyTaskListFragment extends BasePresenterFragment<MyTaskListContract.Presenter> implements
        MyTaskListContract.View, TaskListRecyclerAdapter.OnItemViewClickListener, SwipeRefreshLayout
        .OnRefreshListener, RecyclerScrollListener.LoadMoreListener {


    @BindView(R.id.rec_task_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.txt_my_task_default)
    TextView txtMyTaskDefault;


    private String mTaskStatusType;
    private TaskListRecyclerAdapter mRecyclerAdapter;
    private RecyclerScrollListener mScrollListener;
    private Integer mCurrentPage = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_task_list;
    }

    @Override
    protected void initView() {
        mPresenter.getAdapterType(mTaskStatusType);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onAvatarClick(int position) {

        if (mTaskStatusType.equals(MyTaskConfig.MY_ACCEPT_HAS_FINISHED) || mTaskStatusType.equals(MyTaskConfig
                .MY_ACCEPT_NOT_FINISHED)) {
            int userId = mRecyclerAdapter.getDataList().get(position).getUserId();
            OtherUserPageActivity.startActivity(getContext(), userId);
        }
    }

    @Override
    public void onBtnClick(int position) {
        switch (mTaskStatusType) {
            case MyTaskConfig.MY_ACCEPT_NOT_FINISHED:
                finishTask(position);
                break;
            case MyTaskConfig.MY_RELEASE_NOT_ACCEPTED:
                mPresenter.returnCard(mRecyclerAdapter.getDataList().get(position).getTaskId(),position);
            default:
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(true);
            onRefresh();
        }
        //初始化加载数据
    }

    /**
     * 完成任务
     *
     * @param position
     */
    private void finishTask(final int position) {
        new DialogUtil.QuickDialog(getContext())
                .setClickListener(new DialogUtil.QuickDialog.DialogPositiveButtonListener() {
                    @Override
                    public void onPositiveButtonClick() {
                        int taskId = mRecyclerAdapter.getDataList().get(position).getTaskId();
                        mPresenter.finishTask(taskId, position);
                    }
                })
                .showDialog("是否确认完成任务");
    }


    public String getTaskStatusType() {
        return mTaskStatusType;
    }

    public void setTaskStatusType(String taskStatusType) {
        mTaskStatusType = taskStatusType;
    }

    public static MyTaskListFragment getNewInstance(String taskType) {
        MyTaskListFragment fragment = new MyTaskListFragment();
        fragment.setTaskStatusType(taskType);
        return fragment;
    }

    @Override
    protected MyTaskListContract.Presenter getPresenter() {
        return new MyTaskListPresenter(this);
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        mPresenter.loadTaskList(mTaskStatusType, mCurrentPage + 1);
    }


    @Override
    public void onLoadMore() {
        mPresenter.loadTaskList(mTaskStatusType, mCurrentPage + 1);
    }

    @Override
    public void onLoadTaskListSuccess(List<BaseTaskModel> taskModelList) {


        mRefreshLayout.setRefreshing(false);
        if (mScrollListener.isLoading()) {
            mScrollListener.setLoadMoreFinish();
        }

        if (taskModelList == null || taskModelList.size() == 0) {
            if (mCurrentPage == 0) {
                txtMyTaskDefault.setText("当前列表暂无任务");
                mRecyclerAdapter.removeAllItem();
            } else {
                ToastUtil.showToast("暂无更多");
            }
        } else {

            mCurrentPage++;
            if (mCurrentPage == 1) {
                mRecyclerAdapter.reSetDataList(taskModelList);
            } else {
                mRecyclerAdapter.addItems(taskModelList);
            }
        }
    }

    @Override
    public void onLoadTaskListError(String error) {
        mRefreshLayout.setRefreshing(false);
        if (mScrollListener.isLoading()) {
            mScrollListener.setLoadMoreFinish();
        }
        ToastUtil.showToast(error);
    }

    @Override
    public void initRecyclerView(int btnType) {
        mRecyclerAdapter = new TaskListRecyclerAdapter(new ArrayList<BaseTaskModel>(), getContext()
                , R.layout.item_recycler_task, btnType);
        //设置上拉加载更多
        mRecyclerAdapter.setWithFooter(true);
        //内部控件点击事件
        mRecyclerAdapter.setOnItemViewClickListener(this);
        //设置adapter
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置滑动事件监听
        mScrollListener = new RecyclerScrollListener(mRecyclerView, mRecyclerAdapter);
        //添加加载更多事件监听
        mScrollListener.setLoadMoreListener(this);
        //添加下拉刷新事件监听
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.txt_blue));
    }

    @Override
    public void finishTaskSuccess(int position) {
        ToastUtil.showToast("完成成功");
        mRecyclerAdapter.removeItem(position);
    }

    @Override
    public void finishTaskError(String error) {
        ToastUtil.showToast(error);
    }

    @Override
    public void returnCardSuccess(int position) {
        ToastUtil.showToast("退回卡片成功");
        mRecyclerAdapter.removeItem(position);
    }

    @Override
    public void returnCardError(String error) {
        ToastUtil.showToast(error);
    }

}
