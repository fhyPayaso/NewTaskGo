package com.hrsoft.taskgo.business.mine.view.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.view.BaseToolBarPresenterActivity;
import com.hrsoft.taskgo.business.mine.contract.EditDataContract;
import com.hrsoft.taskgo.business.mine.model.helper.UpLoadHelper;
import com.hrsoft.taskgo.business.mine.model.request.UpdateInformationModel;
import com.hrsoft.taskgo.business.mine.model.response.MineInformationModel;
import com.hrsoft.taskgo.business.mine.presenter.EditDataPresenter;
import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.utils.DialogUtil;
import com.hrsoft.taskgo.utils.FileUtil;
import com.hrsoft.taskgo.utils.ToastUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author：lszr on 2018/5/11 21:42
 * @email：1085963811@qq.com
 */
public class EditDataActivity extends BaseToolBarPresenterActivity<EditDataContract.Presenter> implements
        EditDataContract.View {


    public int mGender = -1;
    @BindView(R.id.txt_mine_status)
    TextView mTxtMineStatus;


    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public MineInformationModel mineInformationModel = null;
    private boolean mHasPermissions = true;


    /**
     * 0代表不需要上传图片，1代表需要上传图片
     */
    private Boolean hasImg = false;
    private AlertDialog bottomDialog;
    private TextView btnTakePhoto;
    private TextView btnFromAlbum;
    private TextView btnCancel;
    private String photoPath;
    private String newName = null;
    private Boolean nameFlag = false;


    @BindView(R.id.txt_mine_phone_number)
    TextView mTxtMinePhoneNumber;
    @BindView(R.id.img_portrait)
    CircleImageView imgPortrait;
    @BindView(R.id.txt_mine_name)
    TextView mTxtMineName;
    @BindView(R.id.txt_mine_edit_data_gender)
    TextView mTxtMineEditDataGender;


    public static void startEditDataActivity(Context context, MineInformationModel mineInformationModel) {
        Intent intent = new Intent(context, EditDataActivity.class);
        String jsonData = new Gson().toJson(mineInformationModel);
        intent.putExtra("MineInformationModel", jsonData);
        context.startActivity(intent);
    }


    @OnClick({R.id.rlayout_mine_edit_photo, R.id.rlayout_mine_edit_name, R.id.rlayout_mine_edit_gender})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlayout_mine_edit_photo:
                updatePhoto();
                break;
            case R.id.rlayout_mine_edit_name:
                ChangeNameActivity.startChangeNameActivity(EditDataActivity.this, EditDataActivity.this);
                break;
            case R.id.rlayout_mine_edit_gender:
                setGender();
                break;
            default:
                break;
        }
    }

    public void setGender() {
        DialogUtil.NativeDialog dialog = new DialogUtil().new NativeDialog().singleInit(EditDataActivity.this);
        dialog.setSingleChoice(new String[]{"男", "女", "未知"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        mGender = 0;
                        break;
                    case 1:
                        mGender = 1;
                        break;
                    case 2:
                        mGender = 2;
                        break;
                    default:
                        break;
                }
            }
        })
                .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (mGender) {
                            case 0:
                                mTxtMineEditDataGender.setText(getString(R.string.txt_gender_man));
                                mineInformationModel.setSex("男");
                                mPresenter.updateInformation(
                                        new UpdateInformationModel(
                                                mineInformationModel.getAvatar(),
                                                mineInformationModel.getName(),
                                                "男"));
                                break;
                            case 1:
                                mTxtMineEditDataGender.setText(getString(R.string.txt_gender_woman));
                                mineInformationModel.setSex("女");
                                mPresenter.updateInformation(
                                        new UpdateInformationModel(
                                                mineInformationModel.getAvatar(),
                                                mineInformationModel.getName(),
                                                "女"));
                                break;
                            case 2:
                                mTxtMineEditDataGender.setText(getString(R.string.txt_gender_unknown));
                                mineInformationModel.setSex("未知");
                                mPresenter.updateInformation(
                                        new UpdateInformationModel(
                                                mineInformationModel.getAvatar(),
                                                mineInformationModel.getName(),
                                                "未知"));
                                break;
                            default:
                                break;
                        }
                    }
                })
                .showNativeDialog();
    }

    private void updatePhoto() {
        FileUtil.verifyStoragePermissions(EditDataActivity.this);
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
                if (FileUtil.checkPermissions(EditDataActivity.this)) {
                    String filePath = FileUtil.createNewFile(EditDataActivity.this, "TaskGo");
                    photoPath = FileUtil.openCamera(EditDataActivity.this, filePath);
                }
                bottomDialog.dismiss();
            }
        });

        //相册选项点击事件
        btnFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FileUtil.checkPermissions(EditDataActivity.this)) {
                    FileUtil.openAlbum(EditDataActivity.this);
                }
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
    protected int getLayoutId() {
        return R.layout.activity_edit_data;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        initInformation();
    }

    private void initInformation() {
        Intent intent = getIntent();
        mineInformationModel = new Gson().fromJson(intent.getStringExtra("MineInformationModel"),
                MineInformationModel.class);

        Glide.with(EditDataActivity.this).load(mineInformationModel.getAvatar()).into(imgPortrait);
        mTxtMineName.setText(mineInformationModel.getName());


        if (mineInformationModel.getSex() == null) {
            mTxtMineEditDataGender.setText("未知");
        } else if (mineInformationModel.getSex().equals("男")) {
            mTxtMineEditDataGender.setText("男");
        } else if (mineInformationModel.getSex().equals("女")) {
            mTxtMineEditDataGender.setText("女");
        } else {
            mTxtMineEditDataGender.setText("未知");

        }


        mTxtMinePhoneNumber.setText(mineInformationModel.getMobile());
        if (mineInformationModel.getStatus() == 0) {
            mTxtMineStatus.setText("未认证");
        } else {
            mTxtMineStatus.setText("已认证");
        }

        setActivityTitle(getString(R.string.txt_mine_edit_data));
    }

    @Override
    public void onUpdateInformationSuccess() {
        ToastUtil.showToast(getString(R.string.txt_change_mine_data_success));
        updateNew();
    }

    @Override
    public void onUploadToQiNiuSuccess(String imgUrl) {

        mPresenter.updateInformation(new UpdateInformationModel(imgUrl,
                mineInformationModel.getName(),
                mineInformationModel.getSex()
        ));
        ToastUtil.showToast("更新头像成功！");
        dismissProgressDialog();


    }

    @Override
    public void onUploadToQiNiuError(String error) {

        ToastUtil.showToast(error);
        dismissProgressDialog();
    }

    @Override
    protected EditDataContract.Presenter getPresenter() {
        return new EditDataPresenter(this);
    }


    private void updateNew() {
        mTxtMineName.setText(mineInformationModel.getName());


        if (mineInformationModel.getSex().equals("男")) {
            mTxtMineEditDataGender.setText("男");
        } else if (mineInformationModel.getSex().equals("女")) {
            mTxtMineEditDataGender.setText("女");
        } else {
            mTxtMineEditDataGender.setText("未知");
        }
        mTxtMinePhoneNumber.setText(mineInformationModel.getMobile());
        if (mineInformationModel.getStatus() == 0) {
            mTxtMineStatus.setText("未认证");
        } else {
            mTxtMineStatus.setText("已认证");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nameFlag) {
            mTxtMineName.setText(newName);
            mPresenter.updateInformation(new UpdateInformationModel(
                    mineInformationModel.getAvatar(),
                    newName,
                    mineInformationModel.getSex()));
            mineInformationModel.setName(newName);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case FileUtil.CAMERA_REQUEST:
                    imgPortrait.setImageBitmap(BitmapFactory.decodeFile(photoPath));
                    mPresenter.uploadToQiNiu(photoPath);
                    showProgressDialog();
                    hasImg = true;
                    break;
                case FileUtil.ALBUM_REQUEST:
                    String picturePath = FileUtil.getSelectedPicturePath(data, EditDataActivity.this);
                    imgPortrait.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    photoPath = picturePath;
                    mPresenter.uploadToQiNiu(photoPath);
                    showProgressDialog();
                    hasImg = true;
                    break;
                case 10:
                    newName = data.getStringExtra("newName");
                    if (!mineInformationModel.getName().equals(newName)) {
                        nameFlag = true;
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
