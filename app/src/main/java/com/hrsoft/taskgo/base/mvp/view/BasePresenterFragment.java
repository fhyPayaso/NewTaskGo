package com.hrsoft.taskgo.base.mvp.view;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.hrsoft.taskgo.base.activity.BaseActivity;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;

/**
 * @author FanHongyu.
 * @since 18/4/24 14:13.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BasePresenterFragment<P extends BasePresenter, M extends BaseModel> extends Fragment implements
        IBaseView {


    protected P mPresenter;

    @Override
    public void onAttach(Context context) {
        initPresenter();
        super.onAttach(context);
    }


    /**
     * 获取Presenter实例
     *
     * @return
     */
    protected abstract P getPresenter();


    /**
     * 获取Presenter实例
     *
     * @return
     */
    protected abstract M getModel();


    /**
     * 初始化绑定状态
     */
    @SuppressWarnings("unchecked")
    private void initPresenter() {
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.bindView(this);
            mPresenter.bindModel(getModel());
        }
    }

    @Override
    public void onDetach() {
        if (mPresenter != null && mPresenter.isBindView()) {
            mPresenter.unBindView();
            mPresenter = null;
        }
        super.onDetach();
    }
}
