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
    }


    interface IBaseModel {


        void addNotifyListener(INotifyListener notifyListener);

        void removeNotifyListener(List<INotifyListener> listenerList);

        boolean removeNotifyListener(INotifyListener listener);

        void removeAllNotifyListener();

    }
}
