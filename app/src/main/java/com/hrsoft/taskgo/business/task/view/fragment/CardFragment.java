package com.hrsoft.taskgo.business.task.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.fragment.BaseFragment;
import com.hrsoft.taskgo.business.task.model.bean.ChooseCardModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author FanHongyu.
 * @since 18/5/5 16:41.
 * email fanhongyu@hrsoft.net.
 */

public class CardFragment extends BaseFragment {


    @BindView(R.id.img_card)
    ImageView imgCard;
    @BindView(R.id.txt_have_card_number)
    TextView txtHaveCardNumber;
    @BindView(R.id.txt_choose_card_number)
    TextView txtChooseCardNumber;
    Unbinder unbinder;


    private ChooseCardModel mChooseCardModel;


    /**
     * 当前卡片拥有数量
     */
    private Integer mHaveCardNumber;

    /**
     * 当前卡片选择数量
     */
    private Integer mChooseCardNumber = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager_card;
    }

    @Override
    protected void initView() {


        if (mChooseCardModel.checkValue()) {
            mHaveCardNumber = mChooseCardModel.getHaveNumber();
            mChooseCardNumber = mChooseCardModel.getChooseNumber();
            txtHaveCardNumber.setText(String.valueOf(mHaveCardNumber));
            txtChooseCardNumber.setText(String.valueOf(mChooseCardNumber));
            Glide.with(getContext()).load(mChooseCardModel.getCardImgUrl()).into(imgCard);
        }
    }

    @Override
    protected void initData() {

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

    @OnClick(R.id.img_card_sub)
    public void onImgCardSubClicked() {
        if (mChooseCardNumber > 0) {
            mHaveCardNumber++;
            mChooseCardNumber--;
        }
        updateTextNumber();
    }

    @OnClick(R.id.img_card_add)
    public void onImgCardAddClicked() {
        if (mHaveCardNumber > 0) {
            mHaveCardNumber--;
            mChooseCardNumber++;
        }
        updateTextNumber();
    }


    /**
     * 更新卡片数量文字状态
     */
    private void updateTextNumber() {
        txtChooseCardNumber.setText(String.valueOf(mChooseCardNumber));
        txtHaveCardNumber.setText(String.valueOf(mHaveCardNumber));
        mChooseCardModel.setChooseNumber(mChooseCardNumber);
        mChooseCardModel.setHaveNumber(mHaveCardNumber);
    }

    public void setChooseCardModel(ChooseCardModel model) {
        this.mChooseCardModel = model;
    }

    public ChooseCardModel getChooseCardModel() {
        return mChooseCardModel;
    }
}
