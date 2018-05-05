package com.hrsoft.taskgo.mvp.model.task;

import android.os.Handler;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.INotifyListener;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.model.account.helper.AccountHelper;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;
import com.hrsoft.taskgo.mvp.model.task.request.ReleaseTaskReqModel;
import com.hrsoft.taskgo.mvp.model.task.request.WaterAttributesReqModel;
import com.hrsoft.taskgo.mvp.model.task.response.TasListRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.WaterAttributesRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.WxRepModel;
import com.hrsoft.taskgo.mvp.presenter.task.TaskListPresenter;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

import java.util.ArrayList;
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
    public void loadSchoolWaterTaskList(IBaseContract.IBasePresenter presenter, final IDataCallback
            .Callback<List<TasListRespModel<WaterAttributesRespModel>>> callback) {

        addNotifyListener(presenter,callback);


        NetworkFactory
                .getService()
                .loadWaterTaskList()
                .compose(BaseObserver.<ApiResponse<List<TasListRespModel<WaterAttributesRespModel>>>>setThread())
                .subscribe(new BaseObserver<List<TasListRespModel<WaterAttributesRespModel>>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<TasListRespModel<WaterAttributesRespModel>>> response) {
                        callback.onDataLoaded(response.getData());
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
    public void releaseWaterTask(IBaseContract.IBasePresenter presenter,final ReleaseTaskReqModel<WaterAttributesReqModel> reqModel, final IDataCallback
            .Callback<WxRepModel> callback) {


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
     * 接受任务网络请求
     *
     * @param callback
     */
    @SuppressWarnings("unchecked")
    public void acceptTask(List<Integer> taskArray, final IDataCallback.Callback callback) {


        NetworkFactory
                .getService()
                .acceptWaterTask(taskArray)
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
}
