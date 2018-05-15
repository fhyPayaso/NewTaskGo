package com.hrsoft.taskgo.business.mine.model.helper;

import android.util.Log;

import com.hrsoft.taskgo.base.mvp.IBaseContract;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.hrsoft.taskgo.base.mvp.model.BaseModel;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.util.Auth;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;


/**
 * 图片上传至七牛云
 *
 * @author FanHongyu.
 * @since 18/3/9 19:23.
 * email fanhongyu@hrsoft.net.
 */

public class UpLoadHelper extends BaseModel {


    private UpLoadHelper() {
    }

    private static class UpLoadHelperHolder {
        private static final UpLoadHelper INSTANCE = new UpLoadHelper();
    }

    public static UpLoadHelper getInstance() {
        return UpLoadHelperHolder.INSTANCE;
    }


    private static final String DOMAIN_NAME = "http://p5fd263i7.bkt.clouddn.com/";

    public void upLoadImgToQiNiu(IBaseContract.IBasePresenter presenter, String imagePath, final IDataCallback.Callback<String> callback) {
        addNotifyListener(presenter,callback);

        if (imagePath != null) {
            try {

                String accessKey = "V4bRlWyjq34V1c2WVvd39rm4WR7qAcaTtQ0Zs7rL";
                String secretKey = "ot-QQmfoY_Cnnn9FZ2B2_-nNuakOIuQubvFOe1-J";
                String bucket = "taskgopic";
                Auth auth = Auth.create(accessKey, secretKey);
                String uploadToken = auth.uploadToken(bucket);
                Log.i(TAG, "upLoadImgToQiNiu: " + uploadToken);

                Configuration config = new Configuration.Builder().zone(FixedZone.zone1).build();
                UploadManager uploadManager = new UploadManager(config);
                // 设置图片路径、上传后文件名、token
                uploadManager.put(imagePath, +System.currentTimeMillis() + ".png", uploadToken, new
                        UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info, JSONObject res) {
                                // info.error中包含了错误信息，可打印调试
                                // 上传成功后将key值上传到自己的服务器
                                if (info.isOK()) {
                                    String imgUrl = DOMAIN_NAME + key;
                                    callback.onDataLoaded(imgUrl);
                                } else {
                                    Log.d("upLoadImgToQiNiu", "complete: " + info.error);
                                    callback.onFailedLoaded("图片上传失败");
                                }
                            }
                        }, null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            callback.onFailedLoaded("图片上传失败");
        }
    }
}
