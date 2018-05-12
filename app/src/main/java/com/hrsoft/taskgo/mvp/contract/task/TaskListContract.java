package com.hrsoft.taskgo.mvp.contract.task;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/29 20:48.
 * email fanhongyu@hrsoft.net.
 */

public interface TaskListContract {


    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 加载任务列表
         *
         * @param taskType 任务类型
         */
        void loadTaskList(String taskType,int page);


        /**
         * 接受任务
         *
         * @param model
         */
        void acceptTask(BaseTaskModel model, int position);


        /**
         * 接受任务
         */
        void acceptAllTask(List<BaseTaskModel> modelList);
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


        /**
         * 接受任务成功
         */
        void onAcceptTaskSuccess(int position);


        /**
         * 接受全部任务成功
         */
        void onAcceptAllTaskSuccess();


        /**
         * 删除任务失败
         *
         * @param error
         */
        void onAcceptTaskError(String error);
    }
}
