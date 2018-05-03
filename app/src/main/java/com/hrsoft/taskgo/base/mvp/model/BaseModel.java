package com.hrsoft.taskgo.base.mvp.model;

import android.os.Looper;
import android.os.Message;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.INotifyListener;
import com.hrsoft.taskgo.base.mvp.WeakHandler;
import com.hrsoft.taskgo.mvp.model.account.helper.AccountHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/23 18:39.
 * email fanhongyu@hrsoft.net.
 */
public abstract class BaseModel implements IBaseContract.IBaseModel {


    protected WeakHandler mHandler;
    protected List<INotifyListener> mNotifyListeners;



    @Override
    public void addNotifyListener(INotifyListener notifyListener) {
        if (notifyListener == null) {
            throw new NullPointerException("INotifyListener could not be null");
        }
        if (mNotifyListeners == null) {
            mNotifyListeners = new LinkedList<>();
        }
        mNotifyListeners.add(notifyListener);
    }


    @Override
    public boolean removeNotifyListener(INotifyListener listener) {
        if (listener != null) {
            return mNotifyListeners.remove(listener);
        }
        return false;
    }


    @Override
    public void removeNotifyListener(List<INotifyListener> listenerList) {

        if (listenerList != null) {
            for (INotifyListener listener : listenerList) {
                removeNotifyListener(listener);
            }
        }
    }


    @Override
    public void removeAllNotifyListener() {
        if (mNotifyListeners != null) {
            mNotifyListeners.clear();
        }
    }


}
