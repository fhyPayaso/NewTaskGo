package com.hrsoft.taskgo.base.mvp.model;

import android.os.Looper;
import android.os.Message;

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
public class BaseModel implements IBaseModel {


    protected boolean mIsLoading;
    protected WeakHandler mHandler;
    protected List<INotifyListener> mNotifyListeners;


    public BaseModel() {
        mIsLoading = false;
    }

    /**
     * 添加通知接口
     *
     * @param notifyListener
     */
    public <T extends INotifyListener> void addNotifyListener(T notifyListener) {

        if (notifyListener == null) {
            throw new NullPointerException("INotifyListener could not be null");
        }
        if (mNotifyListeners == null) {
            mNotifyListeners = new LinkedList<>();
        }
        mNotifyListeners.add(notifyListener);
    }

    /**
     * 移除通知接口
     */
    @Override
    public void clearNotifyListener() {
        if (mNotifyListeners != null) {
            mNotifyListeners.clear();
        }
    }

    public boolean removeListener(INotifyListener listener) {
        if (listener != null) {
            return mNotifyListeners.remove(listener);
        } else {
            return false;
        }
    }

    /**
     * 判断M层正在加载数据
     *
     * @return
     */
    @Override
    public boolean isLoading() {
        return mIsLoading;
    }

}
