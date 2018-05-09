package com.hrsoft.taskgo.network;

import android.util.Log;

import com.hrsoft.taskgo.common.Config;
import com.hrsoft.taskgo.network.response.ApiException;
import com.hrsoft.taskgo.network.response.ApiResponse;
import com.hrsoft.taskgo.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * @author fhyPayaso
 * @since 2018/4/24 on 上午1:00
 * fhyPayaso@qq.com
 */
public abstract class BaseObserver<T> implements Observer<ApiResponse<T>> {


    public abstract void onSuccess(ApiResponse<T> response);

    public abstract void onError(ApiException exception);


    @Override
    public void onNext(ApiResponse<T> response) {

        boolean isCodeTrue = false;
        for (int code : Config.NET_CORRECT_CODE) {
            if (code == response.getCode()) {
                onSuccess(response);
                isCodeTrue = true;
                break;
            }
        }

        if (!isCodeTrue) {
            onError(new ApiException(response.getCode(), response.getMsg()));
        }
    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof ApiException) {
            onError((ApiException) e);
            NetworkErrorHandler.handler((ApiException) e);
        } else {
            onError(new ApiException(-1, "json解析错误"));
            Log.i(TAG, "onError: " + e.toString());
        }

    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }


    /**
     * RxJava线程调度
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}