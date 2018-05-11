package com.hrsoft.taskgo.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.WeakHandler;
import com.hrsoft.taskgo.mvp.model.task.TaskHelper;
import com.hrsoft.taskgo.mvp.view.task.activity.ReleaseTaskActivity;
import com.hrsoft.taskgo.mvp.view.task.activity.TaskListActivity;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * @author fhyPayaso
 * @since 2018/4/30 on 下午3:07
 * fhyPayaso@qq.com
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @BindView(R.id.txt_wx_pay_info)
    TextView txtWxPayInfo;
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_pay_response);
        ButterKnife.bind(this);


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
                //checkPayStatus();
                txtWxPayInfo.setText("支付成功");
            } else {
                txtWxPayInfo.setText("支付失败");
            }
        }
    }


    /**
     * 检查任务实际支付状态
     */
    private void checkPayStatus() {
        if (ReleaseTaskActivity.sTaskId != -1) {
            NetworkFactory
                    .getService()
                    .checkWaterTaskPayStatus(ReleaseTaskActivity.sTaskId)
                    .compose(BaseObserver.<ApiResponse<String>>setThread())
                    .subscribe(new BaseObserver<String>() {
                        @Override
                        public void onSuccess(ApiResponse<String> response) {
                            if (response.getData().equals("0")) {
                                txtWxPayInfo.setText("支付成功，可在我的任务列表中查看信息");
                            } else {
                                txtWxPayInfo.setText("支付失败");
                            }
                        }

                        @Override
                        public void onError(ApiException exception) {
                            txtWxPayInfo.setText("网络错误");
                        }
                    });
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        TaskListActivity.startActivity(this, ReleaseTaskActivity.mTaskType);
    }
}
