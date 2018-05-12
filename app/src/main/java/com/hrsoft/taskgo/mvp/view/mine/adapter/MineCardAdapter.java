package com.hrsoft.taskgo.mvp.view.mine.adapter;

import android.content.Context;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.adapter.BaseViewHolder;
import com.hrsoft.taskgo.base.adapter.FooterRecyclerViewAdapter;
import com.hrsoft.taskgo.mvp.model.mine.response.MineCardModel;

import java.util.List;

/**
 * @author：lszr on 2018/5/12 20:55
 * @email：1085963811@qq.com
 */
public class MineCardAdapter extends FooterRecyclerViewAdapter<MineCardModel> {
    public MineCardAdapter(List<MineCardModel> mineCardModels, Context context, int itemLayoutId) {
        super(mineCardModels, context, itemLayoutId);
    }

    @Override
    protected void bindView(BaseViewHolder viewHolder, MineCardModel item) {

        viewHolder.setText(R.id.txt_card_number,String .valueOf(item.getNumber()))
                .setImgUrl(R.id.img_card,item.getPicture())
                .setText(R.id.txt_card_function,item.getContent());

    }
}
