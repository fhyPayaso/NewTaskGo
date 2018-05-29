package com.hrsoft.taskgo.business.task.model;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.business.task.model.request.AcceptTaskReqModel;
import com.hrsoft.taskgo.business.task.model.request.ReleaseTaskReqModel;
import com.hrsoft.taskgo.business.task.model.request.WaterAttributesReqModel;
import com.hrsoft.taskgo.business.task.model.response.TasListRespModel;
import com.hrsoft.taskgo.business.task.model.response.TaskListPrePageRespModel;
import com.hrsoft.taskgo.business.task.model.response.WaterAttributesRespModel;
import com.hrsoft.taskgo.business.task.model.response.WxRepModel;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

import java.util.List;


/**
 * @author fhyPayaso
 * @since 2018/4/30 on 下午5:53
 * fhyPayaso@qq.com
 */
public class TaskHelper extends BaseModel {


    private TaskHelper() {
    }


    public static class TaskHelperHolder {
        private static final TaskHelper INSTANCE = new TaskHelper();
    }

    public static TaskHelper getInstance() {
        return TaskHelperHolder.INSTANCE;
    }


    /**
     * 加载水任务列表网络请求
     *
     * @param callback
     */
    public void loadSchoolWaterTaskList(IBaseContract.IBasePresenter presenter, int page, final IDataCallback
            .Callback<List<TasListRespModel<WaterAttributesRespModel>>> callback) {

        addNotifyListener(presenter, callback);


        NetworkFactory
                .getService()
                .loadWaterTaskList(page)
                .compose(BaseObserver.<ApiResponse<TaskListPrePageRespModel<WaterAttributesRespModel>>>setThread())
                .subscribe(new BaseObserver<TaskListPrePageRespModel<WaterAttributesRespModel>>() {
                    @Override
                    public void onSuccess(ApiResponse<TaskListPrePageRespModel<WaterAttributesRespModel>> response) {


                        if (response.getData() != null) {
                            callback.onDataLoaded(response.getData().getData());
                        } else {
                            callback.onFailedLoaded("暂无数据");
                        }
                    }

                    @Override
                    public void onError(ApiException exception) {


                        callback.onFailedLoaded(exception.getMsg());

                    }
                });
    }


    /**
     * 发布水任务
     *
     * @param reqModel
     * @param callback
     */
    public void releaseWaterTask(IBaseContract.IBasePresenter presenter, final
    ReleaseTaskReqModel<WaterAttributesReqModel> reqModel, final IDataCallback.Callback<WxRepModel> callback) {

        addNotifyListener(presenter, callback);
        NetworkFactory
                .getService()
                .releaseWaterTask(reqModel)
                .compose(BaseObserver.<ApiResponse<WxRepModel>>setThread())
                .subscribe(new BaseObserver<WxRepModel>() {
                    @Override
                    public void onSuccess(ApiResponse<WxRepModel> response) {
                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    /**
     * 检查水任务是否支付成功
     *
     * @param presenter
     * @param taskId
     * @param callback
     */
    public void checkWaterTaskPayStatus(IBaseContract.IBasePresenter presenter, int taskId, final IDataCallback
            .Callback<String> callback) {

        addNotifyListener(presenter, callback);
        NetworkFactory
                .getService()
                .checkWaterTaskPayStatus(taskId)
                .compose(BaseObserver.<ApiResponse<String>>setThread())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(ApiResponse<String> response) {
                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onDataLoaded(exception.getMsg());
                    }
                });
    }

    /**
     * 退回卡片
     *
     * @param presenter
     * @param waterId
     * @param callback
     */
    @SuppressWarnings("unchecked")
    public void returnCard(IBaseContract.IBasePresenter presenter, int waterId, final IDataCallback.Callback callback) {
        addNotifyListener(presenter, callback);
        NetworkFactory
                .getService()
                .returnCard(waterId)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        callback.onDataLoaded(response);
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    /**
     * 接受任务网络请求
     *
     * @param callback
     */
    @SuppressWarnings("unchecked")
    public void acceptTask(IBaseContract.IBasePresenter presenter, AcceptTaskReqModel reqModel, final IDataCallback
            .Callback
            callback) {

        addNotifyListener(presenter, callback);
        NetworkFactory
                .getService()
                .acceptWaterTask(reqModel)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    /**
     * 加载我发布的任务列表
     *
     * @param presenter
     * @param page
     * @param status
     * @param callback
     */
    public void loadMyReleaseTaskList(IBaseContract.IBasePresenter presenter, int page, int status, final IDataCallback
            .Callback<List<TasListRespModel<String>>> callback) {


        addNotifyListener(presenter, callback);
        NetworkFactory
                .getService()
                .loadMyReleaseTaskList(status, page)
                .compose(BaseObserver.<ApiResponse<TaskListPrePageRespModel<String>>>setThread())
                .subscribe(new BaseObserver<TaskListPrePageRespModel<String>>() {
                    @Override
                    public void onSuccess(ApiResponse<TaskListPrePageRespModel<String>> response) {
                        callback.onDataLoaded(response.getData().getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    /**
     * 加载我接受的任务列表
     *
     * @param presenter
     * @param page
     * @param status
     * @param callback
     */
    public void loadMyAcceptTaskList(IBaseContract.IBasePresenter presenter, int page, int status, final IDataCallback
            .Callback<List<TasListRespModel<String>>> callback) {


        addNotifyListener(presenter, callback);
        NetworkFactory
                .getService()
                .loadMyAcceptTaskList(status, page)
                .compose(BaseObserver.<ApiResponse<TaskListPrePageRespModel<String>>>setThread())
                .subscribe(new BaseObserver<TaskListPrePageRespModel<String>>() {
                    @Override
                    public void onSuccess(ApiResponse<TaskListPrePageRespModel<String>> response) {
                        callback.onDataLoaded(response.getData().getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }

    /**
     * 完成任务网络请求
     *
     * @param presenter
     * @param taskId
     * @param callback
     */
    @SuppressWarnings("unchecked")
    public void finishTask(IBaseContract.IBasePresenter presenter, int taskId, final IDataCallback.Callback callback) {
        addNotifyListener(presenter, callback);
        NetworkFactory
                .getService()
                .finishTask(taskId)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        callback.onDataLoaded(response.getMsg());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


}
