package com.hrsoft.taskgo.mvp.view.message.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 *
 *
 * @author FanHongyu.
 * @since 18/5/12 14:32.
 * email fanhongyu@hrsoft.net.
 */

public class LoopMsgService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
