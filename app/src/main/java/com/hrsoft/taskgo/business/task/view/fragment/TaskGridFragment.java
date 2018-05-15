package com.hrsoft.taskgo.business.task.view.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.fragment.BaseFragment;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.business.task.model.bean.TaskTypeModel;
import com.hrsoft.taskgo.business.task.view.activity.TaskListActivity;
import com.hrsoft.taskgo.business.task.view.adapter.TaskGridAdapter;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.NoScrollGridView;

import java.util.List;

import butterknife.BindView;

/**
 * @author FanHongyu.
 * @since 18/4/27 16:38.
 * email fanhongyu@hrsoft.net.
 */

public class TaskGridFragment extends BaseFragment {


    @BindView(R.id.txt_grid_name)
    TextView txtGridName;
    @BindView(R.id.gv_task_type)
    NoScrollGridView gvTaskType;


    public List<TaskTypeModel> mTypeModelList;
    private TaskGridAdapter mGridAdapter;
    public String mLabelTitle;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_grid_task;
    }

    @Override
    protected void initView() {

        txtGridName.setText(mLabelTitle);
        mGridAdapter = new TaskGridAdapter(getContext(), mTypeModelList);
        gvTaskType.setAdapter(mGridAdapter);
        gvTaskType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showTaskList(mTypeModelList.get(position).getTaskTypeFlag());
            }
        });
    }


    /**
     * 进入任务列表
     *
     * @param taskType
     */
    private void showTaskList(String taskType) {
        switch (taskType) {
            case TaskTypeConfig.COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL:
                TaskListActivity.startActivity(getContext(), taskType);
                break;
            default:
                ToastUtil.showToast("敬请期待");
                break;
        }
    }


    @Override
    protected void initData() {
    }

    /**
     * 获取fragment实例
     *
     * @param label
     * @param modelList
     * @return
     */
    public static TaskGridFragment getNewInstance(String label, List<TaskTypeModel> modelList) {
        TaskGridFragment fragment = new TaskGridFragment();
        fragment.mTypeModelList = modelList;
        fragment.mLabelTitle = label;
        return fragment;
    }

}
