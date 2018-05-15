package com.hrsoft.taskgo.business.mine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;
import com.hrsoft.taskgo.business.account.view.activity.LoginActivity;
import com.hrsoft.taskgo.business.account.view.activity.UpdatePasswordActivity;

import butterknife.OnClick;

/**
 * @author：lszr on 2018/5/9 17:02
 * @email：1085963811@qq.com
 */
public class SettingActivity extends BaseToolBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_setting));

    }

    public static void startSettingActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.rlayout_mine_setting_change_password, R.id.rlayout_mine_setting_about, R.id
            .rlayout_mine_exchange_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlayout_mine_setting_change_password:

                Intent intent = new Intent(SettingActivity.this, UpdatePasswordActivity.class);
                intent.putExtra("updatePassword", "updatePassword");
                startActivity(intent);
                break;
            case R.id.rlayout_mine_setting_about:
                AboutActivity.startAboutActivity(SettingActivity.this);
                break;
            case R.id.rlayout_mine_exchange_account:
                App.getInstance().exitAccount();
                LoginActivity.startActivity(this);
                finish();
                break;
            default:
                break;
        }
    }
}
