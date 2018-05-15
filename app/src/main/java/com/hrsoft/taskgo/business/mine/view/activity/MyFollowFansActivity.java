package com.hrsoft.taskgo.business.mine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.business.mine.contract.MyFollowContract;
import com.hrsoft.taskgo.business.mine.model.response.MyFollowFansModel;
import com.hrsoft.taskgo.business.mine.presenter.MyFollowListPresenter;
import com.hrsoft.taskgo.business.mine.view.adapter.MyFollowFansListAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @author：lszr on 2018/5/9 18:08
 * @email：1085963811@qq.com
 */
public class MyFollowFansActivity extends BaseToolBarPresenterActivity<MyFollowContract.Presenter> implements
        MyFollowContract.View,
        MyFollowFansListAdapter.OnItemViewClickListener {


    @BindView(R.id.txt_default_text)
    TextView mTxtDefaultText;
    @BindView(R.id.rec_my_follow)
    RecyclerView recMyFollow;

    private List<MyFollowFansModel> mMyFollowFansModels;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_follow;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        if (getIntent().getIntExtra("listType", 0) == Config.MY_FOLLOW_LIST) {

            setActivityTitle(getString(R.string.txt_mine_follow_title));
            mPresenter.loadMyFollowList();
        } else {
            setActivityTitle(getString(R.string.txt_mine_fans_title));
            mPresenter.loadMyFansList();
        }


    }

    private void initRecylerView() {
        MyFollowFansListAdapter myFollowFansListAdapter;
        if (getIntent().getIntExtra("listType", 0) == Config.MY_FOLLOW_LIST) {

            if (mMyFollowFansModels.size() != 0) {

                checkModelValue(mMyFollowFansModels);
                myFollowFansListAdapter = new MyFollowFansListAdapter(mMyFollowFansModels, this, R.layout
                        .item_my_follow);

                myFollowFansListAdapter.setOnItemViewClickListener(this);
                recMyFollow.setAdapter(myFollowFansListAdapter);
                recMyFollow.setLayoutManager(new LinearLayoutManager(this));
            } else {
                mTxtDefaultText.setText(getString(R.string.txt_default_follow));
            }
        } else {
            if (mMyFollowFansModels.size() != 0) {

                checkModelValue(mMyFollowFansModels);
                myFollowFansListAdapter = new MyFollowFansListAdapter(mMyFollowFansModels, this, R.layout
                        .item_my_follow);

                myFollowFansListAdapter.setOnItemViewClickListener(this);
                recMyFollow.setAdapter(myFollowFansListAdapter);
                recMyFollow.setLayoutManager(new LinearLayoutManager(this));
            } else {
                mTxtDefaultText.setText(getString(R.string.txt_default_fans));
            }
        }

    }

    /**
     * 对于错误数据进行省缺设置
     *
     * @param myFollowFansModels
     * @return
     */
    private void checkModelValue(List<MyFollowFansModel> myFollowFansModels) {
        for (int i = 0; i < myFollowFansModels.size(); i++) {
            defaultItemValue(myFollowFansModels.get(i));
        }
    }

    /**
     * 具体省缺设置
     *
     * @param myFollowFansModel
     * @return
     */
    private void defaultItemValue(MyFollowFansModel myFollowFansModel) {
        if (myFollowFansModel.getAvatar() == null) {
            myFollowFansModel.setAvatar(getString(R.string.default_avater));
        }
        if (myFollowFansModel.getName() == null) {
            myFollowFansModel.setName(getString(R.string.txt_gender_unknown));
        }
        if ("".equals(String.valueOf(myFollowFansModel.getUserId()))) {
            myFollowFansModel.setUserId(0);
        }
    }

    @Override
    public void onloadMyFollowFansListSuccess(List<MyFollowFansModel> myFollowFansModel) {
        mMyFollowFansModels = myFollowFansModel;
        initRecylerView();
    }


    @Override
    protected MyFollowContract.Presenter getPresenter() {
        return new MyFollowListPresenter(this);
    }

    @Override
    public void onItemClick(int position) {
        if (getIntent().getIntExtra("listType", 0) == Config.MY_FOLLOW_LIST) {

            OtherUserPageActivity.startActivity(this, mMyFollowFansModels.get(position).getUserId());
        } else {
            OtherUserPageActivity.startActivity(this, mMyFollowFansModels.get(position).getFansId());

        }
    }

    public static void startMyFollowActivity(Context context, int type) {
        Intent intent = new Intent(context, MyFollowFansActivity.class);
        intent.putExtra("listType", type);
        context.startActivity(intent);
    }


}
