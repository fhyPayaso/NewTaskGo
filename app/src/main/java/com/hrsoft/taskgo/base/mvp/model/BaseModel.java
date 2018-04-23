package com.hrsoft.taskgo.base.mvp.model;

import android.os.Looper;
import android.os.Message;

import com.hrsoft.taskgo.base.mvp.INotifyListener;
import com.hrsoft.taskgo.base.mvp.WeakHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/23 18:39.
 * email fanhongyu@hrsoft.net.
 */

public class BaseModel implements IBaseModel, WeakHandler.IHandler {


    protected boolean mIsLoading;
    protected WeakHandler mHandler;
    protected List<INotifyListener> mNotifyListeners;


    public BaseModel() {
        mIsLoading = false;
        mHandler = new WeakHandler(Looper.getMainLooper(), this);
    }

    /**
     * 添加通知接口
     *
     * @param notifyListener
     */
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

    /**
     * 移除通知接口
     *
     */
    @Override
    public void clearNotifyListener() {
        if (mNotifyListeners != null) {
            mNotifyListeners.clear();
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

    @Override
    public void handleMsg(Message msg) {

    }
}
