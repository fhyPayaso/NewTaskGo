package com.hrsoft.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterActivity;
import com.hrsoft.taskgo.mvp.contract.mine.OtherUserPageContract;
import com.hrsoft.taskgo.mvp.model.mine.response.OtherUserPageModel;
import com.hrsoft.taskgo.mvp.presenter.mine.OtherUserPagePresenter;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author：lszr on 2018/5/10 21:56
 * @email：1085963811@qq.com
 */
public class OtherUserPageActivity extends BasePresenterActivity<OtherUserPageContract.Presenter> implements OtherUserPageContract.View {

    @BindView(R.id.img_portrait)
    CircleImageView mImgPortrait;
    @BindView(R.id.txt_personal_name)
    TextView mTxtPersonalName;
    @BindView(R.id.txt_mine_follower)
    TextView mTxtMineFollower;
    @BindView(R.id.txt_mine_fans)
    TextView mTxtMineFans;
    @BindView(R.id.txt_personal_phone)
    TextView mTxtPersonalPhone;
    @BindView(R.id.txt_personal_sex)
    TextView mTxtPersonalSex;
    @BindView(R.id.btn_concentrate)
    Button mBtnConcentrate;

    private OtherUserPageModel mOtherUserPageModel;
    private Boolean mIsConcentrating;

    @Override
    public void onLoadOtherUserPageSuccess(OtherUserPageModel otherUserPageModel) {
        mOtherUserPageModel = otherUserPageModel;
        mIsConcentrating = mOtherUserPageModel.getFollowing();
        valueDefaultSet();
        initPage();

    }

    @Override
    public void onConcentrateSBSuccess() {
        mBtnConcentrate.setText(getString(R.string.txt_have_concentrate));
        mIsConcentrating=true;
        ToastUtil.showToast(getString(R.string.txt_concentrate_success));
        mOtherUserPageModel.setFansCount(mOtherUserPageModel.getFansCount()+1);
        mTxtMineFans.setText(String .valueOf(mOtherUserPageModel.getFansCount()));
    }

    @Override
    public void onUnConcentrateSBSuccess() {
        mBtnConcentrate.setText(getString(R.string.txt_none_concentrate));
        mIsConcentrating=false;
        ToastUtil.showToast(getString(R.string.txt_un_concentrate_success));
        mOtherUserPageModel.setFansCount(mOtherUserPageModel.getFansCount()-1);

        mTxtMineFans.setText(String.valueOf(mOtherUserPageModel.getFansCount()));

    }

    private void initPage() {
        Glide
                .with(OtherUserPageActivity.this)
                .load(mOtherUserPageModel.getAvatar())
                .into(mImgPortrait);

        mTxtMineFans.setText(String.valueOf(mOtherUserPageModel.getFansCount()));
        mTxtMineFollower.setText(String.valueOf(mOtherUserPageModel.getFollowCount()));
        mTxtPersonalName.setText(mOtherUserPageModel.getName());
        mTxtPersonalPhone.setText(mOtherUserPageModel.getMobile());
        mTxtPersonalSex.setText(mOtherUserPageModel.getSex());
        if (mIsConcentrating) {
            mBtnConcentrate.setText(getString(R.string.txt_have_concentrate));
        }else{
            mBtnConcentrate.setText(getString(R.string.txt_none_concentrate));
        }
    }

    private void valueDefaultSet() {
        if (mOtherUserPageModel.getAvatar() == null) {
            mOtherUserPageModel.setAvatar(getString(R.string.default_avater));
        }
        if (mOtherUserPageModel.getMobile() == null) {
            mOtherUserPageModel.setMobile(getString(R.string.txt_gender_unknown));
        }
        if (mOtherUserPageModel.getName() == null) {
            mOtherUserPageModel.setName(getString(R.string.txt_gender_unknown));
        }
        if (mOtherUserPageModel.getSex() == null) {
            mOtherUserPageModel.setSex(getString(R.string.txt_gender_unknown));
        }
    }

    @Override
    protected OtherUserPageContract.Presenter getPresenter() {
        return new OtherUserPagePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_user;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        mPresenter.loadOtherUserPage(intent.getIntExtra("userId", 0));
    }

    public static void startActivity(Context context, int userId) {
        Intent intent = new Intent(context, OtherUserPageActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    @OnClick({R.id.btn_chat, R.id.btn_concentrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_chat:
                ToastUtil.showToast(getString(R.string.txt_no_public_function));
                break;
            case R.id.btn_concentrate:
                if(mIsConcentrating){
                    mPresenter.unConcentrateSB(getIntent().getIntExtra("userId", 0));
                }else {
                    mPresenter.concentrateSB(getIntent().getIntExtra("userId", 0));
                }

                break;

            default:
                break;
        }
    }


}
