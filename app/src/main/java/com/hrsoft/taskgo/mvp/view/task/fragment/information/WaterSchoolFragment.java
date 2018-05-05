package com.hrsoft.taskgo.mvp.view.task.fragment.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.base.mvp.view.BasePresenterFragment;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.contract.FillTaskInfContract;
import com.hrsoft.taskgo.mvp.model.task.request.WaterAttributesReqModel;
import com.hrsoft.taskgo.mvp.presenter.task.FillTaskInfoPresenter;
import com.hrsoft.taskgo.mvp.view.task.activity.ReleaseTaskActivity;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 填写校内送水任务信息
 *
 * @author fhyPayaso
 * @since 2018/4/30 on 下午9:17
 * fhyPayaso@qq.com
 */
public class WaterSchoolFragment extends BasePresenterFragment<FillTaskInfContract.Presenter> implements
        FillTaskInfContract.View {


    @BindView(R.id.edit_address_number)
    EditText editAddressNumber;
    @BindView(R.id.txt_type_send)
    TextView txtTypeSend;
    @BindView(R.id.txt_type_self)
    TextView txtTypeSelf;
    @BindView(R.id.btn_release_task)
    TextView btnReleaseTask;
    Unbinder unbinder;


    /**
     * 记录送水类型
     */
    private int mWaterTaskType;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_water_school;
    }

    @Override
    protected void initView() {

        onTxtTypeSendClicked();
    }

    @Override
    protected void initData() {

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

    /**
     * 送水上门类型点击事件
     */
    @OnClick(R.id.txt_type_send)
    public void onTxtTypeSendClicked() {

        txtTypeSend.setSelected(true);
        txtTypeSelf.setSelected(false);
        mWaterTaskType = 0;
    }

    /**
     * 自取类型点击事件
     */
    @OnClick(R.id.txt_type_self)
    public void onTxtTypeSelfClicked() {
        txtTypeSend.setSelected(false);
        txtTypeSelf.setSelected(true);
        mWaterTaskType = 1;
    }

    @OnClick(R.id.btn_release_task)
    public void onBtnReleaseTaskClicked() {
        mPresenter.onCheckWaterTaskInfo(editAddressNumber.getText().toString());
    }

    @Override
    public void onCheckDataTrue() {
        WaterAttributesReqModel reqModel = new WaterAttributesReqModel("6", editAddressNumber.getText().toString(),
                String.valueOf(mWaterTaskType));


        Intent intent = new Intent(getContext(), ReleaseTaskActivity.class);
        intent.putExtra(TaskTypeConfig.KEY_TASK_TYPE, TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL);
        intent.putExtra(TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL, reqModel);
        startActivity(intent);
    }

    @Override
    public void onCheckDataError(String error) {
        ToastUtil.showToast(error);
    }

    @Override
    protected FillTaskInfContract.Presenter getPresenter() {
        return new FillTaskInfoPresenter(this);
    }
}
