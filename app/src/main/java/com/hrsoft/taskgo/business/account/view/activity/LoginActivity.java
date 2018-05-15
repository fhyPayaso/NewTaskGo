package com.hrsoft.taskgo.business.account.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.business.account.model.request.LoginReqModel;
import com.hrsoft.taskgo.business.account.contract.LoginContract;
import com.hrsoft.taskgo.business.account.presenter.LoginPresenter;
import com.hrsoft.taskgo.business.app.view.MainActivity;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:58.
 * email fanhongyu@hrsoft.net.
 */

public class LoginActivity extends BasePresenterActivity<LoginContract.Presenter> implements LoginContract.View {


    @BindView(R.id.edit_phone_number)
    EditText mEditPhoneNumber;
    @BindView(R.id.edit_password)
    EditText mEditPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }


    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {


    }

    @Override
    protected void initView() {
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String phone = mEditPhoneNumber.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        mPresenter.login(new LoginReqModel(phone, password));
    }

    @OnClick(R.id.txt_forget_password)
    public void onTxtForgetPasswordClicked() {
        UpdatePasswordActivity.startActivity(LoginActivity.this);
    }

    @OnClick(R.id.txt_register)
    public void onTxtRegisterClicked() {
        RegisterActivity.startActivity(LoginActivity.this);
    }


    @Override
    public void onLoginSuccess(String token) {

        dismissProgressDialog();
        startActivity(MainActivity.class);
        finish();
        ToastUtil.showToast("登录成功");
    }

    @Override
    public void onLoginFailed(String error) {
        dismissProgressDialog();
        ToastUtil.showToast(error);
        mBtnLogin.setClickable(true);
    }

    @Override
    public void showDialog() {
        mBtnLogin.setClickable(false);
        showProgressDialog();
    }
}
