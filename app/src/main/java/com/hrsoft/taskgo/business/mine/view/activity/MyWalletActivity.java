package com.hrsoft.taskgo.business.mine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.business.mine.contract.MyWalletContract;
import com.hrsoft.taskgo.business.mine.model.response.MineInformationModel;
import com.hrsoft.taskgo.business.mine.presenter.MyWalletPresenter;
import com.hrsoft.taskgo.utils.DialogUtil;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的钱包页面
 *
 * @author FanHongyu.
 * @since 18/6/7 20:26.
 * email fanhongyu@hrsoft.net.
 */

public class MyWalletActivity extends BaseToolBarPresenterActivity<MyWalletContract.Presenter> implements
        MyWalletContract.View {

    @BindView(R.id.txt_my_money)
    TextView mTxtMyMoney;
    @BindView(R.id.card_bank_card)
    CardView mCvCardBankCard;
    @BindView(R.id.txt_my_bank_card_num)
    TextView mTxtMyBankCardNum;
    @BindView(R.id.rl_add_my_bank_card)
    RelativeLayout mRlAddMyBankCard;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRlAddMyBankCard.setVisibility(View.GONE);
        mCvCardBankCard.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getWalletInfo();
    }

    @Override
    protected MyWalletContract.Presenter getPresenter() {
        return new MyWalletPresenter(this);
    }

    @Override
    protected void initView() {
        setActivityTitle("我的钱包");
    }

    @Override
    public void getMoneySuccess() {
        dismissProgressDialog();
        ToastUtil.showToast("提现成功");
        mTxtMyMoney.setText("0.00元");
    }

    @Override
    public void getMonetError(String error) {
        dismissProgressDialog();
        ToastUtil.showToast(error);
    }

    @Override
    public void getWalletInfoSuccess(String myMoney, String bankCardNum) {
        mTxtMyMoney.setText("余额" + myMoney + "元");
        if (bankCardNum != null) {
            mRlAddMyBankCard.setVisibility(View.GONE);
            mCvCardBankCard.setVisibility(View.VISIBLE);
            mTxtMyBankCardNum.setText("尾号" + bankCardNum);
        } else {
            mCvCardBankCard.setVisibility(View.GONE);
            mRlAddMyBankCard.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getWalletInfoError(String error) {
        ToastUtil.showToast(error);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyWalletActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.get_real_money)
    public void onBtnGetRealMoney() {

        if (mCvCardBankCard.getVisibility() == View.GONE) {
            ToastUtil.showToast("请先绑定银行卡");
        } else {

            new DialogUtil.QuickDialog(this)
                    .setClickListener(new DialogUtil.QuickDialog.DialogPositiveButtonListener() {
                        @Override
                        public void onPositiveButtonClick() {
                            showProgressDialog();
                            mPresenter.getMoney();
                        }
                    }).showDialog("确认提出全部余额?");
        }
    }

    @OnClick(R.id.rl_add_my_bank_card)
    public void onRlAddMyBankCard() {
        BindBankCardActivity.startActivity(MyWalletActivity.this);
    }
}
