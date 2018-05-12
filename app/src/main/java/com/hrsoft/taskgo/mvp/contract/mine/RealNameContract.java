package com.hrsoft.taskgo.mvp.contract.mine;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.mine.request.RealNameModel;
import com.hrsoft.taskgo.mvp.model.mine.request.UpdateInformationModel;

/**
 * @author：lszr on 2018/5/12 10:33
 * @email：1085963811@qq.com
 */
public interface RealNameContract {

    interface Presenter extends IBaseContract.IBasePresenter{

        void submitRealName(RealNameModel realNameModel);


    }


    interface View extends IBaseContract.IBaseView{



        void onsubmitRealNameSuccess();



    }
}
