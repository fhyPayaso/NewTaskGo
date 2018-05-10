package com.hrsoft.taskgo.base.adapter;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hrsoft.taskgo.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/4/30 on 下午1:20
 * fhyPayaso@qq.com
 */
public class RecyclerScrollListener extends RecyclerView.OnScrollListener {


    private FloatingActionButton mFloatingButton;
    private RecyclerView.LayoutManager mLayoutManager;
    private FooterRecyclerViewAdapter mFooterRecyclerViewAdapter;
    private LoadMoreListener mListener;

    /**
     * 分页数量
     */
    private int mItemNumPerPage = 20;

    /**
     * 是否正在加载中
     */
    private boolean mLoading = false;
    /**
     * 上拉刷新功能是否开启
     */
    private boolean mIsSwipeToLoadEnabled = true;


    public RecyclerScrollListener(RecyclerView recyclerView, FooterRecyclerViewAdapter footerRecyclerViewAdapter) {
        mLayoutManager = recyclerView.getLayoutManager();
        mFooterRecyclerViewAdapter = footerRecyclerViewAdapter;
        recyclerView.addOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (!mLoading && newState == RecyclerView.SCROLL_STATE_IDLE) {

            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mLayoutManager;
            int lastCompletePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            int totalItemCount = linearLayoutManager.getItemCount();

            //达到需要分页的数目并且滚动到最后
            if (totalItemCount >= mItemNumPerPage && lastCompletePosition == (totalItemCount - 1)) {
                mLoading = true;
                mFooterRecyclerViewAdapter.showFooterVisibility(true);
                if (mListener != null) {
                    mListener.onLoadMore();
                }
            }
        }
    }


    /**
     * 动态显示悬浮按钮
     *
     * @param recyclerView
     * @param dx
     * @param dy
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mFloatingButton != null) {
            if (dy < 0) {
                //向下滑动显示按钮
                mFloatingButton.show();
            } else if (dy > 0) {
                //向上滑动隐藏按钮
                mFloatingButton.hide();
            }
        }
    }


    /**
     * 设置分页数量
     *
     * @param itemNumPerPage 默认为20
     */
    public void setItemNumPerPage(int itemNumPerPage) {
        mItemNumPerPage = itemNumPerPage;
    }

    /**
     * 设置需要监听的悬浮按钮
     *
     * @param floatingButton
     */
    public void setFloatingButton(FloatingActionButton floatingButton) {
        mFloatingButton = floatingButton;
    }

    /**
     * 设置LoadMore Item为加载完成状态, 上拉加载更多完成时调用
     */
    public void setLoadMoreFinish() {
        mLoading = false;
        mFooterRecyclerViewAdapter.showFooterVisibility(false);
    }

    /**
     * 判断是否正在加载
     *
     * @return
     */
    public boolean isLoading() {
        return mLoading;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        mListener = loadMoreListener;
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }
}
