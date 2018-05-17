package com.hrsoft.taskgo.business.mine.presenter;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.business.mine.contract.RealNameContract;
import com.hrsoft.taskgo.business.mine.model.helper.MineInformationHelper;
import com.hrsoft.taskgo.business.mine.model.helper.UpLoadHelper;
import com.hrsoft.taskgo.business.mine.model.request.RealNameModel;

/**
 * @author：lszr on 2018/5/12 11:03
 * @email：1085963811@qq.com
 */
public class RealNamePresenter extends BasePresenter<RealNameContract.View> implements RealNameContract.Presenter {
    public RealNamePresenter(RealNameContract.View view) {
        super(view);
    }

    @Override
    public void submitRealName(final RealNameModel realNameModel) {
        IDataCallback.Callback callback = new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {

            }

            @Override
            public void onDataLoaded(Object o) {
                mView.onsubmitRealNameSuccess();
            }
        };
        MineInformationHelper.getInstance().submitRealName(this, callback, realNameModel);
    }

    @Override
    public void uploadToQiNiu(String imgPath) {

        IDataCallback.Callback<String> callback = new IDataCallback.Callback<String>() {
            @Override
            public void onFailedLoaded(String error) {
                mView.onUploadToQiNiuError(error);
            }

            @Override
            public void onDataLoaded(String s) {
                mView.onUploadToQiNiuSuccess(s);
            }
        };
        UpLoadHelper.getInstance().upLoadImgToQiNiu(this, imgPath, callback);
    }
}
