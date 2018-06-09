package com.hrsoft.taskgo.business.mine.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;

/**
 * @author FanHongyu.
 * @since 18/6/7 20:44.
 * email fanhongyu@hrsoft.net.
 */

public interface MyWalletContract {

    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 提取现金
         */
        void getMoney();

        /**
         * 获取钱包信息
         */
        void getWalletInfo();

    }


    interface View extends IBaseContract.IBaseView {

        /**
         * 提现成功
         */
        void getMoneySuccess();

        /**
         * 提现失败
         *
         * @param error
         */
        void getMonetError(String error);

        /**
         * 获取钱包信息成功
         *
         * @param myMoney
         * @param bankCardNum
         */
        void getWalletInfoSuccess(String myMoney, String bankCardNum);

        /**
         * 获取钱包信息失败
         *
         * @param error
         */
        void getWalletInfoError(String error);

    }

}
