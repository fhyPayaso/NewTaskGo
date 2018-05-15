package com.hrsoft.taskgo.business.mine.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.mine.model.response.OtherUserPageModel;

/**
 * @author：lszr on 2018/5/10 21:58
 * @email：1085963811@qq.com
 */
public interface OtherUserPageContract {

    interface Presenter extends IBaseContract.IBasePresenter{

        void loadOtherUserPage(int userId);

        void concentrateSB(int userId);

        void unConcentrateSB(int userId);
    }


    interface View extends IBaseContract.IBaseView{


        /**
         * 获取关注成功
         * @param
         */
        void onLoadOtherUserPageSuccess(OtherUserPageModel otherUserPageModel);
        /**
         * 关注成功
         * @param
         */
        void onConcentrateSBSuccess();

        /**
         * 关注成功
         * @param
         */
        void onUnConcentrateSBSuccess();

    }
}
