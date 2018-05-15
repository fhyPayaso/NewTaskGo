package com.hrsoft.taskgo.business.message.view.adapter;

import android.content.Context;
import android.view.View;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.BaseViewHolder;
import com.hrsoft.taskgo.base.adapter.FooterRecyclerViewAdapter;
import com.hrsoft.taskgo.business.message.model.MessageModel;

import java.util.List;

/**
 * 消息列表adapter
 *
 * @author FanHongyu.
 * @since 18/5/12 11:30.
 * email fanhongyu@hrsoft.net.
 */

public class MessageListAdapter extends FooterRecyclerViewAdapter<MessageModel> {

    private OnItemViewClickListener mOnItemViewClickListener;

    public MessageListAdapter(List<MessageModel> messageModels, Context context, int itemLayoutId) {
        super(messageModels, context, itemLayoutId);
    }

    @Override
    protected void bindView(BaseViewHolder viewHolder, MessageModel item) {

        final int position = viewHolder.getAdapterPosition();


        viewHolder.setText(R.id.txt_message_content, item.getContent())
                .setText(R.id.txt_message_time, item.getCreatedAt())
                .setText(R.id.txt_username,item.getUserName())
                .setImgUrl(R.id.img_avatar,item.getAvatar());
        if (item.hasRead()) {
            viewHolder.getViewById(R.id.txt_unread_flag).setVisibility(View.GONE);
        } else {
            viewHolder.getViewById(R.id.txt_unread_flag).setVisibility(View.VISIBLE);
        }

        viewHolder.getViewById(R.id.txt_have_read_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemViewClickListener.onHasReadClick(position);
            }
        });

        viewHolder.getViewById(R.id.txt_delete_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemViewClickListener.onDeleteClick(position);
            }
        });
    }

    public interface OnItemViewClickListener {

        /**
         * 已读按钮点击事件
         */
        void onHasReadClick(int position);

        /**
         * 删除按钮点击事件
         */
        void onDeleteClick(int position);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.mOnItemViewClickListener = onItemViewClickListener;
    }
}
