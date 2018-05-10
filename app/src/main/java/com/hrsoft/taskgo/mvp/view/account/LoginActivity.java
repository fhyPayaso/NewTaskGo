package com.hrsoft.taskgo.mvp.view.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;
import com.hrsoft.taskgo.mvp.presenter.account.contract.LoginContract;
import com.hrsoft.taskgo.mvp.presenter.account.presenter.LoginPresenter;
import com.hrsoft.taskgo.mvp.view.MainActivity;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.txt_forget_password)
    TextView mTxtForgetPassword;
    @BindView(R.id.txt_register)
    TextView mTxtRegister;


    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }


    /**
     * 获取Presenter实例
     *
     * @return
     */
    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
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


    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        mBtnLogin.setClickable(false);
        showProgressDialog();
        mPresenter.login(new LoginReqModel(mEditPhoneNumber.getText().toString().trim(), mEditPassword.getText()
                .toString().trim()));
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
    }

    @Override
    public void onLoginFailed(String error) {
        dismissProgressDialog();
        ToastUtil.showToast(error);
        mBtnLogin.setClickable(true);

    }

    @Override
    public void onWriteFailed(String showError) {
        ToastUtil.showToast(showError);
        mBtnLogin.setClickable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
