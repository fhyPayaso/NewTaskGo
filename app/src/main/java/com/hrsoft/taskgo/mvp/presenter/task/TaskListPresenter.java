package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.model.task.TaskHelper;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/29 20:48.
 * email fanhongyu@hrsoft.net.
 */

public class TaskListPresenter extends BasePresenter<TaskListContract.View> implements TaskListContract
        .Presenter {

    public TaskListPresenter(TaskListContract.View view) {
        super(view);
    }

    @Override
    public void loadTaskList(String taskType) {
        switch (taskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL:
                loadSchoolSixWaterTaskList();
                break;
            default:
                break;
        }
    }

    @Override
    public void acceptTask(BaseTaskModel model,int position) {


        mView.onAcceptTaskSuccess(position);
    }


    /**
     * 加载校六送水任务列表
     */
    private void loadSchoolSixWaterTaskList() {


        IDataCallback.Callback<List<BaseTaskModel>> loadListCallBack = new IDataCallback.Callback<List<BaseTaskModel>>() {
            @Override
            public void onFailedLoaded(String error) {
                mView.onLoadTaskListError(error);
            }

            @Override
            public void onDataLoaded(List<BaseTaskModel> taskModelList) {
                mView.onLoadTaskListSuccess(taskModelList);
            }
        };

        addNotifyListener(loadListCallBack,TaskHelper.getInstance());
        TaskHelper.getInstance().loadSchoolSixWaterTaskList(loadListCallBack);
    }

}
