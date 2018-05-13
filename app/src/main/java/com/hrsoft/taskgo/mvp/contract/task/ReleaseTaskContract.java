package com.hrsoft.taskgo.mvp.contract.task;

import android.content.Context;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.task.bean.CardPackageModel;
import com.hrsoft.taskgo.mvp.model.task.bean.ChooseCardModel;
import com.hrsoft.taskgo.mvp.model.task.request.WaterAttributesReqModel;
import com.hrsoft.taskgo.mvp.model.task.response.WxRepModel;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/5/5 16:24.
 * email fanhongyu@hrsoft.net.
 */

public interface ReleaseTaskContract {


    interface View extends IBaseContract.IBaseView {

        /**
         * 加载卡包信息成功
         *
         * @param cardModelList 卡片信息
         */
        void loadUserCardsInfoSuccess(List<ChooseCardModel> cardModelList);


        /**
         * 返回订单信息成功
         *
         * @param repModel
         */
        void onLoadWxOrdersInfoSuccess(WxRepModel repModel);


        /**
         * 加载订单信息失败
         *
         * @param error
         */
        void onLoadWxOrdersInfoError(String error);


        void showDialog();

    }


    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 加载用户卡片信息
         */
        void loadUserCardsInfo();


        /**
         * 发布水任务
         *
         * @param reqModel   水任务请求体
         * @param cardsModel 卡片信息
         */
        void releaseWaterTask(WaterAttributesReqModel reqModel, CardPackageModel cardsModel);


        /**
         * 打开微信支付页面
         *
         * @param repModel
         */
        void openWxPayPage(Context context, WxRepModel repModel);





    }
}
