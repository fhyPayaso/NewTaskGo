package com.hrsoft.taskgo.network;

import com.hrsoft.taskgo.business.account.model.request.UpdatePasswordReqModel;
import com.hrsoft.taskgo.business.account.model.request.RegisterReqModel;
import com.hrsoft.taskgo.business.account.model.response.TokenRespModel;
import com.hrsoft.taskgo.business.app.model.AppInfoRespModel;
import com.hrsoft.taskgo.business.message.model.MessageModel;
import com.hrsoft.taskgo.business.message.model.MsgReadDeleteReqModel;
import com.hrsoft.taskgo.business.mine.model.request.BindBankCardModel;
import com.hrsoft.taskgo.business.mine.model.request.FeedBackModel;
import com.hrsoft.taskgo.business.mine.model.request.RealNameModel;
import com.hrsoft.taskgo.business.mine.model.request.UpdateInformationModel;
import com.hrsoft.taskgo.business.mine.model.response.MineCardModel;
import com.hrsoft.taskgo.business.mine.model.response.MineInformationModel;
import com.hrsoft.taskgo.business.mine.model.response.MyFollowFansModel;
import com.hrsoft.taskgo.business.mine.model.response.OtherUserPageModel;
import com.hrsoft.taskgo.business.task.model.request.AcceptTaskReqModel;
import com.hrsoft.taskgo.business.task.model.request.ReleaseTaskReqModel;
import com.hrsoft.taskgo.business.task.model.response.TaskListPrePageRespModel;
import com.hrsoft.taskgo.business.task.model.response.WaterAttributesRespModel;
import com.hrsoft.taskgo.business.task.model.response.WxRepModel;
import com.hrsoft.taskgo.network.response.ApiResponse;
import com.hrsoft.taskgo.business.account.model.request.LoginReqModel;

import java.util.List;

import io.reactivex.Observable;
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
     * 检查app版本信息
     *
     * @return
     */
    @GET("version/new")
    Observable<ApiResponse<AppInfoRespModel>> checkAppVersion();


    /**
     * 检查token是否过期
     *
     * @return
     */
    @GET("token")
    Observable<ApiResponse<String>> checkToken();

    /**
     * 登录账户
     *
     * @param reqModel
     * @return
     */
    @POST("login")
    Observable<ApiResponse<TokenRespModel>> login(@Body LoginReqModel reqModel);


    /**
     * 注册新账户
     *
     * @param registerRespModel
     * @return
     */
    @POST("register")
    Observable<ApiResponse<TokenRespModel>> register(@Body RegisterReqModel registerRespModel);


    /**
     * 忘记密码
     *
     * @param updatePasswordReqModel
     * @return
     */
    @POST("password/forgot")
    Observable<ApiResponse> updatePassword(@Body UpdatePasswordReqModel updatePasswordReqModel);

    /**
     * 获取验证码
     *
     * @param mobile
     * @return
     */
    @GET("captcha/{mobile}")
    Observable<ApiResponse> sendCaptcha(@Path("mobile") String mobile);


    /**
     * 发布水任务
     *
     * @param model
     * @return
     */
    @POST("water")
    Observable<ApiResponse<WxRepModel>> releaseWaterTask(@Body ReleaseTaskReqModel model);


    /**
     * 退回卡片
     *
     * @param waterId
     * @return
     */
    @GET("water/return/cards/{waterId}")
    Observable<ApiResponse> returnCard(@Path("waterId") int waterId);

    /**
     * 退款接口
     *
     * @param waterId
     * @return
     */
    @GET("water/close/{waterId}")
    Observable<ApiResponse> returnMoney(@Path("waterId") int waterId);


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
    Observable<ApiResponse<TaskListPrePageRespModel<String>>> loadMyAcceptTaskList(@Path("status") int status, @Query
            ("page") int page);


    /**
     * 完成任务
     *
     * @param waterTaskId 水任务id
     * @return
     */
    @GET("water/finish/{taskId}")
    Observable<ApiResponse> finishTask(@Path("taskId") int waterTaskId);


    /**
     * 拉取消息列表
     *
     * @return
     */
    @GET("messages")
    Observable<ApiResponse<List<MessageModel>>> loadMessageList();


    /**
     * 标记消息已读
     *
     * @param reqModel
     * @return
     */
    @POST("messages/read")
    Observable<ApiResponse> hasReadMessage(@Body MsgReadDeleteReqModel reqModel);


    /**
     * 删除消息
     *
     * @param reqModel
     * @return
     */
    @POST("messages/delete")
    Observable<ApiResponse> deleteMessage(@Body MsgReadDeleteReqModel reqModel);


    /**
     * @return
     */
    @GET("userInfo")
    Observable<ApiResponse<MineInformationModel>> loadMineInformation();


    /**
     * @return
     */
    @GET("following")
    Observable<ApiResponse<List<MyFollowFansModel>>> loadMyFollowList();

    /**
     * @return
     */
    @GET("follower")
    Observable<ApiResponse<List<MyFollowFansModel>>> loadMyFansList();


    /**
     * @param userId
     * @return
     */
    @GET("userInfo/{user_id}")
    Observable<ApiResponse<OtherUserPageModel>> loadOtherUserPage(@Path("user_id") int userId);


    /**
     * @param userId
     * @return
     */
    @GET("follow/{follower_id}")
    Observable<ApiResponse> concentrateSB(@Path("follower_id") int userId);

    /**
     * @param userId
     * @return
     */
    @GET("unFollow/{follower_id}")
    Observable<ApiResponse> unConcentrateSB(@Path("follower_id") int userId);


    @POST("userInfo")
    Observable<ApiResponse> updateInformation(@Body UpdateInformationModel updateInformationModel);

    @POST("userInfo/auth")
    Observable<ApiResponse> submitRealName(@Body RealNameModel realNameModel);


    @POST("advice")
    Observable<ApiResponse> submitAdvice(@Body FeedBackModel feedBackModel);


    @GET("mycards")
    Observable<ApiResponse<List<MineCardModel>>> loadMineCard();


    /**
     * 提现接口
     *
     * @return
     */
    @GET("pay/money/true")
    Observable<ApiResponse> withdrawMoney();

    /**
     * 绑定银行卡接口
     *
     * @param bindBankCardModel
     * @return
     */
    @POST("userInfo/bank")
    Observable<ApiResponse> bindBankCard(@Body BindBankCardModel bindBankCardModel);
}
