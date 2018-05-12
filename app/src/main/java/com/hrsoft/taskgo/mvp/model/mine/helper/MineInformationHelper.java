package com.hrsoft.taskgo.mvp.model.mine.helper;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.mvp.contract.mine.MineInformationContract;
import com.hrsoft.taskgo.mvp.model.mine.FeedBackModel;
import com.hrsoft.taskgo.mvp.model.mine.request.RealNameModel;
import com.hrsoft.taskgo.mvp.model.mine.request.UpdateInformationModel;
import com.hrsoft.taskgo.mvp.model.mine.response.MineCardModel;
import com.hrsoft.taskgo.mvp.model.mine.response.MineInformationModel;
import com.hrsoft.taskgo.mvp.model.task.TaskHelper;
import com.hrsoft.taskgo.mvp.presenter.mine.RealNamePresneter;
import com.hrsoft.taskgo.network.BaseObserver;
import com.hrsoft.taskgo.network.NetworkFactory;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;

import java.util.List;

/**
 * @author：lszr on 2018/5/7 21:17
 * @email：1085963811@qq.com
 */
public class MineInformationHelper extends BaseModel {
    private MineInformationHelper() {

    }

    public static class MineInformationHelperHolder{

        private static final MineInformationHelper INSTANCE = new MineInformationHelper();

    }

    public static MineInformationHelper getInstence(){
        return MineInformationHelperHolder.INSTANCE;
    }

    public void loadMineInformation(IBaseContract.IBasePresenter presenter, final IDataCallback.Callback<MineInformationModel> iDataCallback){
        addNotifyListener(presenter,iDataCallback);

        NetworkFactory
                .getService()
                .loadMineInformation()
                .compose(BaseObserver.<ApiResponse<MineInformationModel>>setThread())
                .subscribe(new BaseObserver<MineInformationModel>() {
                    @Override
                    public void onSuccess(ApiResponse<MineInformationModel> response) {
                        iDataCallback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        iDataCallback.onFailedLoaded(exception.getMsg());
                    }
                });

    }
    @SuppressWarnings("unchecked")
    public void updateInformation(IBaseContract.IBasePresenter presenter, final IDataCallback.Callback iDataCallback,UpdateInformationModel updateInformationModel) {
        addNotifyListener(presenter,iDataCallback);

        NetworkFactory
                .getService()
                .updateInformation(updateInformationModel)
                .compose(BaseObserver.<ApiResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        iDataCallback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        iDataCallback.onFailedLoaded(exception.getMsg());
                    }
                });


    }
    @SuppressWarnings("unchecked")
    public void submitRealName(IBaseContract.IBasePresenter presenter,final IDataCallback.Callback callback, RealNameModel realNameModel) {
        addNotifyListener(presenter,callback);

        NetworkFactory
                .getService()
                .submitRealName(realNameModel)
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
    @SuppressWarnings("unchecked")
    public void submitAdvice(IBaseContract.IBasePresenter presenter,final IDataCallback.Callback callback, FeedBackModel feedBackModel){
        addNotifyListener(presenter,callback);

        NetworkFactory
                .getService()
                .submitAdvice(feedBackModel)
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
    public void loadMineCard(IBaseContract.IBasePresenter presenter,final IDataCallback.Callback<List<MineCardModel>> callback) {
        addNotifyListener(presenter,callback);

        NetworkFactory
                .getService()
                .loadMineCard()
                .compose(BaseObserver.<ApiResponse<List<MineCardModel>>>setThread())
                .subscribe(new BaseObserver<List<MineCardModel>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<MineCardModel>> response) {
                        callback.onDataLoaded(response.getData());
                    }

                    @Override
                    public void onError(ApiException exception) {
                        callback.onFailedLoaded(exception.getMsg());
                    }
                });
    }
}
