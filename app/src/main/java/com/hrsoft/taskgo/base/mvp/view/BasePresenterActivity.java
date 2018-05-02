package com.hrsoft.taskgo.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hrsoft.taskgo.base.activity.BaseActivity;
import com.hrsoft.taskgo.base.activity.BaseNoBarActivity;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;

/**
 * @author FanHongyu.
 * @since 18/4/23 19:17.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BasePresenterActivity<P extends BasePresenter, M extends BaseModel> extends BaseNoBarActivity {

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
     * 获取Model实例
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
