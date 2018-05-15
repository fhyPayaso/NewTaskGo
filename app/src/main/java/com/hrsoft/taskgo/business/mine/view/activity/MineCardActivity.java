package com.hrsoft.taskgo.business.mine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.business.mine.contract.MineCardContract;
import com.hrsoft.taskgo.business.mine.model.response.MineCardModel;
import com.hrsoft.taskgo.business.mine.presenter.MineCardPresenter;
import com.hrsoft.taskgo.business.mine.view.adapter.MineCardAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author：lszr on 2018/5/12 19:47
 * @email：1085963811@qq.com
 */
public class MineCardActivity extends BaseToolBarPresenterActivity<MineCardContract.Presenter> implements MineCardContract.View {

    @BindView(R.id.rec_mine_card)
    RecyclerView mRecMineCard;
    private List<MineCardModel> mMineCardModels = null;
    private MineCardAdapter mMineCardAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_card;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_card));
        mPresenter.loadCardList();
    }

    private void initRecylerView() {
        mMineCardAdapter = new MineCardAdapter(mMineCardModels, this, R.layout.item_mine_card);
        mRecMineCard.setAdapter(mMineCardAdapter);
        mRecMineCard.setLayoutManager(new LinearLayoutManager(this));
    }

    public static void startMineCardActivity(Context context) {
        Intent intent = new Intent(context, MineCardActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected MineCardContract.Presenter getPresenter() {
        return new MineCardPresenter(this);
    }

    @Override
    public void onloadCardListSuccess(List<MineCardModel> mineCardModels) {
        mMineCardModels = mineCardModels;
        initRecylerView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
