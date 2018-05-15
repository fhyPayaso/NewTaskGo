package com.hrsoft.taskgo.business.task.model.request;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;
import com.hrsoft.taskgo.business.task.model.bean.CardPackageModel;

/**
 * 发布任务请求体
 *
 * @author fhyPayaso
 * @since 2018/5/4 on 上午12:42
 * fhyPayaso@qq.com
 */
public class ReleaseTaskReqModel<T> extends BaseBean {


    /**
     * 详细信息
     */
    private T attributes;

    /**
     * 卡片信息
     */
    @SerializedName("cards")
    private CardPackageModel mCardsModel;


    public ReleaseTaskReqModel(T attributes, CardPackageModel cardsModel) {
        this.attributes = attributes;
        mCardsModel = cardsModel;
    }


    public T getAttributes() {
        return attributes;
    }

    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }

    public CardPackageModel getCardsModel() {
        return mCardsModel;
    }

    public void setCardsModel(CardPackageModel cardsModel) {
        mCardsModel = cardsModel;
    }

    @Override
    public boolean checkValue() {

        return true;
    }
}
