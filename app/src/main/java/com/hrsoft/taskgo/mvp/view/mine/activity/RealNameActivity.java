package com.hrsoft.taskgo.mvp.view.mine.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.mvp.contract.mine.RealNameContract;
import com.hrsoft.taskgo.mvp.model.mine.helper.UpLoadHelper;
import com.hrsoft.taskgo.mvp.model.mine.request.RealNameModel;
import com.hrsoft.taskgo.mvp.presenter.mine.RealNamePresneter;
import com.hrsoft.taskgo.utils.DialogUtil;
import com.hrsoft.taskgo.utils.FileUtil;
import com.hrsoft.taskgo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hrsoft.taskgo.utils.FileUtil.CAMERA_REQUEST;

/**
 * @author：lszr on 2018/5/7 20:09
 * @email：1085963811@qq.com
 */
public class RealNameActivity extends BaseToolBarPresenterActivity<RealNameContract.Presenter> implements
        RealNameContract.View,
        IDataCallback.Callback<String> {


    @BindView(R.id.txt_real_name)
    TextView mTxtRealName;
    @BindView(R.id.btn_submit_front_id_card)
    ImageView mBtnSubmitFrontIdCard;
    @BindView(R.id.btn_submit_back_id_card)
    ImageView mBtnSubmitBackIdCard;
    @BindView(R.id.btn_submit_id_card)
    Button mBtnSubmitIdCard;
    @BindView(R.id.edit_real_name)
    EditText mEditRealName;
    private AlertDialog bottomDialog;
    private TextView btnTakePhoto;
    private TextView btnFromAlbum;
    private TextView btnCancel;
    private String photoPath;
    private DialogUtil.NativeProgressDialog mProgressDialog;
    private int imagePosition;
    private RealNameModel mRealNameModel=new RealNameModel(null,null,null);


    @Override
    protected int getLayoutId() {
        if (getIntent().getBooleanExtra("flag",false)){
            return R.layout.activity_real_name_have;
        }else {
            return R.layout.activity_real_name;

        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_real_name));

    }

    public static void startRealNameActivity(Context context,Boolean flag) {
        Intent intent = new Intent(context, RealNameActivity.class);
        intent.putExtra("flag",flag);
        context.startActivity(intent);
    }


    @OnClick({R.id.btn_submit_front_id_card, R.id.btn_submit_back_id_card, R.id.btn_submit_id_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit_front_id_card:
                imagePosition = 0;
                selectFront();
                break;
            case R.id.btn_submit_back_id_card:
                imagePosition = 1;
                selectBack();
                break;
            case R.id.btn_submit_id_card:
                if (checkValue()) {
                    mRealNameModel.setTrueName(mEditRealName.getText().toString());
                    mPresenter.submitRealName(mRealNameModel);
                }
                break;
            default:
                break;
        }
    }

    private Boolean checkValue() {
        if (mRealNameModel.getIdCardFront() == null) {
            ToastUtil.showToast(getString(R.string.txt_real_name_no_front_page));
            return false;
        }
        if (mRealNameModel.getIdCardBack() == null) {
            ToastUtil.showToast(getString(R.string.txt_real_name_no_back_page));
            return false;
        }
        if ("".equals(mEditRealName.getText().toString())){
            ToastUtil.showToast(getString(R.string.txt_real_name_no_name));
            return false;
        }
        return true;
    }

    private void selectBack() {
        FileUtil.verifyStoragePermissions(RealNameActivity.this);
        bottomDialog = new AlertDialog.Builder(this, R.style.dialog_bottom).create();
        bottomDialog.show();
        Window window = bottomDialog.getWindow();
        window.setContentView(R.layout.dialog_upload_image);
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);


        btnTakePhoto = bottomDialog.findViewById(R.id.btn_take_photo);
        btnFromAlbum = bottomDialog.findViewById(R.id.btn_from_album);
        btnCancel = bottomDialog.findViewById(R.id.btn_cancel);
        setDialogOnClick();
    }

    private void selectFront() {
        FileUtil.verifyStoragePermissions(RealNameActivity.this);
        bottomDialog = new AlertDialog.Builder(this, R.style.dialog_bottom).create();
        bottomDialog.show();
        Window window = bottomDialog.getWindow();
        window.setContentView(R.layout.dialog_upload_image);
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);


        btnTakePhoto = bottomDialog.findViewById(R.id.btn_take_photo);
        btnFromAlbum = bottomDialog.findViewById(R.id.btn_from_album);
        btnCancel = bottomDialog.findViewById(R.id.btn_cancel);
        setDialogOnClick();

    }






    /**
     * 设置底部dialog点击事件
     */
    private void setDialogOnClick() {

        //拍照选项点击事件
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String filePath = FileUtil.createNewFile(RealNameActivity.this, "TaskGo");
                photoPath = FileUtil.openCamera(RealNameActivity.this,filePath);
                bottomDialog.dismiss();
            }
        });

        //相册选项点击事件
        btnFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FileUtil.openAlbum(RealNameActivity.this);
                bottomDialog.dismiss();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case CAMERA_REQUEST:
                    if (imagePosition==0){

                        mBtnSubmitFrontIdCard.setImageBitmap(BitmapFactory.decodeFile(photoPath));
                    }else {
                        mBtnSubmitBackIdCard.setImageBitmap(BitmapFactory.decodeFile(photoPath));
                    }
                    UpLoadHelper.upLoadImgToQiNiu(photoPath, this);
                    showProgressDialog();
                    break;
                case FileUtil.ALBUM_REQUEST:
                    String picturePath = FileUtil.getSelectedPicturePath(data, RealNameActivity.this);
                    if(imagePosition==0){
                        mBtnSubmitFrontIdCard.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    }else {
                        mBtnSubmitBackIdCard.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    }
                    photoPath = picturePath;
                    UpLoadHelper.upLoadImgToQiNiu(photoPath, this);
                    showProgressDialog();
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void onDataLoaded(String s) {
        if (imagePosition == 0) {
            mRealNameModel.setIdCardFront(s);
        } else {
            mRealNameModel.setIdCardBack(s);
        }

        dismissProgressDialog();
    }

    @Override
    public void onFailedLoaded(String error) {

    }


    @Override
    protected RealNameContract.Presenter getPresenter() {
        return new RealNamePresneter(this);
    }


    @Override
    public void onsubmitRealNameSuccess() {
        ToastUtil.showToast(getString(R.string.txt_submit_success));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
