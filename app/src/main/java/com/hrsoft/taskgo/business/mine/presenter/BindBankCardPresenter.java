package com.hrsoft.taskgo.business.mine.presenter;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.business.mine.contract.BindBankCardContract;
import com.hrsoft.taskgo.business.mine.model.helper.MoneyHelper;
import com.hrsoft.taskgo.business.mine.model.request.BindBankCardModel;

/**
 * @author：lszr on 2018/6/9 19:08
 * @email：1085963811@qq.com
 */
public class BindBankCardPresenter extends BasePresenter<BindBankCardContract.View> implements BindBankCardContract
        .Presenter {
    public BindBankCardPresenter(BindBankCardContract.View view) {
        super(view);
    }

    @Override
    public void bindBankCard(BindBankCardModel bindBankCardModel) {
        IDataCallback.Callback callback = new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {
                if (mView != null) {
                    mView.bindBankCardError(error);
                }
            }

            @Override
            public void onDataLoaded(Object o) {
                if (mView != null) {
                    mView.bindBankCardSuccess();
                }
            }
        };
        MoneyHelper.getInstance().bindBankCard(this, bindBankCardModel, callback);
    }
}
