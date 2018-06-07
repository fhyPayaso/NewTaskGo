package com.hrsoft.taskgo.business.task.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.task.model.bean.BaseTaskModel;

import java.util.List;

/**
 * @author fhyPayaso
 * @since 2018/5/10 on 上午12:12
 * fhyPayaso@qq.com
 */
public interface MyTaskListContract {


    interface Presenter extends IBaseContract.IBasePresenter {


        /**
         * 加载任务列表
         *
         * @param taskType
         * @param page
         */
        void loadTaskList(String taskType, int page);


        /**
         * 获取adapter类型
         *
         * @param taskStatusType 我的任务状态类型
         */
        void getAdapterType(String taskStatusType);


        /**
         * 完成任务
         *
         * @param taskId
         */
        void finishTask(int taskId, int position);


        /**
         * 退回卡片
         *
         * @param taskId
         * @param position
         */
        void returnCard(int taskId, int position);

        /**
         * 退款
         *
         * @param taskId
         * @param position
         */
        void returnMoney(int taskId, int position);
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
         * 初始化RecyclerView
         *
         * @param btnType
         */
        void initRecyclerView(int btnType);


        /**
         * 完成任务成功
         *
         * @param position
         */
        void finishTaskSuccess(int position);


        /**
         * 完成任务失败
         *
         * @param error
         */
        void finishTaskError(String error);


        /**
         * 退回卡片成功
         *
         * @param position
         */
        void returnCardSuccess(int position);


        /**
         * 退回卡片失败
         *
         * @param error
         */
        void returnCardError(String error);

        /**
         * 退款成功
         *
         * @param position
         */
        void returnMoneySuccess(int position);

        /**
         * 退款失败
         *
         * @param error
         */
        void returnMoneyError(String error);

    }


}
