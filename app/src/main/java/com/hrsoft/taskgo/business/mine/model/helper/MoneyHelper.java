package com.hrsoft.taskgo.business.mine.model.helper;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.business.mine.model.request.BindBankCardModel;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

/**
 * @author FanHongyu.
 * @since 18/6/7 20:30.
 * email fanhongyu@hrsoft.net.
 */

public class MoneyHelper extends BaseModel{

    private MoneyHelper() {
    }

    private static class MoneyHelperHolder {
        private static final MoneyHelper INSTANCE = new MoneyHelper();
    }

    public static MoneyHelper getInstance() {
        return MoneyHelperHolder.INSTANCE;
    }

    /**
     * 绑定银行卡
     *
     * @param presenter
     * @param bankCardModel
     * @param callback
     */
    @SuppressWarnings("unchecked")
    public void bindBankCard(IBaseContract.IBasePresenter presenter, BindBankCardModel bankCardModel, final
    IDataCallback
            .Callback callback) {

        addNotifyListener(presenter, callback);

        NetworkFactory
                .getService()
                .bindBankCard(bankCardModel)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        callback.onDataLoaded(response.getMsg());
                    }

                    @Override
                    public void onError(ApiException exception) {

                        callback.onFailedLoaded(exception.getMsg());

                    }
                });
    }

    /**
     * 提取现金
     *
     * @param presenter
     * @param callback
     */
    @SuppressWarnings("unchecked")
    public void withdrawMoney(IBaseContract.IBasePresenter presenter, final IDataCallback.Callback callback) {
        addNotifyListener(presenter, callback);

        NetworkFactory
                .getService()
                .withdrawMoney()
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        callback.onDataLoaded(response.getMsg());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }

}
