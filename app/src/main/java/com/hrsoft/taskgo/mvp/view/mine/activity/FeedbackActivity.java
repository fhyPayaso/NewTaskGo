package com.hrsoft.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.mvp.contract.mine.FeedbackContract;
import com.hrsoft.taskgo.mvp.presenter.mine.FeedBackPresneter;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author：lszr on 2018/5/7 20:15
 * @email：1085963811@qq.com
 */
public class FeedbackActivity extends BaseToolBarPresenterActivity<FeedbackContract.Presenter> implements FeedbackContract.View {


    @BindView(R.id.edit_mine_feedback)
    EditText mEditMineFeedback;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_feedback));
    }

    public static void startFeedbackActivity(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onsubmitAdviceSuccess() {
        ToastUtil.showToast(getString(R.string.txt_submit_success));
        finish();
    }

    @Override
    protected FeedbackContract.Presenter getPresenter() {
        return new FeedBackPresneter(this);
    }


    @OnClick(R.id.btn_mine_feedback)
    public void onViewClicked() {
        if(!"".equals(mEditMineFeedback.getText().toString())){
            mPresenter.submitAdvice(mEditMineFeedback.getText().toString());

        }
        else{
            ToastUtil.showToast(getString(R.string.txt_no_advice));
        }
    }
}
