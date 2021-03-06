package com.hrsoft.taskgo.business.app.contract;

import com.hrsoft.taskgo.base.activity.BaseActivity;
import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.app.model.AppInfoModel;
import com.hrsoft.taskgo.business.message.view.fragment.MessageFragment;
import com.hrsoft.taskgo.business.mine.view.fragment.MineFragment;
import com.hrsoft.taskgo.business.task.view.fragment.HomeFragment;

/**
 * @author FanHongyu.
 * @since 18/4/27 12:01.
 * email fanhongyu@hrsoft.net.
 */

public interface MainContract {


    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 展示home页面
         */
        HomeFragment showHomeFragment(BaseActivity context, HomeFragment homeFragment);

        /**
         * 展示message页面
         */
        MessageFragment showMessageFragment(BaseActivity context, MessageFragment messageFragment, MessageFragment
                .OnMsgNumberListener listener);

        /**
         * 展示mine页面
         */
        MineFragment showMineFragment(BaseActivity context, MineFragment mineFragment);


        /**
         * 检查app版本号
         */
        void checkAppVersion();

    }


    interface View extends IBaseContract.IBaseView {

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


        /**
         * 版本和线上不一致，需要更新
         *
         * @param appInfoModel
         */
        void needUpdateApk(AppInfoModel appInfoModel);
    }
}
