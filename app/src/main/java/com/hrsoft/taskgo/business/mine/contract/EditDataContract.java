package com.hrsoft.taskgo.business.mine.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.mine.model.request.UpdateInformationModel;

/**
 * @author：lszr on 2018/5/11 22:05
 * @email：1085963811@qq.com
 */
public interface EditDataContract {
    interface Presenter extends IBaseContract.IBasePresenter{

        void updateInformation(UpdateInformationModel updateInformationModel);


        /**
         * 上传到七牛云
         *
         * @param imgPath
         */
        void uploadToQiNiu(String imgPath);

    }


    interface View extends IBaseContract.IBaseView{



        void onUpdateInformationSuccess();


        /**
         * 上传图片成功
         *
         * @param imgUrl
         */
        void onUploadToQiNiuSuccess(String imgUrl);


        /**
         * 上传图片失败
         *
         * @param error
         */
        void onUploadToQiNiuError(String error);

    }
}
