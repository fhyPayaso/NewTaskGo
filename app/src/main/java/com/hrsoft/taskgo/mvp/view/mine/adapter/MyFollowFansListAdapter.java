package com.hrsoft.taskgo.mvp.view.mine.adapter;

import android.content.Context;
import android.view.View;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.BaseViewHolder;
import com.hrsoft.taskgo.base.adapter.FooterRecyclerViewAdapter;
import com.hrsoft.taskgo.mvp.model.mine.response.MyFollowFansModel;

import java.util.List;

/**
 * @author：lszr on 2018/5/9 18:36
 * @email：1085963811@qq.com
 */
public class MyFollowFansListAdapter extends FooterRecyclerViewAdapter<MyFollowFansModel> {

    private OnItemViewClickListener mOnItemClickListener;


    public MyFollowFansListAdapter(List<MyFollowFansModel> myFollowFansModels, Context context, int itemLayoutId) {
        super(myFollowFansModels, context, itemLayoutId);
    }

    @Override
    protected void bindView(BaseViewHolder viewHolder, MyFollowFansModel item) {


        final int position = viewHolder.getAdapterPosition();

        viewHolder.setImgUrl(R.id.img_my_follow_avatar,item.getAvatar())
                .setText(R.id.txt_my_follow_name,item.getName());

        viewHolder.getViewById(R.id.rlayout_mine_follow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
            }
        });

    }


    public interface OnItemViewClickListener {

        void onItemClick(int position);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener){
        this.mOnItemClickListener=onItemViewClickListener;
    }

}
