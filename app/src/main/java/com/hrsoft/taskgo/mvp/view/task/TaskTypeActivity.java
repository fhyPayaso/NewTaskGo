package com.hrsoft.taskgo.mvp.view.task;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseActivity;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskTypeModel;
import com.hrsoft.taskgo.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author FanHongyu.
 * @since 18/4/27 13:42.
 * email fanhongyu@hrsoft.net.
 */

public class TaskTypeActivity extends BaseActivity {


    @BindView(R.id.ll_root_view)
    LinearLayout llRootView;

    private List<TaskTypeModel> mCompanyModelList;
    private List<TaskTypeModel> mCharitableModelList;

    /**
     * 获取父布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_type;
    }

    /**
     * 加载数据
     *
     * @param savedInstanceState
     */
    @Override
    protected void initData(Bundle savedInstanceState) {


        mCharitableModelList = new ArrayList<>();
        mCompanyModelList = new ArrayList<>();
        TaskTypeModel model1 = new TaskTypeModel(R.drawable.tab_home_normal, "校内送水", 0);
        TaskTypeModel model2 = new TaskTypeModel(R.drawable.tab_home_normal, "零食店铺", 0);
        TaskTypeModel model3 = new TaskTypeModel(R.drawable.tab_home_normal, "带领快递", 0);
        TaskTypeModel model4 = new TaskTypeModel(R.drawable.tab_home_normal, "E管家", 0);
        mCompanyModelList.add(model1);
        mCompanyModelList.add(model2);
        mCompanyModelList.add(model3);
        mCharitableModelList.add(model4);

        FragmentUtil.addFragment(this,R.id.ll_root_view,
                GridTaskFragment.getNewInstance("创新创业",mCompanyModelList),null);

        FragmentUtil.addFragment(this,R.id.ll_root_view,
                GridTaskFragment.getNewInstance("公益团队",mCharitableModelList),null);

    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
