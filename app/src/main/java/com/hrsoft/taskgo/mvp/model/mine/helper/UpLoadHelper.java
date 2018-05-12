package com.hrsoft.taskgo.mvp.model.mine.helper;

import android.util.Log;
import android.util.Xml;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.IDataCallback;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.utils.UrlSafeBase64;
import com.qiniu.util.Auth;

import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cz.msebera.android.httpclient.util.EncodingUtils;

import static android.content.ContentValues.TAG;


/**
 * @author FanHongyu.
 * @since 18/3/9 19:23.
 * email fanhongyu@hrsoft.net.
 */

public class UpLoadHelper {

    private static final String DOMAIN_NAME = "http://p5fd263i7.bkt.clouddn.com/";

    public static void upLoadImgToQiNiu(String imagePath, final IDataCallback.Callback<String> callback) {

        if (imagePath != null) {
            try {

                String accessKey = "V4bRlWyjq34V1c2WVvd39rm4WR7qAcaTtQ0Zs7rL";
                String secretKey = "ot-QQmfoY_Cnnn9FZ2B2_-nNuakOIuQubvFOe1-J";
                String bucket = "taskgopic";
                Auth auth = Auth.create(accessKey, secretKey);
                String uploadToken = auth.uploadToken(bucket);
                Log.i(TAG, "upLoadImgToQiNiu: "+uploadToken);

                Configuration config = new Configuration.Builder().zone(FixedZone.zone1).build();
                UploadManager uploadManager = new UploadManager(config);
                // 设置图片路径、上传后文件名、token
                uploadManager.put(imagePath, +System.currentTimeMillis() + ".png", uploadToken, new UpCompletionHandler() {
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
