package com.hrsoft.taskgo.business.task.model.request;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/5/9 21:27.
 * email fanhongyu@hrsoft.net.
 */

public class AcceptTaskReqModel {


    List<Integer> taskArray;


    public AcceptTaskReqModel(List<Integer> taskArray) {
        this.taskArray = taskArray;
    }

    public List<Integer> getTaskArray() {
        return taskArray;
    }

    public void setTaskArray(List<Integer> taskArray) {
        this.taskArray = taskArray;
    }
}
