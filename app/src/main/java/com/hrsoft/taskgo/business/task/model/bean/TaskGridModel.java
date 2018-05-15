package com.hrsoft.taskgo.business.task.model.bean;

import java.util.List;

/**
 * 二级任务模块model
 *
 * @author FanHongyu.
 * @since 18/4/29 17:29.
 * email fanhongyu@hrsoft.net.
 */

public class TaskGridModel {


    /**
     * 二级任务模块title
     */
    private String title;
    /**
     * 三级任务模块list
     */
    private List<TaskTypeModel> taskTypeList;


    public TaskGridModel(String title, List<TaskTypeModel> taskTypeList) {
        this.title = title;
        this.taskTypeList = taskTypeList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TaskTypeModel> getTaskTypeList() {
        return taskTypeList;
    }

    public void setTaskTypeList(List<TaskTypeModel> taskTypeList) {
        this.taskTypeList = taskTypeList;
    }
}
