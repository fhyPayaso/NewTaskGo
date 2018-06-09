package com.hrsoft.taskgo.business.mine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.business.mine.contract.BindBankCardContract;
import com.hrsoft.taskgo.business.mine.model.request.BindBankCardModel;
import com.hrsoft.taskgo.business.mine.presenter.BindBankCardPresenter;
import com.hrsoft.taskgo.utils.BankUtil;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author：lszr on 2018/6/9 18:34
 * @email：1085963811@qq.com
 */
public class BindBankCardActivity extends BaseToolBarPresenterActivity<BindBankCardContract.Presenter> implements BindBankCardContract.View {
    @BindView(R.id.edit_get_name)
    EditText mEditGetName;
    @BindView(R.id.edit_get_card_number)
    EditText mEditGetCardNumber;
    private BindBankCardModel mBindBankCardModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_bank_card;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setActivityTitle("绑定银行卡");

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BindBankCardActivity.class);
        context.startActivity(intent);

    }

    @Override
    public void bindBankCardSuccess() {
        ToastUtil.showToast("绑定银行卡成功！");
        finish();
    }

    @Override
    protected BindBankCardContract.Presenter getPresenter() {
        return new BindBankCardPresenter(this);
    }


    @OnClick(R.id.btn_bind)
    public void onbindClicked() {
        if(inputOrNot()){
            mPresenter.bindBankCard(mBindBankCardModel);
        }
    }

    private boolean inputOrNot() {
        if("".equals(mEditGetName.getText().toString())){
            ToastUtil.showToast("请输入真实姓名");
            return false;
        }else if("".equals(mEditGetName.getText().toString())) {
            ToastUtil.showToast("请输入银行卡号");
            return false;
        }
        int bankType= BankUtil.getNameOfBank(mEditGetCardNumber.getText().toString().trim());
        if(bankType==-1){
            ToastUtil.showToast("请输入正确的银行卡号");
            return false;
        }

        mBindBankCardModel=new BindBankCardModel(mEditGetCardNumber.getText().toString().trim(),mEditGetName.getText().toString().trim(),String.valueOf(bankType));

        return true;
    }

}
