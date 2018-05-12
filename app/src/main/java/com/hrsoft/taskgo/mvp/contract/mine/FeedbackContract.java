package com.hrsoft.taskgo.mvp.contract.mine;

import com.hrsoft.taskgo.base.mvp.IBaseContract;

/**
 * @author：lszr on 2018/5/7 20:19
 * @email：1085963811@qq.com
 */
public interface FeedbackContract {
    interface Presenter extends IBaseContract.IBasePresenter{

        void submitAdvice(String contents);


    }


    interface View extends IBaseContract.IBaseView{



        void onsubmitAdviceSuccess();



    }

}
