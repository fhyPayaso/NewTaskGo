package com.hrsoft.taskgo.mvp.view.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.mvp.model.account.request.ForgetPasswordModel;
import com.hrsoft.taskgo.mvp.presenter.account.contract.ForgetPasswordContract;
import com.hrsoft.taskgo.mvp.presenter.account.presenter.ForgetPasswordPresenter;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.VerificationCountDownTimer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author heaijia
 * @since 2018/5/5 下午1:22
 * email 549044363@qq.com
 */

public class UpdatePasswordActivity extends BasePresenterActivity<ForgetPasswordContract.Presenter> implements ForgetPasswordContract.View {

    @BindView(R.id.btn_certain)
    Button mbtnCertain;
    @BindView(R.id.ly_header_forget)
    LinearLayout mlyHeaderForget;
    @BindView(R.id.btn_verification)
    Button mbtnVerification;
    @BindView(R.id.edit_user_number_forget)
    EditText meditUserNumberForget;
    @BindView(R.id.edit_verification_code_forget)
    EditText meditVerificationCodeForget;
    @BindView(R.id.edit_secret_forget)
    EditText meditSecretForget;
    @BindView(R.id.edit_secret_again_forget)
    EditText meditSecretAgainForget;

    VerificationCountDownTimer mCountDownTimer;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, UpdatePasswordActivity.class));

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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

    }

    @Override
    protected ForgetPasswordContract.Presenter getPresenter() {
        return new ForgetPasswordPresenter(this);
    }


    @OnClick(R.id.btn_certain)
    public void onViewClicked() {
        mPresenter.sendRequestNewInformation(new ForgetPasswordModel(
                meditUserNumberForget.getText().toString().trim(),
                meditVerificationCodeForget.getText().toString().trim(),
                meditSecretForget.getText().toString().trim()),
                meditSecretAgainForget.getText().toString().trim());

    }

    @OnClick(R.id.ly_header_forget)
    public void onLyHeaderForgetClicked() {
        /**
         * 点击 发送验证码 按钮，P层进行数据的传输
         */
        LoginActivity.startActivity(UpdatePasswordActivity.this);

    }

    @OnClick(R.id.btn_verification)
    public void onBtnVerificationClicked() {

                mPresenter.getCaptchato(meditUserNumberForget.getText().toString().trim());
            ToastUtil.showToast("你即将获得验证码，请注意查收");
            mCountDownTimer.timerStart(true);



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
                mbtnVerification.setEnabled(false);
                mbtnVerification.setText((millisUntilFinished / 1000) + " s");
            }

            @Override
            public void onFinish() {

                mbtnVerification.setEnabled(true);
                mbtnVerification.setText(getString(R.string.btn_verification_gain));

                if (countDownTime != 60000) {
                    setCountDownTimer(60000);
                }
            }
        };
    }


    @Override
    public void onSendInformationSuccess() {
        ToastUtil.showToast("密码已找回，请重新登录");
        LoginActivity.startActivity(UpdatePasswordActivity.this);
        finish();
    }

    @Override
    public void onError(String error) {

        ToastUtil.showToast("失败");

    }
}
