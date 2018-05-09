package com.hrsoft.taskgo.mvp.view.task.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.TextView;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.contract.ReleaseTaskContract;
import com.hrsoft.taskgo.mvp.model.task.bean.CardPackageModel;
import com.hrsoft.taskgo.mvp.model.task.bean.ChooseCardModel;
import com.hrsoft.taskgo.mvp.model.task.request.WaterAttributesReqModel;
import com.hrsoft.taskgo.mvp.model.task.response.WxRepModel;
import com.hrsoft.taskgo.mvp.presenter.task.ReleaseTaskPresenter;
import com.hrsoft.taskgo.mvp.view.task.adapter.ChoosePagerAdapter;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.AlphaTransformer;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hrsoft.taskgo.common.Config.APP_ID;

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
    private String mTaskType;
    private CardPackageModel mCardPickInfo = new CardPackageModel();
    private ChoosePagerAdapter mPagerAdapter;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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








        ToastUtil.showToast("点击设置金额");
    }

    /**
     * 发布任务按钮点击事件
     */
    @OnClick(R.id.txt_release_task)
    public void onTxtReleaseTaskClicked() {
        upDateCardInfo();
        switch (mTaskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL:
                mPresenter.releaseWaterTask((WaterAttributesReqModel) getIntent().getSerializableExtra(mTaskType),
                        mCardPickInfo);
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

    @Override
    public void onLoadWxOrdersInfoSuccess(WxRepModel repModel) {
        mPresenter.openWxPayPage(App.getInstance(), repModel);
    }

    @Override
    public void onLoadWxOrdersInfoError(String error) {
        ToastUtil.showToast(error);
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
}
