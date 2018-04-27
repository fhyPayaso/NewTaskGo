package com.hrsoft.taskgo.mvp.view.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.fragment.BaseFragment;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskTypeModel;
import com.hrsoft.taskgo.mvp.view.task.adapter.TaskTypeGridAdapter;
import com.hrsoft.taskgo.utils.ToastUtil;
import com.hrsoft.taskgo.widget.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author FanHongyu.
 * @since 18/4/27 16:38.
 * email fanhongyu@hrsoft.net.
 */

public class GridTaskFragment extends BaseFragment {


    @BindView(R.id.txt_grid_name)
    TextView txtGridName;
    @BindView(R.id.gv_task_type)
    NoScrollGridView gvTaskType;


    public List<TaskTypeModel> mTypeModelList;
    private TaskTypeGridAdapter mGridAdapter;
    public String mLabelTitle;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_grid_task;
    }

    @Override
    protected void initView() {

        txtGridName.setText(mLabelTitle);
        mGridAdapter = new TaskTypeGridAdapter(getContext(),mTypeModelList);
        gvTaskType.setAdapter(mGridAdapter);
        gvTaskType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(mTypeModelList.get(position).getTaskTypeName());
            }
        });
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
    public static GridTaskFragment getNewInstance(String label, List<TaskTypeModel> modelList) {
        GridTaskFragment fragment = new GridTaskFragment();
        fragment.mTypeModelList = modelList;
        fragment.mLabelTitle = label;
        return fragment;
    }

}
