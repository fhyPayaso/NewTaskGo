package com.hrsoft.taskgo.mvp.view.task.adapter;

import android.content.Context;
import android.view.View;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.BaseRecyclerViewAdapter;
import com.hrsoft.taskgo.base.adapter.BaseViewHolder;
import com.hrsoft.taskgo.base.adapter.FooterRecyclerViewAdapter;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;
import com.hrsoft.taskgo.utils.ToastUtil;

import java.util.List;

/**
 * @author fhyPayaso
 * @since 2018/4/29 on 下午11:17
 * fhyPayaso@qq.com
 */
public class TaskListRecyclerAdapter extends FooterRecyclerViewAdapter<BaseTaskModel> {


    public static final int BTN_ACCEPT = 0;

    private OnItemViewClickListener mOnItemViewClickListener;

    private int mTaskType;


    public TaskListRecyclerAdapter(List<BaseTaskModel> baseTaskModels, Context context, int itemLayoutId, int taskType) {
        super(baseTaskModels, context, itemLayoutId);
        this.mTaskType = taskType;
    }

    @Override
    protected void bindView(BaseViewHolder viewHolder, BaseTaskModel item) {


        final int position = viewHolder.getAdapterPosition();
        viewHolder.setImgUrl(R.id.img_avatar, item.getAvatarUrl())
                .setText(R.id.txt_username, item.getUserName())
                .setText(R.id.txt_task_type, item.getTaskType())
                .setText(R.id.txt_task_money, String.valueOf(item.getMoney()))
                .setText(R.id.txt_task_card_number, String.valueOf(item.getCardNumber()))
                .setText(R.id.txt_title_first, item.getFirstTitle())
                .setText(R.id.txt_value_first, item.getFirstValue())
                .setText(R.id.txt_title_second, item.getSecondTitle())
                .setText(R.id.txt_value_second, item.getSecondValue())
                .setText(R.id.txt_title_third, item.getThirdTitle())
                .setText(R.id.txt_value_third, item.getThirdValue());


        switch (mTaskType) {
            case BTN_ACCEPT:
                viewHolder.setText(R.id.txt_task_btn,"接受");
                break;
            default:
                break;
        }


        viewHolder.getViewById(R.id.img_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOnItemViewClickListener.onAvatarClick(position);

            }
        });
        viewHolder.getViewById(R.id.txt_task_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOnItemViewClickListener.onBtnClick(position);
            }
        });
    }


    public interface OnItemViewClickListener {

        /**
         * 头像点击事件
         */
        void onAvatarClick(int position);

        /**
         * 按钮点击事件
         */
        void onBtnClick(int position);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.mOnItemViewClickListener = onItemViewClickListener;
    }
}
