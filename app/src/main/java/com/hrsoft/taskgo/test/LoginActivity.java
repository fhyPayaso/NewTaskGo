package com.hrsoft.taskgo.test;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hrsoft.taskgo.MainActivity;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:58.
 * email fanhongyu@hrsoft.net.
 */

public class LoginActivity extends BasePresenterActivity<LoginPresenter, AccountHelper> implements LoginContract.View {


    private EditText mEditUserName;
    private EditText mEditPassword;

    /**
     * 获取Presenter实例
     *
     * @return
     */
    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    /**
     * 获取Presenter实例
     *
     * @return
     */
    @Override
    protected AccountHelper getModel() {
        return AccountHelper.getAccountHelper();
    }

    /**
     * 获取父布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 加载数据
     *
     * @param savedInstanceState
     */
    @Override
    protected void initData(Bundle savedInstanceState) {

        mEditUserName = (EditText) findViewById(R.id.edit_username);
        mEditPassword = (EditText) findViewById(R.id.edit_password);

    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.login(mEditUserName.getText().toString(), mEditPassword.getText().toString());

            }
        });
    }


    @Override
    public void onLoginSuccess() {

        ToastUtil.showToast("登录成功");
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void onLoginFailed(String error) {
        ToastUtil.showToast(error);
    }

    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }
}
