package com.hrsoft.taskgo.business.mine.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.mine.model.response.MineInformationModel;

/**
 * @author：lszr on 2018/5/7 21:36
 * @email：1085963811@qq.com
 */
public interface MineInformationContract {
    interface Presenter extends IBaseContract.IBasePresenter{

        void loadMineInformation();
    }


    interface View extends IBaseContract.IBaseView{


        /**
         * 获取个人信息成功
         */
        void onLoadMineInformationSuccess(MineInformationModel mineInformationModel);

    }

}
