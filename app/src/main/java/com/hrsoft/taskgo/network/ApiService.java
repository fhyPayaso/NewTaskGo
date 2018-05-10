package com.hrsoft.taskgo.network;

import com.hrsoft.taskgo.mvp.model.task.request.AcceptTaskReqModel;
import com.hrsoft.taskgo.mvp.model.task.request.ReleaseTaskReqModel;
import com.hrsoft.taskgo.mvp.model.task.response.TasListRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.TaskListPrePageRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.WaterAttributesRespModel;
import com.hrsoft.taskgo.mvp.model.task.response.WxRepModel;
import com.hrsoft.taskgo.network.response.ApiResponse;
import com.hrsoft.taskgo.mvp.model.account.request.LoginReqModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author fhyPayaso
 * @since 2018/4/24 on 上午12:18
 * fhyPayaso@qq.com
 */
public interface ApiService {


    /**
     * 发布水任务
     *
     * @param model
     * @return
     */
    @POST("water")
    Observable<ApiResponse<WxRepModel>> releaseWaterTask(@Body ReleaseTaskReqModel model);


    /**
     * 分页拉取水任务列表
     *
     * @param page 页码
     * @return
     */
    @GET("waters")
    Observable<ApiResponse<TaskListPrePageRespModel<WaterAttributesRespModel>>> loadWaterTaskList(@Query("page") int
                                                                                                          page);

    /**
     * 接取水任务
     *
     * @return
     */
    @POST("tasks/accept/waters")
    Observable<ApiResponse> acceptWaterTask(@Body AcceptTaskReqModel reqModel);

    /**
     * 拉取我接受的任务列表
     *
     * @param status 任务状态
     * @return
     */
    @GET("me/tasks/master/")
    Observable<ApiResponse<TaskListPrePageRespModel>> getMyAcceptTask(@Query("status") int status);


    /**
     * 查询水任务支付状态
     *
     * @param waterTaskId
     * @return
     */
    @GET("water/status/{waterid}")
    Observable<ApiResponse<String>> checkWaterTaskPayStatus(@Path("waterid") int waterTaskId);


    /**
     * 我发布的任务
     *
     * @param status 任务状态
     * @return
     */
    @GET("me/tasks/master/{status}")
    Observable<ApiResponse<TaskListPrePageRespModel<String>>> loadMyReleaseTaskList(@Path("status") int status,
                                                                                    @Query("page") int page);


    /**
     * 我接受的任务
     *
     * @param status 任务状态
     * @return
     */
    @GET("me/tasks/accept/{status}")
    Observable<ApiResponse<TaskListPrePageRespModel<String>>> loadMyAcceptTaskList(@Path("status") int status, @Query("page") int page);


    /**
     * 完成任务
     *
     * @param waterTaskId 水任务id
     * @return
     */
    @GET("water/finish/{taskId}")
    Observable<ApiResponse> finishTask(@Path("taskId") int waterTaskId);


}
