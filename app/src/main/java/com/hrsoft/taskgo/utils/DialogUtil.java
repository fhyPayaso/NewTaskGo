package com.hrsoft.taskgo.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * @author FanHongyu.
 * @since 18/5/5 22:03.
 * email fanhongyu@hrsoft.net.
 */

public final class DialogUtil {


    /**
     * 快速dialog
     */
    public static class QuickDialog {

        private Context mContext;

        public QuickDialog(Context context) {
            mContext = context;
        }

        private DialogPositiveButtonListener mListener;

        public QuickDialog showDialog(String message) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
            dialog.setMessage(message);
            dialog.setCancelable(true);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (mListener != null) {
                        mListener.onPositiveButtonClick();
                    }
                }
            });
            dialog.setNegativeButton("取消", null);
            dialog.show();
            return this;
        }

        public QuickDialog setClickListener(DialogPositiveButtonListener listener) {
            mListener = listener;
            return this;
        }

        public interface DialogPositiveButtonListener {
            void onPositiveButtonClick();
        }
    }





























}
