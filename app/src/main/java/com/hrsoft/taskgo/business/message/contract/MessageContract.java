package com.hrsoft.taskgo.business.message.contract;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.business.message.model.MessageModel;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/5/12 11:50.
 * email fanhongyu@hrsoft.net.
 */

public interface MessageContract {


    interface Presenter extends IBaseContract.IBasePresenter {


        /**
         * 拉取消息列表
         */
        void loadMsgList();

        /**
         * 标记消息已读
         *
         * @param msgId
         */
        void hasReadMsg(int msgId, int position);


        /**
         * 删除消息
         *
         * @param msgId
         */
        void deleteMsg(int msgId, int position);
    }


    interface View extends IBaseContract.IBaseView {


        /**
         * 加载消息列表成功
         *
         * @param modelList    消息列表
         * @param unreadMsgNum 未读消息数量
         */
        void loadMsgListSuccess(List<MessageModel> modelList, int unreadMsgNum);


        /**
         * 加载信息列表失败
         *
         * @param error
         */
        void loadMsgListError(String error);


        /**
         * 读取信息成功
         *
         * @param position
         */
        void readMsgSuccess(int position);


        /**
         * 读取信息失败
         *
         * @param error
         */
        void readMsgError(String error);


        /**
         * 删除信成功
         *
         * @param position
         */
        void deleteMsgSuccess(int position);


        /**
         * 删除信息失败
         *
         * @param error
         */
        void deleteMsgError(String error);
    }

}
