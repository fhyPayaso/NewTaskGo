package com.hrsoft.taskgo.business.message.presenter;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.business.message.contract.MessageContract;
import com.hrsoft.taskgo.business.message.model.MessageHelper;
import com.hrsoft.taskgo.business.message.model.MessageModel;
import com.hrsoft.taskgo.business.message.model.MsgReadDeleteReqModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/5/12 11:50.
 * email fanhongyu@hrsoft.net.
 */

public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter {

    public MessagePresenter(MessageContract.View view) {
        super(view);
    }

    @Override
    public void loadMsgList() {

        IDataCallback.Callback<List<MessageModel>> callback = new IDataCallback.Callback<List<MessageModel>>() {
            @Override
            public void onFailedLoaded(String error) {

                if (mView != null) {
                    mView.loadMsgListError(error);
                }
            }

            @Override
            public void onDataLoaded(List<MessageModel> messageModelList) {
                if (mView != null) {
                    if (messageModelList == null || messageModelList.size() == 0) {
                        mView.loadMsgListError("暂无消息");
                    } else {

                        int unreadNum = 0;
                        for (MessageModel model : messageModelList) {
                            if (!model.hasRead()) {
                                unreadNum++;
                            }
                        }
                        mView.loadMsgListSuccess(messageModelList, unreadNum);
                    }
                }
            }
        };
        MessageHelper.getInstance().loadMessageList(this, callback);
    }

    @Override
    public void hasReadMsg(int msgId, final int position) {

        IDataCallback.Callback callback = new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {
                mView.readMsgError(error);
            }

            @Override
            public void onDataLoaded(Object o) {
                mView.readMsgSuccess(position);
            }
        };


        List<Integer> list = new ArrayList<>();
        list.add(msgId);
        MsgReadDeleteReqModel reqModel = new MsgReadDeleteReqModel(list);
        MessageHelper.getInstance().hasReadMessage(this, reqModel, callback);
    }


    @Override
    public void deleteMsg(int msgId, final int position) {

        IDataCallback.Callback callback = new IDataCallback.Callback() {
            @Override
            public void onFailedLoaded(String error) {
                mView.deleteMsgError(error);
            }

            @Override
            public void onDataLoaded(Object o) {
                mView.deleteMsgSuccess(position);
            }
        };
        List<Integer> list = new ArrayList<>();
        list.add(msgId);
        MsgReadDeleteReqModel reqModel = new MsgReadDeleteReqModel(list);
        MessageHelper.getInstance().deleteMessage(this, reqModel, callback);
    }
}
