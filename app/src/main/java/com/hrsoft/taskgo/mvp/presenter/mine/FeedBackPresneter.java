package com.hrsoft.taskgo.mvp.presenter.mine;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.contract.mine.FeedbackContract;
import com.hrsoft.taskgo.mvp.model.mine.FeedBackModel;
import com.hrsoft.taskgo.mvp.model.mine.helper.MineInformationHelper;

/**
 * @author：lszr on 2018/5/12 17:22
 * @email：1085963811@qq.com
 */
public class FeedBackPresneter extends BasePresenter<FeedbackContract.View> implements FeedbackContract.Presenter {
    public FeedBackPresneter(FeedbackContract.View view) {
        super(view);
    }

    @Override
    public void submitAdvice(String contents) {
        IDataCallback.Callback<FeedBackModel> callback=new IDataCallback.Callback<FeedBackModel>() {
            @Override
            public void onFailedLoaded(String error) {

            }

            @Override
            public void onDataLoaded(FeedBackModel feedBackModel) {
                mView.onsubmitAdviceSuccess();
            }
        };
        MineInformationHelper.getInstence().submitAdvice(this,callback,new FeedBackModel(contents));
    }
}
