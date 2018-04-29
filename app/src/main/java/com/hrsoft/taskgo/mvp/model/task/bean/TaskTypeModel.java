package com.hrsoft.taskgo.mvp.model.task.bean;

import android.support.annotation.DrawableRes;

/**
 * 三级任务模块model
 *
 * @author FanHongyu.
 * @since 18/4/27 14:18.
 * email fanhongyu@hrsoft.net.
 */

public class TaskTypeModel {


    @DrawableRes
    private int imgResId;
    private String taskTypeName;
    private String taskTypeFlag;


    public TaskTypeModel(int imgResId, String taskTypeName, String taskTypeFlag) {
        this.imgResId = imgResId;
        this.taskTypeName = taskTypeName;
        this.taskTypeFlag = taskTypeFlag;
    }


    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getTaskTypeFlag() {
        return taskTypeFlag;
    }

    public void setTaskTypeFlag(String taskTypeFlag) {
        this.taskTypeFlag = taskTypeFlag;
    }
}
