package com.hrsoft.taskgo.mvp.contract.mine;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.model.mine.response.MyFollowFansModel;

import java.util.List;

/**
 * @author：lszr on 2018/5/9 18:09
 * @email：1085963811@qq.com
 */
public interface MyFollowContract {

    interface Presenter extends IBaseContract.IBasePresenter{

        void loadMyFollowList();

        void loadMyFansList();

    }


    interface View extends IBaseContract.IBaseView{


        /**
         * 获取关注成功
         * @param myFollowFansModel
         */
        void onloadMyFollowFansListSuccess(List<MyFollowFansModel> myFollowFansModel);



    }
}
