package com.hrsoft.taskgo.business.task.presenter;

import android.os.Handler;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.business.task.contract.TaskListContract;
import com.hrsoft.taskgo.business.task.model.TaskHelper;
import com.hrsoft.taskgo.business.task.model.bean.BaseTaskModel;
import com.hrsoft.taskgo.business.task.model.request.AcceptTaskReqModel;
import com.hrsoft.taskgo.business.task.model.response.TasListRespModel;
import com.hrsoft.taskgo.business.task.model.response.WaterAttributesRespModel;

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
    public void loadTaskList(final String taskType, final int page) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (taskType) {
                    case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL:
                        loadSchoolWaterTaskList(page);
                        break;
                    default:
                        break;
                }
            }
        }, 1000);
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
        List<Integer> list = new ArrayList<>();
        list.add(model.getTaskId());
        AcceptTaskReqModel reqModel = new AcceptTaskReqModel(list);
        TaskHelper.getInstance().acceptTask(this, reqModel, callback);
    }

    @Override
    public void acceptAllTask(List<BaseTaskModel> modelList) {

        if (modelList == null || modelList.size() == 0) {
            mView.onAcceptTaskError("当前任务列表为空");
        } else {
            IDataCallback.Callback callback = new IDataCallback.Callback() {
                @Override
                public void onFailedLoaded(String error) {
                    mView.onAcceptTaskError(error);
                }

                @Override
                public void onDataLoaded(Object o) {
                    mView.onAcceptAllTaskSuccess();
                }
            };
            List<Integer> list = new ArrayList<>();

            for (BaseTaskModel model : modelList) {
                list.add(model.getTaskId());
            }
            TaskHelper.getInstance().acceptTask(this, new AcceptTaskReqModel(list), callback);
        }
    }


    /**
     * 加载校六送水任务列表
     */
    private void loadSchoolWaterTaskList(final int page) {


        IDataCallback.Callback loadListCallBack = new IDataCallback.Callback<List<TasListRespModel<WaterAttributesRespModel>>>() {


            @Override
            public void onDataLoaded(List<TasListRespModel<WaterAttributesRespModel>> tasListRespModels) {


                if (mView != null) {

                    if (tasListRespModels == null || tasListRespModels.size() == 0) {
                        if (page == 1) {
                            mView.onLoadTaskListError("当前没有可接受的任务");
                        } else {
                            mView.onLoadTaskListError("暂无更多");
                        }
                    } else {
                        List<BaseTaskModel> baseTaskModels = new ArrayList<>();
                        for (TasListRespModel<WaterAttributesRespModel> respModel : tasListRespModels) {
                            BaseTaskModel model = new BaseTaskModel();
                            model.setUserName(respModel.getUserName() == null ? "" : respModel.getUserName());
                            model.setAvatarUrl(respModel.getAvatarImgUrl());
                            model.setTaskType("校内送水");
                            model.setCardNumber(respModel.getCardsJson().getGoodPeople());
                            model.setMoney(Double.valueOf(respModel.getAttributes().getMoney()));

                            model.setFirstTitle("宿舍楼 : ");
                            model.setFirstValue(String.valueOf(respModel.getAttributes().getApartment()));
                            model.setSecondTitle("宿舍号 : ");
                            model.setSecondValue(respModel.getAttributes().getAddress());
                            model.setThirdTitle("送水类型 : ");
                            model.setThirdValue(respModel.getAttributes().getSendType().equals("0") ? "送水上门" : "自取");
                            model.setTaskId(respModel.getId());
                            model.setTaskStatus(respModel.getAttributes().getStatus());
                            model.setTaskPayStatus(respModel.getAttributes().getPayStatus());
                            model.setUserId(respModel.getUserId());
                            baseTaskModels.add(model);
                        }
                        mView.onLoadTaskListSuccess(baseTaskModels);
                    }

                }
            }

            @Override
            public void onFailedLoaded(String error) {

                if (mView != null) {
                    mView.onLoadTaskListError(error);
                }
            }
        };
        TaskHelper.getInstance().loadSchoolWaterTaskList(this, page, loadListCallBack);
    }
}
