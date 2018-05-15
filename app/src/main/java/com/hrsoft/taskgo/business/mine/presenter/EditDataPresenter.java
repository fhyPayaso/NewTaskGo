package com.hrsoft.taskgo.business.mine.presenter;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.business.mine.contract.EditDataContract;
import com.hrsoft.taskgo.business.mine.model.helper.MineInformationHelper;
import com.hrsoft.taskgo.business.mine.model.helper.UpLoadHelper;
import com.hrsoft.taskgo.business.mine.model.request.UpdateInformationModel;

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
        MineInformationHelper.getInstance().updateInformation(this,callback,updateInformationModel);

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
