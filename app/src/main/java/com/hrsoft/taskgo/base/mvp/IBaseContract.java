package com.hrsoft.taskgo.base.mvp;

import java.util.List;

/**
 * @author fhyPayaso
 * @since 2018/5/3 on 上午1:55
 * fhyPayaso@qq.com
 */
public interface IBaseContract {


    interface IBaseView {

    }


    interface IBasePresenter {

        void unBindView();

        void registerModel(IBaseModel model);
    }


    interface IBaseModel {


        void addNotifyListener(IBaseContract.IBasePresenter presenter, INotifyListener listener);

        void removeNotifyListener(IBaseContract.IBasePresenter presenter);

        void removeAllNotifyListener();
    }
}
