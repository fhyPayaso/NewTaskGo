package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.TaskTypeConfig;

/**
 * @author FanHongyu.
 * @since 18/4/29 20:48.
 * email fanhongyu@hrsoft.net.
 */

public class TaskListPresenter extends BasePresenter<BaseModel, TaskListContract.View> implements TaskListContract
        .Presenter {

    @Override
    public void loadTaskList(String taskType) {
        switch (taskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL_SIX:
                loadSchoolSixWaterTaskList();
            default:
                break;
        }
    }


    /**
     * 加载校六送水任务列表
     */
    private void loadSchoolSixWaterTaskList() {
















    }


}
