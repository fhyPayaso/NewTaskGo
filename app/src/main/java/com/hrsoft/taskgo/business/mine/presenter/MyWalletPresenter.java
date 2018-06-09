package com.hrsoft.taskgo.business.mine.presenter;

import android.content.SharedPreferences;
import android.os.HandlerThread;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.business.mine.contract.MyWalletContract;
import com.hrsoft.taskgo.business.mine.model.helper.MineInformationHelper;
import com.hrsoft.taskgo.business.mine.model.helper.MoneyHelper;
import com.hrsoft.taskgo.business.mine.model.response.MineInformationModel;
import com.hrsoft.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/6/7 21:03.
 * email fanhongyu@hrsoft.net.
 */

public class MyWalletPresenter extends BasePresenter<MyWalletContract.View> implements MyWalletContract.Presenter {

    public MyWalletPresenter(MyWalletContract.View view) {
        super(view);
    }


    @Override
    public void getMoney() {

        IDataCallback.Callback callback = new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {
                if(mView!=null) {
                    mView.getMonetError(error);
                }
            }

            @Override
            public void onDataLoaded(Object o) {
                if(mView!=null) {
                    mView.getMoneySuccess();
                }
            }
        };
        MoneyHelper.getInstance().withdrawMoney(this,callback);

    }

    @Override
    public void getWalletInfo() {

        IDataCallback.Callback dataCallback = new IDataCallback.Callback<MineInformationModel>() {


            @Override
            public void onFailedLoaded(String error) {
                if(mView!=null) {
                    mView.getWalletInfoError(error);
                }
            }

            @Override
            public void onDataLoaded(MineInformationModel mineInformationModel) {
                if (mView != null) {

                    double myMoney = mineInformationModel.getBalance()/100.0;
                    String bankCardNum = mineInformationModel.getBankCard();
                    if (bankCardNum != null) {
                        bankCardNum = bankCardNum.substring(bankCardNum.length()-5);
                    }
                    mView.getWalletInfoSuccess(String.valueOf(myMoney), bankCardNum);
                }
            }
        };
        MineInformationHelper.getInstance().loadMineInformation(this, dataCallback);
    }
}
