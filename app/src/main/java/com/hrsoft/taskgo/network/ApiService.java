package com.hrsoft.taskgo.network;

import com.hrsoft.taskgo.network.response.ApiResponse;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author fhyPayaso
 * @since 2018/4/24 on 上午12:18
 * fhyPayaso@qq.com
 */
public interface ApiService {



    @POST("sss")
    Observable<ApiResponse> login(@Body LoginReqModel reqModel);

}
