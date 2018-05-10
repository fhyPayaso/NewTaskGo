package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.contract.FillTaskInfContract;
import com.hrsoft.taskgo.utils.RegexpUtils;

/**
 * @author FanHongyu.
 * @since 18/5/4 20:51.
 * email fanhongyu@hrsoft.net.
 */

public class FillTaskInfoPresenter extends BasePresenter<FillTaskInfContract.View> implements FillTaskInfContract
        .Presenter {


    public FillTaskInfoPresenter(FillTaskInfContract.View view) {
        super(view);
    }

    @Override
    public void onCheckWaterTaskInfo(String address) {

        if (address.equals("")) {
            mView.onCheckDataError("请填宿舍号");
        } else if (address.length() != 4 || !RegexpUtils.checkAllNumber(address)) {
            mView.onCheckDataError("宿舍号格式错误");
        } else {
            mView.onCheckDataTrue();
        }
    }
}
