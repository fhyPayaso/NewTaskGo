package com.hrsoft.taskgo.base.mvp.presenter;

import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.view.IBaseView;

/**
 * @author FanHongyu.
 * @since 18/4/23 18:43.
 * email fanhongyu@hrsoft.net.
 */

public interface IBasePresenter<M extends BaseModel, V extends IBaseView> {

    /**
     * 绑定v层
     *
     * @param view
     */
    void bindView(V view);

    /**
     * 解绑v层
     */
    void unBindView();

    /**
     * 判断是否已经绑定
     *
     * @return
     */
    boolean isBindView();


    /**
     * 绑定m层
     *
     * @param model
     */
    void bindModel(M model);


    /**
     * 获取M层实例
     *
     * @return
     */
    M getModel();


    /**
     * 解绑m层
     */
    void unBindModel();


    /**
     * 判断是否正在进行数据加载
     *
     * @return
     */
    boolean isLoading();


}