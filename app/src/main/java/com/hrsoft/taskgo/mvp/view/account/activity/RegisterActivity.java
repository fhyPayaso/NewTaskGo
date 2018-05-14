package com.hrsoft.taskgo.mvp.view.account.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.mvp.model.account.request.RegisterReqModel;
import com.hrsoft.taskgo.mvp.contract.account.RegisterContract;
import com.hrsoft.taskgo.mvp.presenter.account.RegisterPresenter;
import com.hrsoft.taskgo.mvp.view.MainActivity;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.VerificationCountDownTimer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author FanHongyu.
 * @since 18/5/3 18:55.
 * email fanhongyu@hrsoft.net.
 */

public class RegisterActivity extends BasePresenterActivity<RegisterContract.Presenter> implements RegisterContract.View {

    @BindView(R.id.btn_verification_register)
    Button mBtnGetCaptcha;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.edit_user_number)
    EditText mEditPhoneNumber;
    @BindView(R.id.edit_verification_code)
    EditText mEditCaptchaCode;
    @BindView(R.id.edit_secret)
    EditText mEditPassword;
    @BindView(R.id.edit_secret_again)
    EditText mEditRepeatPassword;
    @BindView(R.id.img_agreement_selector)
    ImageView imgAgreementSelector;


    private boolean flagAgreement = false;

    private VerificationCountDownTimer mCountDownTimer;

    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }


    @Override
    protected RegisterContract.Presenter getPresenter() {
        return new RegisterPresenter(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        initCountDownTimer();
    }


    /**
     * 获取验证码点击事件
     */
    @OnClick(R.id.btn_verification_register)
    public void onBtnVerificationRegisterClicked() {
        mPresenter.getCaptcha(mEditPhoneNumber.getText().toString().trim());
    }

    /**
     * 注册按钮点击事件
     */
    @OnClick(R.id.btn_register)
    public void onBtnRegisterClicked() {
        String phoneNumber = mEditPhoneNumber.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        String captcha = mEditCaptchaCode.getText().toString().trim();
        String repeatPassword = mEditRepeatPassword.getText().toString().trim();
        mPresenter.register(new RegisterReqModel(phoneNumber, password, captcha), repeatPassword);
    }

    /**
     * 左上角箭头点击事件
     */
    @OnClick(R.id.ly_header_register)
    public void onLyHeaderRegisterClicked() {
        this.finish();
    }

    @OnClick(R.id.txt_servise)
    public void onTxtServiceClicked() {
        ToastUtil.showToast("暂未开启");
    }


    @Override
    public void startTimer() {
        mCountDownTimer.timerStart(true);
    }

    @Override
    public void getCaptchaSuccess() {
        ToastUtil.showToast("已经发送验证码，请注意查收");
    }

    @Override
    public void getCaptchaError(String error) {
        ToastUtil.showToast(error);
    }

    @Override
    public void onRegisterSuccess() {
        ToastUtil.showToast("注册成功");
        startActivity(MainActivity.class);
        mCountDownTimer.cancel();
        finish();
    }

    @Override
    public void onRegisterError(String error) {
        ToastUtil.showToast(error);
        dismissProgressDialog();
        mBtnRegister.setClickable(true);
    }

    @Override
    public void showDialog() {
        showProgressDialog();
        mBtnRegister.setClickable(false);
    }


    @OnClick(R.id.img_agreement_selector)
    public void onViewClicked() {
        if (flagAgreement) {
            flagAgreement = false;
            imgAgreementSelector.setSelected(true);
        } else {
            imgAgreementSelector.setSelected(false);
            flagAgreement = true;
        }
    }

    /**
     * 倒计时具体方法
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
                String time = millisUntilFinished / 1000 + " s";
                mBtnGetCaptcha.setText(time);
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
