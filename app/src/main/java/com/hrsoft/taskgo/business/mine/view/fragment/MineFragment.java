package com.hrsoft.taskgo.business.mine.view.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterFragment;
import com.hrsoft.taskgo.business.mine.view.activity.MyWalletActivity;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.common.MyTaskConfig;
import com.hrsoft.taskgo.business.mine.contract.MineInformationContract;
import com.hrsoft.taskgo.business.mine.model.response.MineInformationModel;
import com.hrsoft.taskgo.business.mine.presenter.MineInformationPresenter;
import com.hrsoft.taskgo.business.mine.view.activity.EditDataActivity;
import com.hrsoft.taskgo.business.mine.view.activity.FeedbackActivity;
import com.hrsoft.taskgo.business.mine.view.activity.MineCardActivity;
import com.hrsoft.taskgo.business.mine.view.activity.MyFollowFansActivity;
import com.hrsoft.taskgo.business.mine.view.activity.RealNameActivity;
import com.hrsoft.taskgo.business.mine.view.activity.SettingActivity;
import com.hrsoft.taskgo.business.task.view.activity.MyTaskListActivity;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * @author FanHongyu.
 * @since 18/4/27 11:49.
 * email fanhongyu@hrsoft.net.
 */

public class MineFragment extends BasePresenterFragment<MineInformationContract.Presenter> implements
        MineInformationContract.View {

    private MineInformationModel mMineInformationModel;
    private Boolean ifHaveRealName;


    @BindView(R.id.btn_release)
    RelativeLayout mBtnRelease;
    @BindView(R.id.btn_accept)
    RelativeLayout mBtnAccept;
    @BindView(R.id.img_portrait)
    CircleImageView imgPortrait;
    @BindView(R.id.txt_mine_follower)
    TextView txtMineFollower;
    @BindView(R.id.txt_mine_fans)
    TextView txtMineFans;


    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    /**
     * 初始化View.
     */
    @Override
    protected void initView() {
        mPresenter.loadMineInformation();
    }

    /**
     * 初始数据
     */
    @Override
    protected void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();

        mPresenter.loadMineInformation();

    }

    @OnClick({R.id.rlayout_mine_follow,
            R.id.rlayout_mine_fans,
            R.id.btn_release,
            R.id.btn_accept,
            R.id.rlayout_mine_card,
            R.id.rlayout_mine_real_name,
            R.id.rlayout_mine_feedback,
            R.id.rlayout_mine_setting,
            R.id.rlayout_mine_edit,
            R.id.rlayout_mine_money_package,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlayout_mine_follow:
                MyFollowFansActivity.startMyFollowActivity(getContext(), Config.MY_FOLLOW_LIST);
                break;
            case R.id.rlayout_mine_fans:
                MyFollowFansActivity.startMyFollowActivity(getContext(), Config.MY_FANS_LIST);
                break;
            case R.id.btn_release:
                MyTaskListActivity.startActivity(getContext(), MyTaskConfig.MY_RELEASE_TASK);
                break;
            case R.id.btn_accept:
                MyTaskListActivity.startActivity(getContext(), MyTaskConfig.MY_ACCEPT_TASK);
                break;
            case R.id.rlayout_mine_real_name:
                RealNameActivity.startRealNameActivity(getContext(), ifHaveRealName);
                break;
            case R.id.rlayout_mine_feedback:
                FeedbackActivity.startFeedbackActivity(getContext());
                break;
            case R.id.rlayout_mine_setting:
                SettingActivity.startSettingActivity(getContext());
                break;
            case R.id.rlayout_mine_edit:
                EditDataActivity.startEditDataActivity(getContext(), mMineInformationModel);
                break;
            case R.id.rlayout_mine_card:
                MineCardActivity.startMineCardActivity(getContext());
                break;
            case R.id.rlayout_mine_money_package:
                MyWalletActivity.startActivity(getContext());
                break;
            default:
                break;
        }
    }


    @Override
    protected MineInformationContract.Presenter getPresenter() {
        return new MineInformationPresenter(this);
    }

    @Override
    public void onLoadMineInformationSuccess(MineInformationModel mineInformationModel) {
        mMineInformationModel = mineInformationModel;
        if (mMineInformationModel.getStatus() == 0) {
            ifHaveRealName = false;
        } else {
            ifHaveRealName = true;
        }
        if (checkInformation(mineInformationModel)) {
            txtMineFollower.setText(String.valueOf(mineInformationModel.getFollowCount()));
            txtMineFans.setText(String.valueOf(mineInformationModel.getFansCount()));

            Glide.with(getContext())
                    .load(mineInformationModel.getAvatar())
                    .transition(withCrossFade())
                    .into(imgPortrait);
        }
    }

    private boolean checkInformation(MineInformationModel mineInformationModel) {
        if (String.valueOf(mineInformationModel.getFollowCount()).equals(getString(R.string.empty))) {
            ToastUtil.showToast(getString(R.string.error_follower));
            txtMineFollower.setText("N/A");
            return false;
        } else if (String.valueOf(mineInformationModel.getFansCount()).equals(getString(R.string.empty))) {
            ToastUtil.showToast(getString(R.string.error_fans));
            txtMineFollower.setText("N/A");
            return false;
        } else if (mineInformationModel.getAvatar().equals(getString(R.string.empty))) {

            Glide.with(getContext())
                    .load(getString(R.string.default_avater))
                    .transition(withCrossFade())
                    .into(imgPortrait);

            ToastUtil.showToast(getString(R.string.error_avater));
            return false;
        }


        return true;
    }
}
