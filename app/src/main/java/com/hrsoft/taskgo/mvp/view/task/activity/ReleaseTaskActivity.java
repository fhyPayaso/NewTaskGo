package com.hrsoft.taskgo.mvp.view.task.activity;

import android.os.Bundle;
import android.widget.Button;

import com.hrsoft.taskgo.App;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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

    private IWXAPI mIWXAPIpi;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_task;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mIWXAPIpi = WXAPIFactory.createWXAPI(this, APP_ID, true);
        mIWXAPIpi.registerApp(APP_ID);
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

        if (mIWXAPIpi != null) {

            PayReq payReq = new PayReq();


            //应用id
            payReq.appId = APP_ID;
            //商户号
            payReq.partnerId = "1900000109";
            //预支付交易会话ID
            payReq.prepayId = "1101000000140415649af9fc314aa427";
            //扩展字段
            payReq.packageValue = "Sign=WXPay";
            //随机字符串
            payReq.nonceStr = "1101000000140429eb40476f8896f4c9";
            //时间戳
            payReq.timeStamp = "1398746574";
            //签名
            payReq.sign = "3b4d4ef42bb681b700439226e5d3e80a";

            mIWXAPIpi.sendReq(payReq);
        }
    }
}
