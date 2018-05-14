package com.hrsoft.taskgo.mvp.view.account.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseNoBarActivity;
import com.hrsoft.taskgo.mvp.view.account.adapter.GuidePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author heaijia
 * @since 2018/5/7 下午10:16
 * email 549044363@qq.com
 */

public class GuideActivity extends BaseNoBarActivity {

    @BindView(R.id.vp_guide_pager)
    ViewPager vpGuidePager;

    private Button mBtnToLogin;
    private int imageId[];
    private List<View> pagerList;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,GuideActivity.class));
    }

    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, GuideActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide_pager;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        imageId = new int[]{R.layout.fragment_initiate_first, R.layout.fragment_initiate_second};
        pagerList = new ArrayList<>();
    }

    @Override
    protected void initView() {
        initViewPager();
    }


    private void initViewPager() {


        for (int i = 0; i < imageId.length; i++) {

            View view = LayoutInflater.from(this).inflate(imageId[i], null);
            if (i == imageId.length - 1) {
                mBtnToLogin = (Button) view.findViewById(R.id.btn_initiate);
                mBtnToLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginActivity.startActivity(GuideActivity.this);
                        finish();
                    }
                });
            }
            pagerList.add(view);
        }

        vpGuidePager.setAdapter(new GuidePagerAdapter(pagerList));
    }
}
