package com.hrsoft.taskgo.mvp.view.task.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.RecyclerScrollListener;
import com.hrsoft.taskgo.base.fragment.BaseFragment;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterFragment;
import com.hrsoft.taskgo.mvp.contract.MyTaskListContract;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;
import com.hrsoft.taskgo.mvp.presenter.task.MyTaskListPresenter;
import com.hrsoft.taskgo.mvp.view.task.adapter.TaskListRecyclerAdapter;
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
public class MyTaskListFragment extends BasePresenterFragment<MyTaskListContract.Presenter> implements MyTaskListContract.View,
        TaskListRecyclerAdapter.OnItemViewClickListener, SwipeRefreshLayout.OnRefreshListener,RecyclerScrollListener
                .LoadMoreListener {


    @BindView(R.id.rec_task_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.fresh_task_list)
    SwipeRefreshLayout mRefreshLayout;
    Unbinder unbinder;

    private String mTaskType;
    private TaskListRecyclerAdapter mRecyclerAdapter;
    private RecyclerScrollListener mScrollListener;
    private List<BaseTaskModel> mTaskModelList = new ArrayList<>();
    private Integer mCurrentPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_task_list;
    }

    @Override
    protected void initView() {

        initRecyclerView();
    }

    @Override
    protected void initData() {

    }


    private void initRecyclerView() {


        mRecyclerAdapter = new TaskListRecyclerAdapter(mTaskModelList, getContext()
                , R.layout.item_recycler_task, TaskListRecyclerAdapter.BTN_ACCEPT);
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
        mRefreshLayout.setRefreshing(true);
        //初始化加载数据
        mPresenter.loadTaskList(mTaskType, mCurrentPage);
    }




    @Override
    public void onAvatarClick(int position) {
        ToastUtil.showToast("点击了头像" + position);
    }

    @Override
    public void onBtnClick(int position) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public String getTaskType() {
        return mTaskType;
    }

    public void setTaskType(String taskType) {
        mTaskType = taskType;
    }


    public static MyTaskListFragment getNewInstance(String taskType) {
        MyTaskListFragment fragment = new MyTaskListFragment();
        fragment.setTaskType(taskType);
        return fragment;
    }

    @Override
    protected MyTaskListContract.Presenter getPresenter() {
        return new MyTaskListPresenter(this);
    }

    @Override
    public void onRefresh() {

        mCurrentPage = 1;
        mPresenter.loadTaskList(mTaskType, mCurrentPage);
    }



    @Override
    public void onLoadMore() {
        mPresenter.loadTaskList(mTaskType, mCurrentPage + 1);
    }

    @Override
    public void onLoadTaskListSuccess(List<BaseTaskModel> taskModelList) {
        mRefreshLayout.setRefreshing(false);
        if (mScrollListener.isLoading()) {
            mScrollListener.setLoadMoreFinish();
        }
        if (mCurrentPage == 1) {
            mRecyclerAdapter.reSetDataList(taskModelList);
        } else {
            mRecyclerAdapter.addItems(taskModelList);
        }
        mCurrentPage++;
    }

    @Override
    public void onLoadTaskListError(String error) {
        mRefreshLayout.setRefreshing(false);
        if (mScrollListener.isLoading()) {
            mScrollListener.setLoadMoreFinish();
        }
        ToastUtil.showToast(error);
    }
}
