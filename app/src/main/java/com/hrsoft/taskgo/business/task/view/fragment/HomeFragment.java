package com.hrsoft.taskgo.business.task.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.fragment.BaseFragment;
import com.hrsoft.taskgo.business.task.view.activity.TaskGridActivity;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.BannerPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

/**
 * @author FanHongyu.
 * @since 18/4/27 11:49.
 * email fanhongyu@hrsoft.net.
 */

public class HomeFragment extends BaseFragment implements BannerPager.OnBannerClick {


    @BindView(R.id.vp_banner_pager)
    BannerPager vpBannerPager;
    @BindView(R.id.card_student_company)
    CardView cardStudentCompany;
    @BindView(R.id.card_diy_task)
    CardView cardDiyTask;
    @BindView(R.id.card_money_task)
    CardView cardMoneyTask;
    @BindView(R.id.card_help_task)
    CardView cardHelpTask;
    @BindView(R.id.card_promotion_task)
    CardView cardPromotionTask;
    @BindView(R.id.card_recruitment_task)
    CardView cardRecruitmentTask;

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
        vpBannerPager.setOnBannerClick(this);
        vpBannerPager.addView(getImageView(R.drawable.bg_banner1));
        vpBannerPager.addView(getImageView(R.drawable.bg_banner2));
        vpBannerPager.addView(getImageView(R.drawable.bg_banner3));
        vpBannerPager.startFlipping();
    }

    /**
     * 获取资源图片
     *
     * @return
     */
    private ImageView getImageView(int imgUrl) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(imgUrl);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @OnClick({R.id.card_student_company, R.id.card_diy_task, R.id.card_money_task, R.id.card_help_task, R.id
            .card_promotion_task, R.id.card_recruitment_task})
    public void onViewClicked(View view) {

        Intent intent = new Intent(getContext(), TaskGridActivity.class);
        switch (view.getId()) {
            case R.id.card_student_company:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_COLLEGE);
                getContext().startActivity(intent);
                break;
            case R.id.card_diy_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_DIY);
                ToastUtil.showToast("敬请期待");
                break;
            case R.id.card_money_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_MONEY);
                getContext().startActivity(intent);
                break;
            case R.id.card_help_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_HELP);
                getContext().startActivity(intent);
                break;
            case R.id.card_promotion_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_PROMOTION);
                getContext().startActivity(intent);
                break;
            case R.id.card_recruitment_task:
                intent.putExtra(TaskTypeConfig.KEY_MODULE_TYPE, TaskTypeConfig.MODEL_OFFER);
                getContext().startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBannerClick(int position) {

    }

}
