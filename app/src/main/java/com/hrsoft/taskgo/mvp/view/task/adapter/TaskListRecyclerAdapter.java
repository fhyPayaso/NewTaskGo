package com.hrsoft.taskgo.mvp.view.task.adapter;

import android.content.Context;
import android.view.View;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.BaseRecyclerViewAdapter;
import com.hrsoft.taskgo.base.adapter.BaseViewHolder;
import com.hrsoft.taskgo.base.adapter.FooterRecyclerViewAdapter;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;

import java.util.List;

/**
 * @author fhyPayaso
 * @since 2018/4/29 on 下午11:17
 * fhyPayaso@qq.com
 */
public class TaskListRecyclerAdapter extends FooterRecyclerViewAdapter<BaseTaskModel> implements View.OnClickListener {


    private OnItemViewClickListener mOnItemViewClickListener;
    private int mPosition;


    public TaskListRecyclerAdapter(List<BaseTaskModel> baseTaskModels, Context context, int itemLayoutId) {
        super(baseTaskModels, context, itemLayoutId);
    }

    @Override
    protected void bindView(BaseViewHolder viewHolder, BaseTaskModel item) {
        mPosition = viewHolder.getAdapterPosition();

        viewHolder.setImgUrl(R.id.img_avatar, item.getAvatarUrl())
                .setText(R.id.txt_username, item.getUserName())
                .setText(R.id.txt_task_type, item.getTaskType())
                .setText(R.id.txt_task_money, String.valueOf(item.getMoney()))
                .setText(R.id.txt_task_card_number,String.valueOf(item.getCardNumber()))
                .setText(R.id.txt_title_first, item.getFirstTitle())
                .setText(R.id.txt_value_first, item.getFirstVaule())
                .setText(R.id.txt_title_second, item.getSecondTitle())
                .setText(R.id.txt_value_second, item.getSecondVaule())
                .setText(R.id.txt_title_third, item.getThirdTitle())
                .setText(R.id.txt_value_third, item.getThirdValue());


        viewHolder.getViewById(R.id.img_avatar).setOnClickListener(this);
        viewHolder.getViewById(R.id.txt_task_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_avatar:
                mOnItemViewClickListener.onAvatarClick(mDataList.get(mPosition));
                break;
            case R.id.txt_task_btn:
                mOnItemViewClickListener.onBtnClick(mDataList.get(mPosition));
                break;
            default:
                break;
        }
    }


    public interface OnItemViewClickListener {

        /**
         * 头像点击事件
         *
         * @param model
         */
        void onAvatarClick(BaseTaskModel model);

        /**
         * 按钮点击事件
         *
         * @param model
         */
        void onBtnClick(BaseTaskModel model);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.mOnItemViewClickListener = onItemViewClickListener;
    }
}
