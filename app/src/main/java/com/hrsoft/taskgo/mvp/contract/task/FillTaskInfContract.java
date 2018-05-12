package com.hrsoft.taskgo.mvp.contract.task;

import com.hrsoft.taskgo.base.mvp.IBaseContract;

/**
 * @author FanHongyu.
 * @since 18/5/4 20:51.
 * email fanhongyu@hrsoft.net.
 */

public interface FillTaskInfContract {


    interface View extends IBaseContract.IBaseView {


        /**
         * 数据校验成功
         */
        void onCheckDataTrue();


        /**
         * 数据校验失败
         *
         * @param error
         */
        void onCheckDataError(String error);


    }


    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 检查送水任务信息
         *
         * @param address       宿舍地址
         */
        void onCheckWaterTaskInfo(String address);

    }
}
