package com.hrsoft.taskgo.common;

/**
 * @author fhyPayaso
 * @since 2018/5/9 on 下午11:24
 * fhyPayaso@qq.com
 */
public class MyTaskConfig {


    /**
     * 我的任务类型bundleKey
     */
    public static final String KEY_MY_TASK_TYPE = "key_my_task_type";

    /**
     * 我发布的任务
     */
    public static final String MY_RELEASE_TASK = "my_release_task";

    /**
     * 未接受
     */
    public static final String MY_RELEASE_NOT_ACCEPTED = MY_RELEASE_TASK + "_not_accepted";

    /**
     * 被接受
     */
    public static final String MY_RELEASE_HAS_ACCEPTED = MY_RELEASE_TASK + "_has_accepted";

    /**
     * 已完成
     */
    public static final String MY_RELEASE_HAS_FINISHED = MY_RELEASE_TASK + "_has_finish";


    /**
     * 我接受的任务
     */
    public static final String MY_ACCEPT_TASK = "my_accept_task";

    /**
     * 未完成
     */
    public static final String MY_ACCEPT_NOT_FINISHED = MY_ACCEPT_TASK + "not_finished";

    /**
     * 已完成
     */
    public static final String MY_ACCEPT_HAS_FINISHED = MY_ACCEPT_TASK + "has_finished";


    /*******后端状态码********/

    //支付状态

    /**
     * 未支付
     */
    public static final int PAY_STATUS_NO_PAY = 0;

    /**
     * 支付成功
     */
    public static final int PAY_STATUS_SUCCESS_PAY = 1;

    /**
     * 支付失败
     */
    public static final int PAY_STATUS_PAY_ERROR = -1;

    //完成状态

    /**
     * 未被接受
     */
    public static final int STATUS_NOT_ACCEPT = 0;

    /**
     * 已被接受
     */
    public static final int STATUS_HAS_ACCEPT = 1;

    /**
     * 申请完成
     */
    public static final int STATUS_APPLY_FINISH = 2;

    /**
     * 已经完成
     */
    public static final int STATUS_HAS_FINISH = 3;

    /**
     * 拒绝完成
     */
    public static final int STATUS_REFUSED_FINISH = 4;

}
