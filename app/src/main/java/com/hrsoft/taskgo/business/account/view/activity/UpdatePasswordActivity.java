package com.hrsoft.taskgo.business.account.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.business.account.contract.ForgetPasswordContract;
import com.hrsoft.taskgo.business.account.model.request.UpdatePasswordReqModel;
import com.hrsoft.taskgo.business.account.presenter.UpdatePasswordPresenter;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.VerificationCountDownTimer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author heaijia
 * @since 2018/5/5 下午1:22
 * email 549044363@qq.com
 */

public class UpdatePasswordActivity extends BasePresenterActivity<ForgetPasswordContract.Presenter> implements
        ForgetPasswordContract.View {

    @BindView(R.id.btn_certain)
    Button mBtnUpdatePassword;
    @BindView(R.id.btn_verification)
    Button mBtnGetCaptcha;
    @BindView(R.id.edit_user_number_forget)
    EditText mEditPhoneNumber;
    @BindView(R.id.edit_verification_code_forget)
    EditText mEditCaptcha;
    @BindView(R.id.edit_secret_forget)
    EditText mEditPassword;
    @BindView(R.id.edit_secret_again_forget)
    EditText mEditRepeatPassword;

    VerificationCountDownTimer mCountDownTimer;
    @BindView(R.id.txt_password_title)
    TextView txtPasswordTitle;



    @OnClick(R.id.btn_certain)
    public void onViewClicked() {
        String phoneNumber = mEditPhoneNumber.getText().toString().trim();
        String captcha = mEditCaptcha.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        String repeatPassword = mEditRepeatPassword.getText().toString().trim();
        UpdatePasswordReqModel passwordModel = new UpdatePasswordReqModel(phoneNumber, captcha, password);
        mPresenter.updatePassWord(passwordModel, repeatPassword);
    }

    @OnClick(R.id.ly_header_forget)
    public void onLyHeaderForgetClicked() {
        finish();
    }

    @OnClick(R.id.btn_verification)
    public void onBtnVerificationClicked() {
        mPresenter.getCaptcha(mEditPhoneNumber.getText().toString().trim());

    }

    @Override
    public void onGetCaptchaSuccess() {
        ToastUtil.showToast("你即将获得验证码，请注意查收");
    }

    @Override
    public void onGetCaptchaError(String error) {
        ToastUtil.showToast(error);
    }

    @Override
    public void onUpdatePasswordSuccess() {
        ToastUtil.showToast("密码修改成功，请重新登录");
        finish();
    }

    @Override
    public void onUpdatePasswordError(String error) {
        ToastUtil.showToast(error);
        dismissProgressDialog();
        mBtnUpdatePassword.setClickable(true);
    }

    @Override
    public void showDialog() {
        showProgressDialog();
        mBtnUpdatePassword.setClickable(false);
    }

    @Override
    public void startTimer() {
        mCountDownTimer.timerStart(true);
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, UpdatePasswordActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgetpassword;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initCountDownTimer();

    }

    @Override
    protected void initView() {
        if (getIntent().getStringExtra("updatePassword") != null) {

            if (getIntent().getStringExtra("updatePassword").equals("updatePassword")) {
                txtPasswordTitle.setText(getString(R.string.txt_update_password_title));
            }
        }
    }

    @Override
    protected ForgetPasswordContract.Presenter getPresenter() {
        return new UpdatePasswordPresenter(this);
    }


    /**
     * 倒计时生物具体方法
     */
    public void initCountDownTimer() {

        if (!VerificationCountDownTimer.FLAG_FIRST_IN &&
                VerificationCountDownTimer.mcurMillis + 60000 > System.currentTimeMillis()) {

            setCountDownTimer(VerificationCountDownTimer.mcurMillis + 60000 - System.currentTimeMillis());
            mCountDownTimer.timerStart(false);

        } else {

            setCountDownTimer(60000);
        }
    }

    public void setCountDownTimer(final long countDownTime) {

        mCountDownTimer = new VerificationCountDownTimer(countDownTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mBtnGetCaptcha.setEnabled(false);
                mBtnGetCaptcha.setText((millisUntilFinished / 1000) + " s");
            }

            @Override
            public void onFinish() {

                mBtnGetCaptcha.setEnabled(true);
                mBtnGetCaptcha.setText(getString(R.string.btn_verification_gain));

                if (countDownTime != 60000) {
                    setCountDownTimer(60000);
                }
            }
        };
    }
}
