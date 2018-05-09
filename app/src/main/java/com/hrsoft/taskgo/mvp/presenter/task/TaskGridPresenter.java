package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.contract.TaskGridContract;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskGridModel;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskTypeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/29 16:57.
 * email fanhongyu@hrsoft.net.
 */

public class TaskGridPresenter extends BasePresenter<TaskGridContract.View> implements TaskGridContract
        .Presenter {


    public TaskGridPresenter(TaskGridContract.View view) {
        super(view);
    }

    @Override
    public void getGridList(String moduleType) {

        switch (moduleType) {
            case TaskTypeConfig.COLLEGE:
                getCollegeLists();
                break;
            default:
                break;
        }
    }

    /**
     * 获取
     */
    @Override
    public void getCollegeLists() {

        List<TaskGridModel> gridModelList = new ArrayList<>();
        //自主创业模块
        List<TaskTypeModel> businessList = new ArrayList<>();
        businessList.add(new TaskTypeModel(R.drawable.ic_school_water, "校内送水", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL));
        businessList.add(new TaskTypeModel(R.drawable.ic_snacks, "零食店铺", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_SNACKS));
        businessList.add(new TaskTypeModel(R.drawable.ic_express_delivery, "带领快递", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_EXPRESS));
        gridModelList.add(new TaskGridModel("自主创业", businessList));

        //公益服务模块
        List<TaskTypeModel> welfareList = new ArrayList<>();
        welfareList.add(new TaskTypeModel(R.drawable.ic_e_computer, "E管家", TaskTypeConfig
                .COLLEGE_PUBLIC_GOOD_E_HELPER));
        gridModelList.add(new TaskGridModel("公益服务", welfareList));
        mView.onLoadGridListSuccess(gridModelList);
    }
}
