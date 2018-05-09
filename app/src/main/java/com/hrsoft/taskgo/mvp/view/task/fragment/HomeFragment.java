package com.hrsoft.taskgo.mvp.view.task.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.fragment.BaseFragment;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.view.task.activity.TaskGridActivity;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.BannerPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author FanHongyu.
 * @since 18/4/27 11:49.
 * email fanhongyu@hrsoft.net.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.vp_banner_pager)
    BannerPager vpBannerPager;
    Unbinder unbinder;

    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    /**
     * 初始化View.
     */
    @Override
    protected void initView() {

        initBanner();
    }

    /**
     * 初始数据
     */
    @Override
    protected void initData() {

    }


    private void initBanner() {
        vpBannerPager.addView(getImageView(R.drawable.home_banner1));
        vpBannerPager.addView(getImageView(R.drawable.home_banner2));
        vpBannerPager.addView(getImageView(R.drawable.home_banner3));
        vpBannerPager.addView(getImageView(R.drawable.home_banner4));
        vpBannerPager.startFlipping();
    }

    /**
     * 获取资源图片
     *
     * @param res
     * @return
     */
    private ImageView getImageView(int res) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(res);
        return imageView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.card_student_company, R.id.card_diy_task, R.id.card_money_task, R.id.card_help_task, R.id
            .card_promotion_task, R.id.card_recruitment_task})
    public void onViewClicked(View view) {


        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view,
                getString(R.string.key_home_animation));
        Intent intent = new Intent(getContext(), TaskGridActivity.class);
        switch (view.getId()) {
            case R.id.card_student_company:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_COLLEGE);
                ActivityCompat.startActivity(getContext(), intent, compat.toBundle());
                break;
            case R.id.card_diy_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_DIY);
                ToastUtil.showToast("敬请期待");
                break;
            case R.id.card_money_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_MONEY);
                ActivityCompat.startActivity(getContext(), intent, compat.toBundle());
                break;
            case R.id.card_help_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_HELP);
                ActivityCompat.startActivity(getContext(), intent, compat.toBundle());
                break;
            case R.id.card_promotion_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_PROMOTION);
                ActivityCompat.startActivity(getContext(), intent, compat.toBundle());
                break;
            case R.id.card_recruitment_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_OFFER);
                ActivityCompat.startActivity(getContext(), intent, compat.toBundle());
                break;
            default:
                break;
        }
    }
}
