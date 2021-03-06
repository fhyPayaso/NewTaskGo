package com.hrsoft.taskgo.business.mine.presenter;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.business.mine.contract.OtherUserPageContract;
import com.hrsoft.taskgo.business.mine.model.helper.MyFollowFansHelper;
import com.hrsoft.taskgo.business.mine.model.response.OtherUserPageModel;

/**
 * @author：lszr on 2018/5/10 22:18
 * @email：1085963811@qq.com
 */
public class OtherUserPagePresenter extends BasePresenter<OtherUserPageContract.View> implements
        OtherUserPageContract.Presenter {
    public OtherUserPagePresenter(OtherUserPageContract.View view) {
        super(view);
    }

    @Override
    public void loadOtherUserPage(int userId) {

        IDataCallback.Callback<OtherUserPageModel> callback = new IDataCallback.Callback<OtherUserPageModel>() {
            @Override
            public void onFailedLoaded(String error) {

            }

            @Override
            public void onDataLoaded(OtherUserPageModel otherUserPageModel) {
                mView.onLoadOtherUserPageSuccess(otherUserPageModel);
            }
        };
        MyFollowFansHelper.getInstance().loadOtherUserPage(this, callback, userId);

    }

    @Override
    public void concentrateSB(int userId) {
        IDataCallback.Callback callback = new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {

            }

            @Override
            public void onDataLoaded(Object o) {
                mView.onConcentrateSBSuccess();
            }
        };
        MyFollowFansHelper.getInstance().concentrateSB(this, callback, userId);

    }

    @Override
    public void unConcentrateSB(int userId) {
        IDataCallback.Callback callback = new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {

            }

            @Override
            public void onDataLoaded(Object o) {
                mView.onUnConcentrateSBSuccess();
            }
        };
        MyFollowFansHelper.getInstance().unConcentrateSB(this, callback, userId);

    }
}
