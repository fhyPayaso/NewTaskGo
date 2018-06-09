package com.hrsoft.taskgo.business.mine.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.mine.model.request.BindBankCardModel;
import com.hrsoft.taskgo.business.mine.model.request.UpdateInformationModel;

/**
 * @author：lszr on 2018/6/9 19:06
 * @email：1085963811@qq.com
 */
public interface BindBankCardContract {

    interface Presenter extends IBaseContract.IBasePresenter{

        void bindBankCard(BindBankCardModel bindBankCardModel);




    }


    interface View extends IBaseContract.IBaseView{



        void bindBankCardSuccess();




    }
}
