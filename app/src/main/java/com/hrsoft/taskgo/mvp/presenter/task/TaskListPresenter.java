package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.contract.TaskListContract;
import com.hrsoft.taskgo.mvp.model.task.TaskHelper;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;
import com.hrsoft.taskgo.mvp.model.task.response.TasListRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.WaterAttributesRespModel;

import java.util.ArrayList;
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
                loadSchoolWaterTaskList();
                break;
            default:
                break;
        }
    }

    @Override
    public void acceptTask(BaseTaskModel model, final int position) {


        IDataCallback.Callback callback = new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {
                mView.onAcceptTaskError(error);
            }

            @Override
            public void onDataLoaded(Object o) {
                mView.onAcceptTaskSuccess(position);
            }
        };
        // TODO: 2018/5/4 接受多个任务优化 
        List<Integer> list = new ArrayList<>();
        list.add(model.getTaskId());
        TaskHelper.getInstance().acceptTask(list, callback);
    }


    /**
     * 加载校六送水任务列表
     */
    private void loadSchoolWaterTaskList() {


        IDataCallback.Callback loadListCallBack = new IDataCallback
                .Callback<List<TasListRespModel<WaterAttributesRespModel>>>() {


            @Override
            public void onDataLoaded(List<TasListRespModel<WaterAttributesRespModel>> tasListRespModels) {


                List<BaseTaskModel> baseTaskModels = new ArrayList<>();

                for (TasListRespModel<WaterAttributesRespModel> respModel : tasListRespModels) {
                    BaseTaskModel model = new BaseTaskModel();
                    model.setUserName(respModel.getUserName() == null ? "" : respModel.getUserName());
                    model.setAvatarUrl(respModel.getAvatarImgUrl());
                    model.setTaskType("校内送水");
                    model.setCardNumber(respModel.getCardsModel().getGoodPeople());
                    model.setMoney(Double.valueOf(respModel.getAttributes().getMoney()));
                    model.setFirstTitle("宿舍号 : ");
                    model.setFirstValue(respModel.getAttributes().getAddress());
                    model.setSecondTitle("送水类型 : ");
                    model.setSecondValue(respModel.getAttributes().getSendType().equals("0") ? "送水上门" : "自取");
                    model.setTaskId(respModel.getId());
                    baseTaskModels.add(model);
                }
                mView.onLoadTaskListSuccess(baseTaskModels);
            }

            @Override
            public void onFailedLoaded(String error) {
                mView.onLoadTaskListError(error);
            }
        };

        TaskHelper.getInstance().loadSchoolWaterTaskList(this, loadListCallBack);
    }


}
