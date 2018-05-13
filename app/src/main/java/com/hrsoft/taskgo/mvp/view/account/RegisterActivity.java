package com.hrsoft.taskgo.mvp.view.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.mvp.model.account.request.RegisterReqModel;
import com.hrsoft.taskgo.mvp.contract.account.RegisterContract;
import com.hrsoft.taskgo.mvp.presenter.account.RegisterPresenter;
import com.hrsoft.taskgo.mvp.view.MainActivity;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.VerificationCountDownTimer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author FanHongyu.
 * @since 18/5/3 18:55.
 * email fanhongyu@hrsoft.net.
 */

public class RegisterActivity extends BasePresenterActivity<RegisterContract.Presenter> implements RegisterContract
        .View {

    @BindView(R.id.btn_verification_register)
    Button mbtnVerificationRegister;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.edit_user_number)
    EditText meditUserNumber;
    @BindView(R.id.edit_verification_code)
    EditText meditVerificationCode;
    @BindView(R.id.edit_secret)
    EditText meditSecret;
    @BindView(R.id.edit_secret_again)
    EditText meditSecretAgain;
    @BindView(R.id.img_agreement_selector)
    ImageView imgAgreementSelector;
    @BindView(R.id.ly_header_register)
    LinearLayout lyHeaderRegister;
    @BindView(R.id.txt_servise)
    LinearLayout txtServise;

    private boolean flagAgreement = false;

    VerificationCountDownTimer mverificationCountDownTimer;

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
        initCountDownTimer();

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 绑定 获得验证码 按钮，启动timerStart
     */
    @OnClick(R.id.btn_verification_register)
    public void onBtnVerificationRegisterClicked() {
        mPresenter.getCaptcha(meditUserNumber.getText().toString().trim());
        mverificationCountDownTimer.timerStart(true);
    }

    @OnClick(R.id.btn_register)
    public void onBtnRegisterClicked() {

        mPresenter.register(new RegisterReqModel(meditUserNumber.getText().toString().trim(),
                meditSecret.getText().toString().trim(),
                meditVerificationCode.getText().toString().trim()), meditSecretAgain.getText().toString().trim());

    }


    /**
     * 忘记密码的返回按钮，从忘记密码界面，回到登录界面
     */
    @OnClick(R.id.ly_header_register)
    public void onLyHeaderRegisterClicked() {
        this.finish();
    }

    @OnClick(R.id.txt_servise)
    public void onTxtServiseClicked() {
        ToastUtil.showToast("暂未开启");
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
        dismissProgressDialog();
        btnRegister.setClickable(true);
        startActivity(MainActivity.class);
        mverificationCountDownTimer.cancel();
        finish();
    }

    @Override
    public void onRegisterError(String error) {
        ToastUtil.showToast(error);
        dismissProgressDialog();
        btnRegister.setClickable(true);
    }

    @Override
    public void showDialog() {
        showProgressDialog();
        btnRegister.setClickable(false);
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
            mverificationCountDownTimer.timerStart(false);

        } else {

            setCountDownTimer(60000);
        }
    }

    public void setCountDownTimer(final long countDownTime) {

        mverificationCountDownTimer = new VerificationCountDownTimer(countDownTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mbtnVerificationRegister.setEnabled(false);
                mbtnVerificationRegister.setText((millisUntilFinished / 1000) + " s");
            }

            @Override
            public void onFinish() {

                mbtnVerificationRegister.setEnabled(true);
                mbtnVerificationRegister.setText(getString(R.string.btn_verification_gain));

                if (countDownTime != 60000) {
                    setCountDownTimer(60000);
                }
            }
        };
    }
}
