package com.hrsoft.taskgo.mvp.view.task.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.BaseViewHolder;
import com.hrsoft.taskgo.base.adapter.FooterRecyclerViewAdapter;
import com.hrsoft.taskgo.common.MyTaskConfig;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;

import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * @author fhyPayaso
 * @since 2018/4/29 on 下午11:17
 * fhyPayaso@qq.com
 */
public class TaskListRecyclerAdapter extends FooterRecyclerViewAdapter<BaseTaskModel> {


    /**
     * 无按钮
     */
    public static final int BTN_EMPTY = 0;

    /**
     * 接受
     */
    public static final int BTN_ACCEPT = 1;

    /**
     * 申请完成
     */
    public static final int BTN_APPLY_FINISH = 2;

    /**
     * 撤销任务或者继续支付
     */
    public static final int BTN_CANCEL_OR_PAY = 3;

    private OnItemViewClickListener mOnItemViewClickListener;

    private int mBtnType;


    public TaskListRecyclerAdapter(List<BaseTaskModel> baseTaskModels, Context context, int itemLayoutId, int
            btnType) {
        super(baseTaskModels, context, itemLayoutId);
        this.mBtnType = btnType;
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


        TextView textButton = (TextView) viewHolder.getViewById(R.id.txt_task_btn);
        switch (mBtnType) {
            case BTN_ACCEPT:
                textButton.setText("接受");
                break;
            case BTN_APPLY_FINISH:
                textButton.setText("完成");
                break;
            case BTN_CANCEL_OR_PAY:
                if (item.getTaskPayStatus() == MyTaskConfig.PAY_STATUS_SUCCESS_PAY) {
                    textButton.setText("撤销任务");
                } else {
                    textButton.setText("继续支付");
                }
                break;
            default:
                textButton.setVisibility(View.GONE);
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
