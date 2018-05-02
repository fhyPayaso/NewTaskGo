package com.hrsoft.taskgo.base.mvp.presenter;

import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.view.IBaseView;

/**
 * @author FanHongyu.
 * @since 18/4/23 18:40.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BasePresenter<V extends IBaseView>{

    protected V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    /**
     * 绑定v层
     *
     * @param view
     */
    public void bindView(V view) {
        mView = view;
    }

    /**
     * 解绑v层
     */
    public void unBindView() {
        if (mView != null) {
            mView = null;
        }
        unBindModel();
    }

    /**
     * 判断是否已经绑定
     *
     * @return
     */
    public boolean isBindView() {
        return false;
    }

    /**
     * 解绑m层
     */
    protected abstract void unBindModel();
}
