package com.hrsoft.taskgo.business.task.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.business.task.contract.ReleaseTaskContract;
import com.hrsoft.taskgo.business.task.model.bean.CardPackageModel;
import com.hrsoft.taskgo.business.task.model.bean.ChooseCardModel;
import com.hrsoft.taskgo.business.task.model.request.WaterAttributesReqModel;
import com.hrsoft.taskgo.business.task.model.response.WxRepModel;
import com.hrsoft.taskgo.business.task.presenter.ReleaseTaskPresenter;
import com.hrsoft.taskgo.business.task.view.adapter.ChoosePagerAdapter;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.AlphaTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fhyPayaso
 * @since 2018/4/29 on 下午11:21
 * fhyPayaso@qq.com
 */
public class ReleaseTaskActivity extends BaseToolBarPresenterActivity<ReleaseTaskContract.Presenter> implements
        ReleaseTaskContract.View {


    @BindView(R.id.vp_card_pager)
    ViewPager vpCardPager;
    @BindView(R.id.txt_set_task_money)
    TextView txtSetTaskMoney;
    @BindView(R.id.txt_task_money)
    TextView txtTaskMoney;
    @BindView(R.id.txt_release_task)
    TextView txtReleaseTask;


    private List<ChooseCardModel> mCardModelList;
    /**
     * 回调的TaskId，用于查询支付状态
     */
    public static int sTaskId = -1;
    /**
     * 要发布的任务类型
     */
    public static String mTaskType;
    /**
     * 卡片信息
     */
    private CardPackageModel mCardPickInfo = new CardPackageModel();
    private ChoosePagerAdapter mPagerAdapter;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_task;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTaskType = getIntent().getStringExtra(TaskTypeConfig.KEY_TASK_TYPE);
        mPresenter.loadUserCardsInfo();
    }

    @Override
    protected void initView() {
        setActivityTitle("发布任务");
        countMoney();
    }


    @Override
    protected ReleaseTaskContract.Presenter getPresenter() {
        return new ReleaseTaskPresenter(this);
    }


    /**
     * 设置金额点击事件
     */
    @OnClick(R.id.txt_set_task_money)
    public void onTxtSetTaskMoneyClicked() {
        switch (mTaskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL:
                ToastUtil.showToast("送水任务不支持修改金额");
                break;
            default:
                break;
        }
    }

    /**
     * 发布任务按钮点击事件
     */
    @OnClick(R.id.txt_release_task)
    public void onTxtReleaseTaskClicked() {


        upDateCardInfo();
        switch (mTaskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL:
                mPresenter.releaseWaterTask((WaterAttributesReqModel) getIntent().getSerializableExtra(mTaskType), mCardPickInfo);
                break;
            default:
                break;
        }
    }

    @Override
    public void loadUserCardsInfoSuccess(List<ChooseCardModel> cardModelList) {
        this.mCardModelList = cardModelList;
        vpCardPager.setOffscreenPageLimit(3);
        vpCardPager.setPageTransformer(false, new AlphaTransformer());
        mPagerAdapter = new ChoosePagerAdapter(getSupportFragmentManager(), mCardModelList);
        vpCardPager.setAdapter(mPagerAdapter);
    }


    /**
     * 打开微信客户端
     *
     * @param repModel
     */
    @Override
    public void onLoadWxOrdersInfoSuccess(WxRepModel repModel) {
        //sTaskId = repModel.getTaskId();

        mPresenter.openWxPayPage(App.getInstance(), repModel);
    }

    @Override
    public void onLoadWxOrdersInfoError(String error) {
        ToastUtil.showToast(error);
    }

    @Override
    public void showDialog() {
        txtReleaseTask.setClickable(false);
        showProgressDialog();
    }


    /**
     * 更新卡片选择信息
     */
    private void upDateCardInfo() {
        List<ChooseCardModel> modelList = mPagerAdapter.getPickCardInfo();
        mCardPickInfo.setGoodPeople(modelList.get(0).getChooseNumber());
    }


    /**
     * 初始化money
     */
    private void countMoney() {

        switch (mTaskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL:
                WaterAttributesReqModel model = (WaterAttributesReqModel) getIntent().getSerializableExtra(mTaskType);
                if (model.getSend().equals("0")) {
                    txtTaskMoney.setText("8");
                } else {
                    txtTaskMoney.setText("9");
                }
                break;
            default:
                break;

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
