package com.hrsoft.taskgo.business.mine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;
import com.hrsoft.taskgo.business.app.model.AppInfoModel;
import com.hrsoft.taskgo.common.CacheKey;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author：lszr on 2018/5/9 17:22
 * @email：1085963811@qq.com
 */
public class AboutActivity extends BaseToolBarActivity {
    @BindView(R.id.txt_app_version)
    TextView txtAppVersion;

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
        AppInfoModel appInfoModel = (AppInfoModel) App.getInstance().getCacheUtil().getSerializableObj(CacheKey
                .APP_INFORMATION);
        txtAppVersion.setText("版本号 : " + appInfoModel.getAppVersion());
    }

    public static void startAboutActivity(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }


}
