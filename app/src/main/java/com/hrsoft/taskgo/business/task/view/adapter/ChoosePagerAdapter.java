package com.hrsoft.taskgo.business.task.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hrsoft.taskgo.business.task.model.bean.ChooseCardModel;
import com.hrsoft.taskgo.business.task.view.fragment.CardFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/5/5 16:39.
 * email fanhongyu@hrsoft.net.
 */

public class ChoosePagerAdapter extends FragmentPagerAdapter {


    private List<ChooseCardModel> mCardModelList;
    private List<CardFragment> mCardFragmentList;


    public ChoosePagerAdapter(FragmentManager fm, List<ChooseCardModel> modelList) {
        super(fm);
        this.mCardModelList = modelList;
        initFragmentList();
    }

    private void initFragmentList() {
        mCardFragmentList = new ArrayList<>();
        for (ChooseCardModel model : mCardModelList) {
            CardFragment fragment = new CardFragment();
            fragment.setChooseCardModel(model);
            mCardFragmentList.add(fragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mCardFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mCardModelList.size();
    }


    /**
     * 获取卡片的选择信息
     *
     * @return
     */
    public List<ChooseCardModel> getPickCardInfo() {
        for (int i = 0; i < mCardModelList.size(); i++) {
            mCardModelList.set(i, mCardFragmentList.get(i).getChooseCardModel());
        }
        return mCardModelList;
    }
}
