package com.hrsoft.taskgo.business.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.activity.BaseToolBarActivity;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author：lszr on 2018/5/11 22:02
 * @email：1085963811@qq.com
 */
public class ChangeNameActivity extends BaseToolBarActivity {
    @BindView(R.id.edit_change_name)
    EditText editChangeName;
    public static void startChangeNameActivity(Activity activity, Context context) {
        Intent intent = new Intent(context, ChangeNameActivity.class);
        activity.startActivityForResult(intent, 10);

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_name;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_edit_data_name));

    }


    @OnClick(R.id.btn_submmit_new_name)
    public void onViewClicked() {

        String name = editChangeName.getText().toString();



        if("".equals(name)){
            ToastUtil.showToast("请输入新的昵称");
        }else if(name.length()>15){
            ToastUtil.showToast("昵称不能超过15个字");
        }
        else{

            Intent intent = new Intent(this,EditDataActivity.class);
            intent.putExtra("newName", editChangeName.getText().toString().trim());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
