package com.hrsoft.taskgo.base.mvp.presenter;

import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.view.IBaseView;

/**
 * @author FanHongyu.
 * @since 18/4/23 18:40.
 * email fanhongyu@hrsoft.net.
 */

public class BasePresenter<M extends BaseModel, V extends IBaseView> implements IBasePresenter<M, V> {


    protected M mModel;
    protected V mView;


    public BasePresenter() {

    }

    /**
     * 绑定v层
     *
     * @param view
     */
    @Override
    public void bindView(V view) {
        mView = view;
    }

    /**
     * 解绑v层
     */
    @Override
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
    @Override
    public boolean isBindView() {
        return false;
    }

    /**
     * 绑定m层
     *
     * @param model
     */
    @Override
    public void bindModel(M model) {
        if (model != null) {
            mModel = model;
        }
    }

    /**
     * 获取M层实例
     *
     * @return
     */
    @Override
    public M getModel() {
        return mModel;
    }

    /**
     * 解绑m层
     */
    @Override
    public void unBindModel() {
        if (mModel != null) {
            mModel.clearNotifyListener();
            mModel = null;
        }
    }

    /**
     * 判断是否正在进行数据加载
     *
     * @return
     */
    @Override
    public boolean isLoading() {
        return mModel != null && mModel.isLoading();
    }
}
