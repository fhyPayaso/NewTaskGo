package com.hrsoft.taskgo.mvp.presenter.mine;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.mvp.contract.mine.EditDataContract;
import com.hrsoft.taskgo.mvp.model.mine.helper.MineInformationHelper;
import com.hrsoft.taskgo.mvp.model.mine.request.UpdateInformationModel;

/**
 * @author：lszr on 2018/5/11 22:07
 * @email：1085963811@qq.com
 */
public class EditDataPresenter extends BasePresenter<EditDataContract.View> implements EditDataContract.Presenter{
    public EditDataPresenter(EditDataContract.View view) {
        super(view);
    }


    @Override
    public void updateInformation(UpdateInformationModel updateInformationModel) {
        IDataCallback.Callback callback=new IDataCallback.Callback() {
            @Override
            public void onDataLoaded(Object o) {
                mView.onUpdateInformationSuccess();

            }

            @Override
            public void onFailedLoaded(String error) {

            }


        };
        MineInformationHelper.getInstence().updateInformation(this,callback,updateInformationModel);

    }
}
