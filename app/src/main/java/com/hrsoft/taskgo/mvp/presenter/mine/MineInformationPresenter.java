package com.hrsoft.taskgo.mvp.presenter.mine;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.contract.mine.MineInformationContract;
import com.hrsoft.taskgo.mvp.model.mine.helper.MineInformationHelper;
import com.hrsoft.taskgo.mvp.model.mine.response.MineInformationModel;

/**
 * @author：lszr on 2018/5/7 22:17
 * @email：1085963811@qq.com
 */
public class MineInformationPresenter extends BasePresenter<MineInformationContract.View>implements MineInformationContract.Presenter {
    public MineInformationPresenter(MineInformationContract.View view) {
        super(view);
    }


    @Override
    public void loadMineInformation() {
        IDataCallback.Callback iDataCallback=new IDataCallback.Callback<MineInformationModel>(){


            @Override
            public void onFailedLoaded(String error) {

            }

            @Override
            public void onDataLoaded(MineInformationModel mineInformationModel) {
                mView.onLoadMineInformationSuccess(mineInformationModel);
            }
        };
        MineInformationHelper.getInstence().loadMineInformation(this,iDataCallback);
    }
}
