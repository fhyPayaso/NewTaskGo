package com.hrsoft.taskgo.mvp.presenter;

import com.hrsoft.taskgo.base.activity.BaseActivity;
import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.mvp.view.message.MessageFragment;
import com.hrsoft.taskgo.mvp.view.mine.MineFragment;
import com.hrsoft.taskgo.mvp.view.task.fragment.HomeFragment;

/**
 * @author FanHongyu.
 * @since 18/4/27 12:01.
 * email fanhongyu@hrsoft.net.
 */

public interface MainContarct {


    interface Presenter extends IBaseContract.IBasePresenter{

        /**
         * 展示home页面
         */
        HomeFragment showHomeFragment(BaseActivity context, HomeFragment homeFragment);

        /**
         * 展示message页面
         */
        MessageFragment showMessageFragment(BaseActivity context, MessageFragment messageFragment);

        /**
         * 展示mine页面
         */
        MineFragment showMineFragment(BaseActivity context, MineFragment mineFragment);

    }


    interface View extends IBaseContract.IBaseView{

        /**
         * 清除所有标签状态
         */
        void clearChoiceStatus();

        /**
         * 隐藏所有fragment
         */
        void hideAllFragments();

        /**
         * 标签更新至home选中
         */
        void changeToHomeStatus();

        /**
         * 标签更新至message选中
         */
        void changeToMessageStatus();

        /**
         * 标签更新至mine选中
         */
        void changeToMineStatus();
    }
}
