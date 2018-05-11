package com.hrsoft.taskgo.mvp.model.app;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.mvp.model.task.TaskHelper;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

/**
 * app基本信息model层
 *
 * @author FanHongyu.
 * @since 18/5/11 13:18.
 * email fanhongyu@hrsoft.net.
 */

public class AppHelper extends BaseModel {


    private AppHelper() {
    }


    public static class AppHelperHolder {
        private static final AppHelper INSTANCE = new AppHelper();
    }

    public static AppHelper getInstance() {
        return AppHelper.AppHelperHolder.INSTANCE;
    }


    /**
     * 检查app版本
     *
     * @param presenter
     * @param callback
     */
    public void checkAppVersion(IBaseContract.IBasePresenter presenter, final IDataCallback.Callback<AppInfoModel>
            callback) {

        addNotifyListener(presenter, callback);

        NetworkFactory
                .getService()
                .checkAppVersion()
                .compose(BaseObserver.<ApiResponse<AppInfoModel>>setThread())
                .subscribe(new BaseObserver<AppInfoModel>() {
                    @Override
                    public void onSuccess(ApiResponse<AppInfoModel> response) {
                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


}
