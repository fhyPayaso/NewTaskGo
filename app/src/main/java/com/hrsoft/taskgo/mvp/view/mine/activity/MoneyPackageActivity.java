package com.hrsoft.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.mvp.model.mine.response.MineInformationModel;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author：lszr on 2018/5/13 01:10
 * @email：1085963811@qq.com
 */
public class MoneyPackageActivity extends BaseToolBarPresenterActivity {
    @BindView(R.id.txt_money_count)
    TextView mTxtMoneyCount;


    public MineInformationModel mineInformationModel = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_money_package;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mineInformationModel = new Gson().fromJson(intent.getStringExtra("MineInformationModel"),
                MineInformationModel.class);
        setActivityTitle(getString(R.string.txt_money_package_title));
        mTxtMoneyCount.setText("￥"+mineInformationModel.getBalance());

    }

    public static void startActivity(Context context, MineInformationModel mineInformationModel) {
        Intent intent = new Intent(context, MoneyPackageActivity.class);
        String jsonData = new Gson().toJson(mineInformationModel);
        intent.putExtra("MineInformationModel", jsonData);
        context.startActivity(intent);
    }

    @Override
    protected IBaseContract.IBasePresenter getPresenter() {
        return null;
    }


    @OnClick({R.id.get_real_money, R.id.btn_bind_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_real_money:
                ToastUtil.showToast(getString(R.string.txt_no_function_get_money));
                break;
            case R.id.btn_bind_bank:
                ToastUtil.showToast(getString(R.string.txt_no_function_get_money));
                break;
            default:
                break;
        }
    }
}
