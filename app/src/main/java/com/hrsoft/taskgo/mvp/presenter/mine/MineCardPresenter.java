package com.hrsoft.taskgo.mvp.presenter.mine;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.contract.mine.MineCardContract;
import com.hrsoft.taskgo.mvp.model.mine.helper.MineInformationHelper;
import com.hrsoft.taskgo.mvp.model.mine.response.MineCardModel;

import java.util.List;

/**
 * @author：lszr on 2018/5/12 20:45
 * @email：1085963811@qq.com
 */
public class MineCardPresenter extends BasePresenter<MineCardContract.View> implements MineCardContract.Presenter {

    public MineCardPresenter(MineCardContract.View view) {
        super(view);
    }

    @Override
    public void loadCardList() {
        IDataCallback.Callback<List<MineCardModel>> callback=new IDataCallback.Callback<List<MineCardModel>>() {
            @Override
            public void onDataLoaded(List<MineCardModel> mineCardModels) {
                mView.onloadCardListSuccess(mineCardModels);
            }

            @Override
            public void onFailedLoaded(String error) {

            }


        };
        MineInformationHelper.getInstence().loadMineCard(this,callback);
    }
}
