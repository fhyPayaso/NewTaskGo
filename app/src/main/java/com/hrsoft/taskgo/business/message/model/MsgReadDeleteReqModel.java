package com.hrsoft.taskgo.business.message.model;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/5/12 14:51.
 * email fanhongyu@hrsoft.net.
 */

public class MsgReadDeleteReqModel extends BaseBean {


    @SerializedName("taskarray")
    private List<Integer> msgIdList;

    public MsgReadDeleteReqModel(List<Integer> msgIdList) {
        this.msgIdList = msgIdList;
    }

    public List<Integer> getMsgIdList() {
        return msgIdList;
    }

    public void setMsgIdList(List<Integer> msgIdList) {
        this.msgIdList = msgIdList;
    }

    @Override
    public boolean checkValue() {
        return false;
    }
}
