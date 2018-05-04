package com.hrsoft.taskgo.mvp.model.task.request;

import com.google.gson.annotations.SerializedName;
import com.hrsoft.taskgo.base.mvp.model.BaseBean;
import com.hrsoft.taskgo.mvp.model.task.bean.CardsModel;

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
    private CardsModel mCardsModel;


    public ReleaseTaskReqModel(T attributes, CardsModel cardsModel) {
        this.attributes = attributes;
        mCardsModel = cardsModel;
    }


    public T getAttributes() {
        return attributes;
    }

    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }

    public CardsModel getCardsModel() {
        return mCardsModel;
    }

    public void setCardsModel(CardsModel cardsModel) {
        mCardsModel = cardsModel;
    }

    @Override
    protected void checkValue() {

    }
}
