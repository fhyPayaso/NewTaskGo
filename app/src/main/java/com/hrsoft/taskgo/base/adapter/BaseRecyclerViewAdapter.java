package com.hrsoft.taskgo.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.mvp.presenter.task.TaskListContract;

import java.util.Collection;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/23 18:11.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BaseRecyclerViewAdapter<Data> extends RecyclerView.Adapter<BaseViewHolder> {


    protected List<Data> mDataList;
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected int mItemLayoutId;
    private OnItemClickListener mOnItemClickListener;


    public BaseRecyclerViewAdapter(List<Data> dataList, Context context, @LayoutRes int itemLayoutId) {
        mDataList = dataList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mItemLayoutId = itemLayoutId;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, mDataList.get(position), position);
                }

            }
        });
        bindView(holder, mDataList.get(position));
    }


    /**
     * 绑定item中的view和数据
     *
     * @param viewHolder
     * @param item
     */
    protected abstract void bindView(BaseViewHolder viewHolder, Data item);


    @Override
    @SuppressWarnings("unchecked")
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mItemLayoutId, parent, false);
        return new BaseViewHolder(mContext, view);
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setDataList(Collection<Data> data) {
        this.mDataList.clear();
        this.mDataList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 获取当前列表的数据
     */
    public List<Data> getDataList() {
        return this.mDataList;
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addItem(Data data) {
        this.mDataList.add(data);
        notifyDataSetChanged();
    }


    /**
     * @param collection
     */
    public void addItems(Collection<Data> collection) {
        this.mDataList.addAll(collection);
        notifyDataSetChanged();
    }


    /**
     * 移除数据
     *
     * @param data 移除的数据
     */
    public void removeItem(Data data) {
        this.mDataList.remove(data);
        notifyDataSetChanged();
    }


    /**
     * 移除数据（带动画）
     *
     * @param position pos
     */
    public void removeItem(int position) {
        this.mDataList.remove(position);
        //该方法不会使position及其之后位置的itemView重新onBindViewHolder
        notifyItemRemoved(position);
        //所以需要从position到列表末尾进行数据刷新
        notifyItemRangeChanged(position, mDataList.size() - position);

    }

    /**
     * 清除全部数据
     */
    public void removeAllItem() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取position 处数据
     */
    public Data getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * 刷新页面
     */
    public void refresh() {
        notifyDataSetChanged();
    }

    public void refresh(int position) {
        notifyItemChanged(position);
    }

    public interface OnItemClickListener<Data> {

        /**
         * 点击事件回调
         *
         * @param view
         * @param position
         */
        public void onItemClick(View view, Data data, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
