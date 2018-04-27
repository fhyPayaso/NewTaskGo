package com.hrsoft.taskgo.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hrsoft.taskgo.base.activity.BaseActivity;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;

/**
 * @author FanHongyu.
 * @since 18/4/24 14:12.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BaseToolBarPresenterActivity<P extends BasePresenter, M extends BaseModel> extends
        BaseToolBarActivity implements IBaseView {


    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initPresenter();
        super.onCreate(savedInstanceState);
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
    protected void onDestroy() {

        if (mPresenter != null && mPresenter.isBindView()) {
            mPresenter.unBindView();
            mPresenter = null;
        }
        super.onDestroy();
    }


}
