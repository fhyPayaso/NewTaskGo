package com.hrsoft.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;

/**
 * @author：lszr on 2018/5/9 17:22
 * @email：1085963811@qq.com
 */
public class AboutAvtivity extends BaseToolBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_about));
    }

    public static void startAboutActivity(Context context) {
        Intent intent = new Intent(context, AboutAvtivity.class);
        context.startActivity(intent);
    }
}
