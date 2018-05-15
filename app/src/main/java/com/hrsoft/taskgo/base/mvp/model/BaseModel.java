package com.hrsoft.taskgo.base.mvp.model;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.INotifyListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model层基类
 *
 * @author FanHongyu.
 * @since 18/4/23 18:39.
 * email fanhongyu@hrsoft.net.
 */
public abstract class BaseModel implements IBaseContract.IBaseModel {

    /**
     * 维护每一个Presenter所对应的回调
     */
    protected Map<IBaseContract.IBasePresenter, List<INotifyListener>> mListenerMap;


    @Override
    public void addNotifyListener(IBaseContract.IBasePresenter presenter, INotifyListener callback) {

        if (presenter == null) {
            return;
        } else {
            presenter.registerModel(this);
        }

        if (mListenerMap == null) {
            mListenerMap = new HashMap<>();
        }
        List<INotifyListener> notifyListenerList = mListenerMap.get(presenter);
        if (notifyListenerList == null) {
            notifyListenerList = new ArrayList<>();
        }
        notifyListenerList.add(callback);
        mListenerMap.put(presenter, notifyListenerList);
    }

    @Override
    public void removeNotifyListener(IBaseContract.IBasePresenter presenter) {
        if ((mListenerMap != null && mListenerMap.get(presenter) != null)) {
            mListenerMap.get(presenter).clear();
            mListenerMap.remove(presenter);
            presenter = null;
        }
    }

    @Override
    public void removeAllNotifyListener() {
        if (mListenerMap != null) {
            mListenerMap.clear();
        }
    }
}
