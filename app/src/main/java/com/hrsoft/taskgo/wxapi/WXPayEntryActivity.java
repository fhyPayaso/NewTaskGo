package com.hrsoft.taskgo.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hrsoft.taskgo.utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static android.content.ContentValues.TAG;

/**
 * @author fhyPayaso
 * @since 2018/4/30 on 下午3:07
 * fhyPayaso@qq.com
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        api = WXAPIFactory.createWXAPI(this, "", false);
        api.handleIntent(getIntent(), this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

        Log.i(TAG, "onResp: 微信支付回调>>resp>>" + baseResp.errCode);

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {

                ToastUtil.showToast("支付成功");

            } else {

                ToastUtil.showToast("支付失败");

            }
        }

        finish();
    }
}
