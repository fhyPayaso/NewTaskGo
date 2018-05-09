package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.MyTaskConfig;
import com.hrsoft.taskgo.mvp.contract.MyTaskListContract;
import com.hrsoft.taskgo.mvp.model.task.TaskHelper;
import com.hrsoft.taskgo.mvp.model.task.response.TasListRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.WaterAttributesRespModel;

import java.util.List;

/**
 * @author fhyPayaso
 * @since 2018/5/10 on 上午12:12
 * fhyPayaso@qq.com
 */
public class MyTaskListPresenter extends BasePresenter<MyTaskListContract.View> implements MyTaskListContract.Presenter {

    public MyTaskListPresenter(MyTaskListContract.View view) {
        super(view);
    }


    @Override
    public void loadTaskList(String taskType, int page) {
        switch (taskType) {
            case MyTaskConfig.MY_RELEASE_NOT_ACCEPTED:
                loadMyReleaseTaskList(0, page);
                break;
            case MyTaskConfig.MY_RELEASE_HAS_ACCEPTED:
                loadMyReleaseTaskList(1, page);
                break;
            case MyTaskConfig.MY_RELEASE_HAS_FINISHED:
                loadMyReleaseTaskList(4, page);
                break;
            case MyTaskConfig.MY_ACCEPT_NOT_FINISHED:
                loadMyAcceptTaskList(1, page);
                break;
            case MyTaskConfig.MY_ACCEPT_HAS_FINISHED:
                loadMyAcceptTaskList(4, page);
                break;
            default:
                break;
        }
    }


    /**
     * 我接受的任务
     *
     * @param page
     */
    private void loadMyAcceptTaskList(int status, int page) {



        IDataCallback.Callback<List<TasListRespModel<String>>> callback = new IDataCallback.Callback<List<TasListRespModel<String>>>() {
            @Override
            public void onFailedLoaded(String error) {
                mView.onLoadTaskListError(error);
            }

            @Override
            public void onDataLoaded(List<TasListRespModel<String>> tasListRespModels) {

            }
        };

        TaskHelper.getInstance().loadMyAcceptTaskList(this,page,status,callback);
    }


    /**
     * 我发布的任务
     *
     * @param status
     * @param page
     */
    private void loadMyReleaseTaskList(int status, int page) {

        IDataCallback.Callback<List<TasListRespModel<String>>> callback = new IDataCallback.Callback<List<TasListRespModel<String>>>() {
            @Override
            public void onFailedLoaded(String error) {
                mView.onLoadTaskListError(error);
            }

            @Override
            public void onDataLoaded(List<TasListRespModel<String>> tasListRespModels) {

            }
        };
        TaskHelper.getInstance().loadMyReleaseTaskList(this,page,status,callback);
    }










}
