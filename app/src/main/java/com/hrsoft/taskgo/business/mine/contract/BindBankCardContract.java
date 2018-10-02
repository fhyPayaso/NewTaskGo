package com.hrsoft.taskgo.business.mine.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.mine.model.request.BindBankCardModel;
import com.hrsoft.taskgo.business.mine.model.request.UpdateInformationModel;

/**
 * @author：lszr on 2018/6/9 19:06
 * @email：1085963811@qq.com
 */
public interface BindBankCardContract {

    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 绑定银行卡
         *
         * @param bindBankCardModel
         */
        void bindBankCard(BindBankCardModel bindBankCardModel);
    }


    interface View extends IBaseContract.IBaseView {


        /**
         * 绑定银行卡成功
         */
        void bindBankCardSuccess();


        /**
         * 绑定银行卡失败
         *
         * @param error
         */
        void bindBankCardError(String error);
    }
}
