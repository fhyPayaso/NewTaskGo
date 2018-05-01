package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.base.mvp.view.IBaseView;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskGridModel;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/29 16:57.
 * email fanhongyu@hrsoft.net.
 */

public interface TaskGridContract {


    interface Presenter {

        /**
         * 获取GridView的List
         *
         * @param moduleType
         */
        void getGridList(String moduleType);


        /**
         * 生成大学生自主创业模块
         */
        void getCollegeLists();
    }


    interface View extends IBaseView {


        /**
         * 获取列表成功
         *
         * @param gridList 二级模块列表
         */
        void onLoadGridListSuccess(List<TaskGridModel> gridList);

    }

}