package com.hrsoft.taskgo.mvp.view.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskTypeModel;

import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/27 14:10.
 * email fanhongyu@hrsoft.net.
 */

public class TaskTypeGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<TaskTypeModel> mModelList;


    public TaskTypeGridAdapter(Context context, List<TaskTypeModel> modelList) {
        mContext = context;
        mModelList = modelList;
    }

    @Override
    public int getCount() {
        return mModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        TaskTypeModel model = mModelList.get(position);
        ViewHolder viewHolder;


        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_task, null, false);
            viewHolder.imgTaskTypeIcon = (ImageView) convertView.findViewById(R.id.img_task_type_icon);
            viewHolder.txtTaskTypeName = (TextView) convertView.findViewById(R.id.txt_task_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgTaskTypeIcon.setImageResource(model.getImgResId());
        viewHolder.txtTaskTypeName.setText(model.getTaskTypeName());
        return convertView;
    }


    private class ViewHolder {
        ImageView imgTaskTypeIcon;
        TextView txtTaskTypeName;
    }


}
