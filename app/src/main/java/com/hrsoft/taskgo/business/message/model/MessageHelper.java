package com.hrsoft.taskgo.business.message.model;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/5/12 11:50.
 * email fanhongyu@hrsoft.net.
 */

public class MessageHelper extends BaseModel {


    private MessageHelper() {
    }

    private static class MessageHelperHolder {
        private static final MessageHelper INSTANCE = new MessageHelper();
    }

    public static MessageHelper getInstance() {
        return MessageHelperHolder.INSTANCE;
    }


    /**
     * 加载消息列表
     *
     * @param presenter
     * @param callback
     */
    public void loadMessageList(IBaseContract.IBasePresenter presenter, final IDataCallback
            .Callback<List<MessageModel>> callback) {

        addNotifyListener(presenter, callback);

        NetworkFactory
                .getService()
                .loadMessageList()
                .compose(BaseObserver.<ApiResponse<List<MessageModel>>>setThread())
                .subscribe(new BaseObserver<List<MessageModel>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<MessageModel>> response) {
                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    /**
     * 标记已经读取
     *
     * @param presenter
     * @param reqModel
     * @param callback
     */
    @SuppressWarnings("unchecked")
    public void hasReadMessage(IBaseContract.IBasePresenter presenter, MsgReadDeleteReqModel reqModel, final
    IDataCallback.Callback
            callback) {
        addNotifyListener(presenter, callback);


        NetworkFactory
                .getService()
                .hasReadMessage(reqModel)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    /**
     * 删除消息
     *
     * @param presenter
     * @param reqModel
     * @param callback
     */
    @SuppressWarnings("unchecked")
    public void deleteMessage(IBaseContract.IBasePresenter presenter, final MsgReadDeleteReqModel reqModel, final IDataCallback.Callback callback) {

        addNotifyListener(presenter, callback);


        NetworkFactory
                .getService()
                .deleteMessage(reqModel)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {

                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


}
