package com.hrsoft.taskgo.mvp.presenter.mine;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.contract.mine.RealNameContract;
import com.hrsoft.taskgo.mvp.model.mine.helper.MineInformationHelper;
import com.hrsoft.taskgo.mvp.model.mine.request.RealNameModel;

/**
 * @author：lszr on 2018/5/12 11:03
 * @email：1085963811@qq.com
 */
public class RealNamePresneter extends BasePresenter<RealNameContract.View> implements RealNameContract.Presenter {
    public RealNamePresneter(RealNameContract.View view) {
        super(view);
    }

    @Override
    public void submitRealName(RealNameModel realNameModel) {
        IDataCallback.Callback callback=new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {

            }

            @Override
            public void onDataLoaded(Object o) {
                mView.onsubmitRealNameSuccess();
            }
        };
        MineInformationHelper.getInstence().submitRealName(this,callback,realNameModel);
    }
}
