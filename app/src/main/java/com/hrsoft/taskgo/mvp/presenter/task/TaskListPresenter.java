package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.model.task.TaskHelper;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/29 20:48.
 * email fanhongyu@hrsoft.net.
 */

public class TaskListPresenter extends BasePresenter<TaskHelper, TaskListContract.View> implements TaskListContract
        .Presenter {

    @Override
    public void loadTaskList(String taskType) {
        switch (taskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL_SIX:
                loadSchoolSixWaterTaskList();
                break;
            default:
                break;
        }
    }

    @Override
    public void acceptTask(BaseTaskModel model) {


        mView.onAcceptTaskSuccess(model);
    }


    /**
     * 加载校六送水任务列表
     */
    private void loadSchoolSixWaterTaskList() {


        getModel().loadSchoolSixWaterTaskList(new IDataCallback.Callback<List<BaseTaskModel>>() {
            @Override
            public void onFailedLoaded(String error) {
                mView.onLoadTaskListError(error);
            }

            @Override
            public void onDataLoaded(List<BaseTaskModel> taskModelList) {
                mView.onLoadTaskListSuccess(taskModelList);
            }
        });
    }
}
