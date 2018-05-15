package com.hrsoft.taskgo.business.mine.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.mine.model.response.MineCardModel;

import java.util.List;

/**
 * @author：lszr on 2018/5/12 20:40
 * @email：1085963811@qq.com
 */
public interface MineCardContract {
    interface Presenter extends IBaseContract.IBasePresenter{

        void loadCardList();


    }


    interface View extends IBaseContract.IBaseView{



        void onloadCardListSuccess(List<MineCardModel> mineCardModels);



    }
}
