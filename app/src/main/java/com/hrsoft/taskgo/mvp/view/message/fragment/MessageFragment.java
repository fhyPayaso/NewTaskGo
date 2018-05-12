package com.hrsoft.taskgo.mvp.view.message.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterFragment;
import com.hrsoft.taskgo.mvp.contract.task.MessageContract;
import com.hrsoft.taskgo.mvp.model.message.MessageModel;
import com.hrsoft.taskgo.mvp.presenter.message.MessagePresenter;
import com.hrsoft.taskgo.mvp.view.message.adapter.MessageListAdapter;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author FanHongyu.
 * @since 18/4/27 11:49.
 * email fanhongyu@hrsoft.net.
 */

public class MessageFragment extends BasePresenterFragment<MessageContract.Presenter> implements MessageContract
        .View, MessageListAdapter.OnItemViewClickListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recycler_message_list)
    RecyclerView mRecyclerMessage;
    @BindView(R.id.sl_refresh_message)
    SwipeRefreshLayout mRefreshLayout;
    Unbinder unbinder;

    private List<MessageModel> mMessageList = new ArrayList<>();
    private MessageListAdapter mRecyclerAdapter;
    private OnMsgNumberListener mOnMsgNumberListener;
    /**
     * 未读消息数量
     */
    private Integer mUnreadMsgNum = 0;


    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    /**
     * 初始化View.
     */
    @Override
    protected void initView() {

        initRecyclerView();
    }

    /**
     * 初始数据
     */
    @Override
    protected void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.setRefreshing(true);
        mPresenter.loadMsgList();
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

    private void initRecyclerView() {
        mRecyclerAdapter = new MessageListAdapter(mMessageList, getContext(), R.layout.item_recycler_message);
        mRecyclerAdapter.setOnItemViewClickListener(this);
        mRecyclerMessage.setAdapter(mRecyclerAdapter);
        //添加滑动
        mRecyclerMessage.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        mRecyclerMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.txt_blue));
    }

    @Override
    protected MessageContract.Presenter getPresenter() {
        return new MessagePresenter(this);
    }

    @Override
    public void onHasReadClick(int position) {
        mPresenter.hasReadMsg(mMessageList.get(position).getId(), position);
    }

    @Override
    public void onDeleteClick(int position) {
        mPresenter.deleteMsg(mMessageList.get(position).getId(), position);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadMsgList();
    }


    @Override
    public void loadMsgListSuccess(List<MessageModel> modelList, int unreadMsgNum) {
        mRefreshLayout.setRefreshing(false);
        mRecyclerAdapter.reSetDataList(modelList);
        mUnreadMsgNum = unreadMsgNum;
        if (mOnMsgNumberListener != null) {
            mOnMsgNumberListener.updateUnreadMsgNum(mUnreadMsgNum);
        }
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMsgListError(String error) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showToast(error);
    }

    @Override
    public void readMsgSuccess(int position) {
        mMessageList.get(position).setHasRead(true);
        if (mUnreadMsgNum > 0) {
            mUnreadMsgNum--;
        }
        if (mOnMsgNumberListener != null) {
            mOnMsgNumberListener.updateUnreadMsgNum(mUnreadMsgNum);
        }
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void readMsgError(String error) {
        ToastUtil.showToast(error);
    }

    @Override
    public void deleteMsgSuccess(int position) {
        mRecyclerAdapter.removeItem(position);
    }

    @Override
    public void deleteMsgError(String error) {
        ToastUtil.showToast(error);
    }


    public interface OnMsgNumberListener {

        /**
         * 获取未读消息数量
         *
         * @param number
         */
        void updateUnreadMsgNum(int number);
    }

    public void setOnMsgNumberListener(OnMsgNumberListener onMsgNumberListener) {
        mOnMsgNumberListener = onMsgNumberListener;
    }
}
