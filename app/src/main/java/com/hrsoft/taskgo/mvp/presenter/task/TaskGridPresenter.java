package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskGridModel;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskTypeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/29 16:57.
 * email fanhongyu@hrsoft.net.
 */

public class TaskGridPresenter extends BasePresenter<BaseModel, TaskGridContract.View> implements TaskGridContract
        .Presenter {


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
        businessList.add(new TaskTypeModel(R.drawable.tab_home_normal, "校六送水", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL_SIX));
        businessList.add(new TaskTypeModel(R.drawable.tab_home_normal, "零食店铺", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL_SIX));
        businessList.add(new TaskTypeModel(R.drawable.tab_home_normal, "带领快递", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL_SIX));
        gridModelList.add(new TaskGridModel("自主创业", businessList));


        //公益服务模块
        List<TaskTypeModel> welfareList = new ArrayList<>();
        welfareList.add(new TaskTypeModel(R.drawable.tab_home_normal, "E管家", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL_SIX));
        gridModelList.add(new TaskGridModel("公益服务", welfareList));


        mView.onLoadGridListSuccess(gridModelList);
    }


}
