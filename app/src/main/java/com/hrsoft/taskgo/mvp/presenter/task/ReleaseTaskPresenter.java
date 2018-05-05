package com.hrsoft.taskgo.mvp.presenter.task;

import android.content.Context;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.contract.ReleaseTaskContract;
import com.hrsoft.taskgo.mvp.model.task.TaskHelper;
import com.hrsoft.taskgo.mvp.model.task.bean.CardPackageModel;
import com.hrsoft.taskgo.mvp.model.task.bean.ChooseCardModel;
import com.hrsoft.taskgo.mvp.model.task.request.ReleaseTaskReqModel;
import com.hrsoft.taskgo.mvp.model.task.request.WaterAttributesReqModel;
import com.hrsoft.taskgo.mvp.model.task.response.WxRepModel;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import static com.hrsoft.taskgo.common.Config.APP_ID;

/**
 * @author FanHongyu.
 * @since 18/5/5 16:28.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseTaskPresenter extends BasePresenter<ReleaseTaskContract.View> implements ReleaseTaskContract
        .Presenter {

    public ReleaseTaskPresenter(ReleaseTaskContract.View view) {
        super(view);
    }

    @Override
    public void loadUserCardsInfo() {

        List<ChooseCardModel> modelList = new ArrayList<>();
        ChooseCardModel chooseCardModel = new ChooseCardModel();
        chooseCardModel.setCardImgUrl("https://timgsa.baidu" +
                ".com/timg?image&quality=80&size=b9999_10000&sec=1524211166428&di" +
                "=66aac07e1d134dab3b77d77c76a5632a&imgtype=0&src=http%3A%2F%2Fimg4.duitang" +
                ".com%2Fuploads%2Fitem%2F201501%2F15%2F20150115234911_S4xLM.jpeg");
        chooseCardModel.setHaveNumber(10);
        chooseCardModel.setChooseNumber(0);
        modelList.add(chooseCardModel);
        mView.loadUserCardsInfoSuccess(modelList);
    }

    @Override
    public void releaseWaterTask(WaterAttributesReqModel reqModel, CardPackageModel cardsModel) {


        if (cardsModel.getGoodPeople() != 1) {
            mView.onLoadWxOrdersInfoError("水任务需要一张好人卡");
        } else {

            IDataCallback.Callback<WxRepModel> callback = new IDataCallback.Callback<WxRepModel>() {
                @Override
                public void onFailedLoaded(String error) {
                    mView.onLoadWxOrdersInfoError(error);
                }

                @Override
                public void onDataLoaded(WxRepModel repModel) {
                    mView.onLoadWxOrdersInfoSuccess(repModel);
                }
            };
            ReleaseTaskReqModel taskReqModel = new ReleaseTaskReqModel<WaterAttributesReqModel>(reqModel, cardsModel);
            TaskHelper.getInstance().releaseWaterTask(this, taskReqModel, callback);
        }
    }

    @Override
    public void openWxPayPage(Context context, WxRepModel repModel) {


        //发起请求之首先进行注册
        IWXAPI wxApi = WXAPIFactory.createWXAPI(context, APP_ID, true);
        wxApi.registerApp(APP_ID);
        PayReq payReq = new PayReq();
        //应用id
        payReq.appId = repModel.getAppid();
        //商户号
        payReq.partnerId = repModel.getPartnerid();
        //预支付交易会话ID
        payReq.prepayId = repModel.getPrepayid();
        //扩展字段
        payReq.packageValue = repModel.getPackageName();
        //随机字符串
        payReq.nonceStr = repModel.getNoncestr();
        //时间戳
        payReq.timeStamp = repModel.getTimestamp();
        //签名
        payReq.sign = repModel.getSign();

        wxApi.sendReq(payReq);
    }
}
