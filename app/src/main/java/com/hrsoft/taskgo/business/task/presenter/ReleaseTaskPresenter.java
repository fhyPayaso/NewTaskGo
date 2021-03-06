package com.hrsoft.taskgo.business.task.presenter;

import android.content.Context;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.business.task.contract.ReleaseTaskContract;
import com.hrsoft.taskgo.business.mine.model.helper.MineInformationHelper;
import com.hrsoft.taskgo.business.mine.model.response.MineCardModel;
import com.hrsoft.taskgo.business.task.model.TaskHelper;
import com.hrsoft.taskgo.business.task.model.bean.CardPackageModel;
import com.hrsoft.taskgo.business.task.model.bean.ChooseCardModel;
import com.hrsoft.taskgo.business.task.model.request.ReleaseTaskReqModel;
import com.hrsoft.taskgo.business.task.model.request.WaterAttributesReqModel;
import com.hrsoft.taskgo.business.task.model.response.WxRepModel;
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

public class ReleaseTaskPresenter extends BasePresenter<ReleaseTaskContract.View> implements ReleaseTaskContract.Presenter {

    public ReleaseTaskPresenter(ReleaseTaskContract.View view) {
        super(view);
    }

    @Override
    public void loadUserCardsInfo() {

        IDataCallback.Callback<List<MineCardModel>> callback = new IDataCallback.Callback<List<MineCardModel>>() {
            @Override
            public void onFailedLoaded(String error) {

            }

            @Override
            public void onDataLoaded(List<MineCardModel> mineCardModels) {

                List<ChooseCardModel> modelList = new ArrayList<>();
                for (MineCardModel cardModel : mineCardModels) {

                    ChooseCardModel chooseCardModel = new ChooseCardModel();

                    chooseCardModel.setCardImgUrl(cardModel.getPicture());
                    chooseCardModel.setCardName(cardModel.getUse());
                    chooseCardModel.setCardInfo(cardModel.getContent());
                    chooseCardModel.setCardPrice(String.valueOf(cardModel.getPrice()));
                    chooseCardModel.setHaveNumber(cardModel.getNumber());
                    chooseCardModel.setChooseNumber(0);
                    modelList.add(chooseCardModel);
                }

                mView.loadUserCardsInfoSuccess(modelList);
            }
        };
        MineInformationHelper.getInstance().loadMineCard(this, callback);
    }

    @Override
    public void releaseWaterTask(WaterAttributesReqModel reqModel, CardPackageModel cardsModel) {


        if (cardsModel.getGoodPeople() != 1) {
            mView.onLoadWxOrdersInfoError("水任务需要一张好人卡");
        } else {
            mView.showDialog();
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
