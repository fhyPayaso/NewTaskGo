package com.hrsoft.taskgo.mvp.presenter.mine;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.contract.mine.MyFollowContract;
import com.hrsoft.taskgo.mvp.model.mine.helper.MyFollowFansHelper;
import com.hrsoft.taskgo.mvp.model.mine.response.MyFollowFansModel;

import java.util.List;

/**
 * @author：lszr on 2018/5/9 18:13
 * @email：1085963811@qq.com
 */
public class MyFollowListPresenter extends BasePresenter<MyFollowContract.View> implements MyFollowContract.Presenter {
    public MyFollowListPresenter(MyFollowContract.View view) {
        super(view);
    }

    @Override
    public void loadMyFollowList() {
        IDataCallback.Callback callback=new IDataCallback.Callback<List<MyFollowFansModel>>() {
            @Override
            public void onDataLoaded(List<MyFollowFansModel> myFollowModels) {
                mView.onloadMyFollowFansListSuccess(myFollowModels);
            }

            @Override
            public void onFailedLoaded(String error) {


            }


        };
        MyFollowFansHelper.getInstence().loadMyFollowList(this,callback);

    }

    @Override
    public void loadMyFansList() {
        IDataCallback.Callback callback=new IDataCallback.Callback<List<MyFollowFansModel>>() {
            @Override
            public void onDataLoaded(List<MyFollowFansModel> myFollowModels) {
                mView.onloadMyFollowFansListSuccess(myFollowModels);
            }

            @Override
            public void onFailedLoaded(String error) {


            }


        };
        MyFollowFansHelper.getInstence().loadMyFansList(this,callback);
    }
}
