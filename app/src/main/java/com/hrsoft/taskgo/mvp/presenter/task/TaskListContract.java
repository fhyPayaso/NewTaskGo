package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.base.mvp.view.IBaseView;

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
    }


    interface View extends IBaseView {

        /**
         * 加载任务列表成功
         */
        void onLoadTaskListSuccess();

        /**
         * 加载任务列表失败
         *
         * @param error
         */
        void onLoadTaskListError(String error);
    }


}
