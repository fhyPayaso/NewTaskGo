package com.hrsoft.taskgo.network;

import com.hrsoft.taskgo.mvp.model.account.request.ForgetPasswordModel;
import com.hrsoft.taskgo.mvp.model.account.request.RegisterReqModel;
import com.hrsoft.taskgo.mvp.model.account.request.TokenResponse;
import com.hrsoft.taskgo.mvp.model.task.request.ReleaseTaskReqModel;
import com.hrsoft.taskgo.mvp.model.task.response.TasListRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.WaterAttributesRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.WxRepModel;
import com.hrsoft.taskgo.network.response.ApiResponse;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author fhyPayaso
 * @since 2018/4/24 on 上午12:18
 * fhyPayaso@qq.com
 */
public interface ApiService {


    @POST("login")
    Observable<ApiResponse<TokenResponse>> login(@Body LoginReqModel reqModel);


    @POST("register")
    Observable<ApiResponse<TokenResponse>> register(@Body RegisterReqModel registerReqModel);


    @POST("password/forgot")
    Observable<ApiResponse> forgetPassWord(@Body ForgetPasswordModel forgetPasswordModel);


    @GET("captcha/{mobile}")
    Observable<ApiResponse> sendCaptcha(@Path("mobile") String mobile);
    /**
     * 检查token是否过期
     *
     * @param
     * @return
     */
    @GET("token")
    Observable<ApiResponse> updateToke();

//    Observable<ApiResponse> updateToke(@Path("token") String token);


    /**
     * 发布水任务
     *
     * @param model
     * @return
     */
    @POST("water")
    Observable<ApiResponse<WxRepModel>> releaseWaterTask(@Body ReleaseTaskReqModel model);


    /**
     * 拉取水任务列表
     *
     * @return
     */
    @GET("waters")
    Observable<ApiResponse<List<TasListRespModel<WaterAttributesRespModel>>>> loadWaterTaskList();


    /**
     * 接取水任务
     *
     * @param taskArray 任务信息列表
     * @return
     */
    @POST("tasks/accept/waters")
    Observable<ApiResponse> acceptWaterTask(@Body List<Integer> taskArray);


}
