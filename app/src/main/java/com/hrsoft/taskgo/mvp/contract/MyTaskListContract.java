package com.hrsoft.taskgo.mvp.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;

import java.util.List;

/**
 * @author fhyPayaso
 * @since 2018/5/10 on 上午12:12
 * fhyPayaso@qq.com
 */
public interface MyTaskListContract {



    interface Presenter extends IBaseContract.IBasePresenter{

        void loadTaskList(String taskType,int page);
    }




    interface View extends IBaseContract.IBaseView {

        /**
         * 加载任务列表成功
         */
        void onLoadTaskListSuccess(List<BaseTaskModel> taskModelList);

        /**
         * 加载任务列表失败
         *
         * @param error
         */
        void onLoadTaskListError(String error);
    }


}
