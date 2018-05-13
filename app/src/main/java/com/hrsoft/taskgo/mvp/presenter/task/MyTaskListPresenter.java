package com.hrsoft.taskgo.mvp.presenter.task;

import android.os.Handler;

import com.google.gson.Gson;
import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.CacheKey;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.common.MyTaskConfig;
import com.hrsoft.taskgo.mvp.contract.task.MyTaskListContract;
import com.hrsoft.taskgo.mvp.model.task.TaskHelper;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;
import com.hrsoft.taskgo.mvp.model.task.response.TasListRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.WaterAttributesRespModel;
import com.hrsoft.taskgo.mvp.view.task.adapter.TaskListRecyclerAdapter;
import com.hrsoft.taskgo.utils.CacheUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * @author fhyPayaso
 * @since 2018/5/10 on 上午12:12
 * fhyPayaso@qq.com
 */
public class MyTaskListPresenter extends BasePresenter<MyTaskListContract.View> implements MyTaskListContract
        .Presenter {


    /**
     * 送水类型响应体
     */
    public static final String RESP_WATER = "water";


    private boolean mNeedCache = false;


    public MyTaskListPresenter(MyTaskListContract.View view) {
        super(view);
    }


    @Override
    public void loadTaskList(final String taskStatusType, final int page) {
        if (mView != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (taskStatusType) {
                        case MyTaskConfig.MY_RELEASE_NOT_ACCEPTED:
                            loadMyReleaseTaskList(MyTaskConfig.STATUS_NOT_ACCEPT, page);
                            break;
                        case MyTaskConfig.MY_RELEASE_HAS_ACCEPTED:
                            loadMyReleaseTaskList(MyTaskConfig.STATUS_HAS_ACCEPT, page);
                            break;
                        case MyTaskConfig.MY_RELEASE_HAS_FINISHED:
                            loadMyReleaseTaskList(MyTaskConfig.STATUS_HAS_FINISH, page);
                            break;
                        case MyTaskConfig.MY_ACCEPT_NOT_FINISHED:
                            loadMyAcceptTaskList(MyTaskConfig.STATUS_HAS_ACCEPT, page);
                            break;
                        case MyTaskConfig.MY_ACCEPT_HAS_FINISHED:
                            loadMyAcceptTaskList(MyTaskConfig.STATUS_HAS_FINISH, page);
                            break;
                        default:
                            break;
                    }
                }
            }, 1000);
        }
    }

    @Override
    public void getAdapterType(String taskStatusType) {

        int adapterBtnType = TaskListRecyclerAdapter.BTN_EMPTY;
        switch (taskStatusType) {
            case MyTaskConfig.MY_RELEASE_NOT_ACCEPTED:
                adapterBtnType = TaskListRecyclerAdapter.BTN_CANCEL_OR_PAY;
                break;
            case MyTaskConfig.MY_ACCEPT_NOT_FINISHED:
                adapterBtnType = TaskListRecyclerAdapter.BTN_APPLY_FINISH;
                break;
            default:
                break;
        }
        mView.initRecyclerView(adapterBtnType);
    }


    /**
     * 完成任务
     *
     * @param taskId
     */
    @Override
    public void finishTask(int taskId, final int position) {

        IDataCallback.Callback callback = new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {
                mView.finishTaskError(error);
            }

            @Override
            public void onDataLoaded(Object o) {
                mView.finishTaskSuccess(position);
            }
        };
        TaskHelper.getInstance().finishTask(this, taskId, callback);
    }

    /**
     * 我接受的任务
     *
     * @param page
     */
    private void loadMyAcceptTaskList(int status, final int page) {


        IDataCallback.Callback<List<TasListRespModel<String>>> callback = new IDataCallback
                .Callback<List<TasListRespModel<String>>>() {
            @Override
            public void onFailedLoaded(String error) {
                mView.onLoadTaskListError(error);
            }

            @Override
            public void onDataLoaded(List<TasListRespModel<String>> tasListRespModels) {
                processingData(tasListRespModels, page);

            }
        };

        TaskHelper.getInstance().loadMyAcceptTaskList(this, page, status, callback);
    }


    /**
     * 我发布的任务
     *
     * @param status
     * @param page
     */
    private void loadMyReleaseTaskList(int status, final int page) {

        IDataCallback.Callback<List<TasListRespModel<String>>> callback = new IDataCallback
                .Callback<List<TasListRespModel<String>>>() {
            @Override
            public void onFailedLoaded(String error) {
                mView.onLoadTaskListError(error);
            }

            @Override
            public void onDataLoaded(List<TasListRespModel<String>> tasListRespModels) {
                mNeedCache = true;
                processingData(tasListRespModels, page);
                mNeedCache = false;
            }
        };
        TaskHelper.getInstance().loadMyReleaseTaskList(this, page, status, callback);
    }


    /**
     * 任务处理过滤
     *
     * @param tasListRespModels
     * @param page
     */
    private void processingData(List<TasListRespModel<String>> tasListRespModels, int page) {

        if (mView != null) {

            if (tasListRespModels == null || tasListRespModels.size() == 0) {
                if (page == 1) {
                    mView.onLoadTaskListError("当前列表暂无任务");
                } else {
                    mView.onLoadTaskListError("暂无更多");
                }
            } else {
                List<BaseTaskModel> baseTaskModels = new ArrayList<>();
                for (TasListRespModel<String> respModel : tasListRespModels) {
                    switch (respModel.getType()) {
                        case RESP_WATER:
                            BaseTaskModel model = processingWaterTaskData(respModel);
                            if (model != null) {
                                baseTaskModels.add(model);
                            }
                            break;
                        default:
                            break;
                    }
                }
                mView.onLoadTaskListSuccess(baseTaskModels);
            }
        }
    }


    /**
     * 解析水任务数据类型
     *
     * @param respModel
     * @return
     */
    private BaseTaskModel processingWaterTaskData(TasListRespModel<String> respModel) {


        BaseTaskModel model = new BaseTaskModel();
        WaterAttributesRespModel waterRespModel = new Gson().fromJson(respModel.getAttributes(),
                WaterAttributesRespModel.class);


        if (mNeedCache) {
            CacheUtil cacheUtil = App.getInstance().getCacheUtil();
            model.setUserId(Integer.valueOf(cacheUtil.getString(CacheKey.USER_ID)));
            model.setAvatarUrl(cacheUtil.getString(CacheKey.USER_AVATAR));
            model.setUserName(cacheUtil.getString(CacheKey.USER_NAME));
        } else {
            model.setUserId(respModel.getUserId());
            model.setUserName(respModel.getUserName() == null ? "" : respModel.getUserName());
            model.setAvatarUrl(respModel.getAvatarImgUrl());

        }
        model.setTaskType("校内送水");
        model.setCardNumber(respModel.getCardsJson().getGoodPeople());
        model.setMoney(Double.valueOf(waterRespModel.getMoney()));
        model.setFirstTitle("宿舍楼 : ");
        model.setFirstValue(String.valueOf(waterRespModel.getApartment()));
        model.setSecondTitle("宿舍号 : ");
        model.setSecondValue(waterRespModel.getAddress());
        model.setThirdTitle("送水类型 : ");
        model.setThirdValue(waterRespModel.getSendType().equals("0") ? "送水上门" : "自取");
        model.setTaskId(respModel.getId());
        model.setTaskStatus(waterRespModel.getStatus());
        model.setTaskPayStatus(waterRespModel.getPayStatus());


        //先只展示支付成功的任务
        if (model.getTaskPayStatus() == 1) {
            return model;
        } else {
            return null;
        }
    }
}
