package com.hrsoft.taskgo.base.mvp.presenter;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.INotifyListener;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/23 18:40.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BasePresenter<V extends IBaseContract.IBaseView> implements IBaseContract.IBasePresenter {

    protected V mView;
    protected List<IBaseContract.IBaseModel> mBaseModelList;

    public BasePresenter(V view) {
        mView = view;
        mBaseModelList = new ArrayList<>();
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
    protected boolean isBindView() {
        return !(mView == null);
    }

    /**
     * 解绑与m层相关的回调
     */
    protected void unBindModel() {
        for (IBaseContract.IBaseModel baseModel : mBaseModelList) {
            baseModel.removeNotifyListener(this);
        }
    }


    /**
     * 一个presenter可能注册多个model
     * @param model
     */
    @Override
    public void registerModel(IBaseContract.IBaseModel model) {
        if (!mBaseModelList.contains(model)) {
            mBaseModelList.add(model);
        }
    }
}
