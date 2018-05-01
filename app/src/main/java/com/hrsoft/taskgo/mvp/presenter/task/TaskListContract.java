package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.base.mvp.view.IBaseView;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/29 20:48.
 * email fanhongyu@hrsoft.net.
 */

public interface TaskListContract {


    interface Presenter {

        /**
         * 加载任务列表
         *
         * @param taskType 任务类型
         */
        void loadTaskList(String taskType);


        /**
         * 接受任务
         *
         * @param model
         */
        void acceptTask(BaseTaskModel model);
    }


    interface View extends IBaseView {

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
        void onAcceptTaskSuccess(BaseTaskModel model);


        /**
         * 删除任务失败
         *
         * @param error
         */
        void onAcceptTaskError(String error);
    }
}
