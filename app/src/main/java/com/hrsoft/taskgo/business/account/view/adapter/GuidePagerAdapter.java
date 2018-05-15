package com.hrsoft.taskgo.business.account.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author heaijia
 * @since 2018/5/9 上午9:52
 * email 549044363@qq.com
 */

public class GuidePagerAdapter extends PagerAdapter{

    private List<View> pagerList;

    public GuidePagerAdapter(List<View> pagerList) {
        this.pagerList = pagerList;
    }



    @Override
    public int getCount() {
        if(pagerList!=null) {
            return pagerList.size();
        }
        return 0;
    }

    /**
     * 判断对象是否生成界面
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pagerList.get(position));
        return pagerList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pagerList.get(position));
    }


}
