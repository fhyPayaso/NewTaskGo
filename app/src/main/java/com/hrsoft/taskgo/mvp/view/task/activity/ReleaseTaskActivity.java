package com.hrsoft.taskgo.mvp.view.task.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;
import com.hrsoft.taskgo.mvp.model.task.request.WaterAttributesReqModel;
import com.hrsoft.taskgo.mvp.model.task.bean.CardsModel;
import com.hrsoft.taskgo.mvp.model.task.request.ReleaseTaskReqModel;
import com.hrsoft.taskgo.mvp.model.task.response.WxRepModel;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;
import com.hrsoft.taskgo.utils.ToastUtil;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fhyPayaso
 * @since 2018/4/29 on 下午11:21
 * fhyPayaso@qq.com
 */
public class ReleaseTaskActivity extends BaseToolBarActivity {


    @BindView(R.id.btn_pay)
    Button mBtnPay;


    public static final String APP_ID = "wx73e42ddc37ff92ff";

    //private IWXAPI mIWXAPIpi;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_task;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

//        mIWXAPIpi = WXAPIFactory.createWXAPI(this, APP_ID, true);
//        mIWXAPIpi.registerApp(APP_ID);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_pay)
    public void onViewClicked() {

        ToastUtil.showToast("开始下单");


        CardsModel cardsModel = new CardsModel(1);
        WaterAttributesReqModel waterAttributesReqModel = new WaterAttributesReqModel("6", "6088", "0");
        ReleaseTaskReqModel model = new ReleaseTaskReqModel(waterAttributesReqModel, cardsModel);

        NetworkFactory
                .getService()
                .releaseWaterTask(model)
                .compose(BaseObserver.<ApiResponse<WxRepModel>>setThread())
                .subscribe(new BaseObserver<WxRepModel>() {
                    @Override
                    public void onSuccess(ApiResponse<WxRepModel> response) {

                        Log.i(TAG, "onSuccess: " + response.getData().getAppid());
                        wxPay(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        ToastUtil.showToast(exception.getMsg());
                    }
                });


    }


    private void wxPay(WxRepModel model) {


//        if (mIWXAPIpi != null) {
//
//            PayReq payReq = new PayReq();
//
//
//            //应用id
//            payReq.appId = model.getAppid();
//            //商户号
//            payReq.partnerId = model.getPartnerid();
//            //预支付交易会话ID
//            payReq.prepayId = model.getPrepayid();
//            //扩展字段
//            payReq.packageValue = model.getPackageName();
//            //随机字符串
//            payReq.nonceStr = model.getNoncestr();
//            //时间戳
//            payReq.timeStamp = model.getTimestamp();
//            //签名
//            payReq.sign = model.getSign();
//
//            mIWXAPIpi.sendReq(payReq);
    }


}
