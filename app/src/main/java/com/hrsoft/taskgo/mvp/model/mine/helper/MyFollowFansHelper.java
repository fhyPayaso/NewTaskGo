package com.hrsoft.taskgo.mvp.model.mine.helper;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.mvp.model.mine.response.MyFollowFansModel;
import com.hrsoft.taskgo.mvp.model.mine.response.OtherUserPageModel;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

import java.util.List;

/**
 * @author：lszr on 2018/5/9 22:14
 * @email：1085963811@qq.com
 */
public class MyFollowFansHelper extends BaseModel {


    public static class MyFollowFansHelperHolder{

        private static final MyFollowFansHelper INSTANCE = new MyFollowFansHelper();

    }

    public static MyFollowFansHelper getInstence(){
        return MyFollowFansHelper.MyFollowFansHelperHolder.INSTANCE;
    }
    public MyFollowFansHelper() {
    }
    public void loadMyFollowList(IBaseContract.IBasePresenter presenter, final IDataCallback.Callback<List<MyFollowFansModel>> iDataCalllback) {
        addNotifyListener(presenter,iDataCalllback);

        NetworkFactory
                .getService()
                .loadMyFollowList()
                .compose(BaseObserver.<ApiResponse<List<MyFollowFansModel>>>setThread())
                .subscribe(new BaseObserver<List<MyFollowFansModel>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<MyFollowFansModel>> response) {
                        iDataCalllback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        iDataCalllback.onFailedLoaded(exception.getMsg());
                    }
                });
    }
    public void loadMyFansList(IBaseContract.IBasePresenter presenter, final IDataCallback.Callback<List<MyFollowFansModel>> iDataCalllback) {
        addNotifyListener(presenter,iDataCalllback);

        NetworkFactory
                .getService()
                .loadMyFansList()
                .compose(BaseObserver.<ApiResponse<List<MyFollowFansModel>>>setThread())
                .subscribe(new BaseObserver<List<MyFollowFansModel>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<MyFollowFansModel>> response) {
                        iDataCalllback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        iDataCalllback.onFailedLoaded(exception.getMsg());
                    }
                });
    }

    public void loadOtherUserPage(IBaseContract.IBasePresenter presenter, final IDataCallback.Callback<OtherUserPageModel> iDataCallback,int userId){
        addNotifyListener(presenter,iDataCallback);

        NetworkFactory
                .getService()
                .loadOtherUserPage(userId)
                .compose(BaseObserver.<ApiResponse<OtherUserPageModel>>setThread())
                .subscribe(new BaseObserver<OtherUserPageModel>() {
                    @Override
                    public void onSuccess(ApiResponse<OtherUserPageModel> response) {
                        iDataCallback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        iDataCallback.onFailedLoaded(exception.getMsg());
                    }
                });
    }


    @SuppressWarnings("unchecked")
    public void concentrateSB(IBaseContract.IBasePresenter presenter, final IDataCallback.Callback iDataCallback,int userId) {
        addNotifyListener(presenter,iDataCallback);

        NetworkFactory
                .getService()
                .concentrateSB(userId)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {

                    @Override
                    public void onSuccess(ApiResponse response) {
                        iDataCallback.onDataLoaded(response);
                    }

                    @Override
                    public void onError(ApiException exception) {
                        iDataCallback.onFailedLoaded(exception.getMsg());
                    }
                });
    }

    @SuppressWarnings("unchecked")
    public void unConcentrateSB(IBaseContract.IBasePresenter presenter, final IDataCallback.Callback iDataCallback,int userId) {

        addNotifyListener(presenter,iDataCallback);

        NetworkFactory
                .getService()
                .unConcentrateSB(userId)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {

                    @Override
                    public void onSuccess(ApiResponse response) {
                        iDataCallback.onDataLoaded(response);
                    }

                    @Override
                    public void onError(ApiException exception) {
                        iDataCallback.onFailedLoaded(exception.getMsg());
                    }
                });
    }

}
