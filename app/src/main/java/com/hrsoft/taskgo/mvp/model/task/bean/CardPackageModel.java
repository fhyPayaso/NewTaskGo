package com.hrsoft.taskgo.mvp.model.task.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author fhyPayaso
 * @since 2018/5/4 on 上午12:53
 * fhyPayaso@qq.com
 */
public class CardPackageModel {

    @SerializedName("1")
    private int goodPeople;

    public CardPackageModel() {

    }

    public int getGoodPeople() {
        return goodPeople;
    }

    public void setGoodPeople(int goodPeople) {
        this.goodPeople = goodPeople;
    }
}
