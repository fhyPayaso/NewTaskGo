package com.hrsoft.taskgo.mvp.contract.mine;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.mine.request.UpdateInformationModel;

/**
 * @author：lszr on 2018/5/11 22:05
 * @email：1085963811@qq.com
 */
public interface EditDataContract {
    interface Presenter extends IBaseContract.IBasePresenter{

        void updateInformation(UpdateInformationModel updateInformationModel);


    }


    interface View extends IBaseContract.IBaseView{



        void onUpdateInformationSuccess();



    }
}
