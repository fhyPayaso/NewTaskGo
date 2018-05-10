package com.hrsoft.taskgo.base.mvp.model;

import com.hrsoft.taskgo.base.mvp.INotifyListener;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:01.
 * email fanhongyu@hrsoft.net.
 */

public interface IBaseModel {


    /**
     * 添加通知接口
     *
     * @param notifyListener
     */
    void addNotifyListener(INotifyListener notifyListener);

    /**
     * 移除通知接口
     *
     */
    void clearNotifyListener();


    /**
     * 判断M层正在加载数据
     *
     * @return
     */
    boolean isLoading();
}
