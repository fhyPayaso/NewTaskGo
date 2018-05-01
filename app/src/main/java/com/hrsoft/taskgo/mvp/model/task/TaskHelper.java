package com.hrsoft.taskgo.mvp.model.task;

import android.os.Handler;

import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.hrsoft.taskgo.mvp.model.task.bean.BaseTaskModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author fhyPayaso
 * @since 2018/4/30 on 下午5:53
 * fhyPayaso@qq.com
 */
public class TaskHelper extends BaseModel {


    public void loadSchoolSixWaterTaskList(final IDataCallback.Callback<List<BaseTaskModel>> callback) {

        addNotifyListener(callback);

        final List<BaseTaskModel> baseTaskModels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            BaseTaskModel model = new BaseTaskModel();


            model.setUserName("fhyPayaso");
            model.setAvatarUrl("http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png");

            model.setTaskType("校六送水");
            model.setMoney(10.0);
            model.setCardNumber(i);

            model.setFirstTitle("宿舍号 : ");
            model.setFirstVaule("607" + i);
            model.setSecondTitle("送水类型 : ");
            model.setSecondVaule("自取");


            baseTaskModels.add(model);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                callback.onDataLoaded(baseTaskModels);
            }
        }, 2000);


    }
}
