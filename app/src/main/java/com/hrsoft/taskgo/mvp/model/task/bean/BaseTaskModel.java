package com.hrsoft.taskgo.mvp.model.task.bean;

/**
 * @author FanHongyu.
 * @since 18/4/27 15:44.
 * email fanhongyu@hrsoft.net.
 */

public class BaseTaskModel {


    /**
     * 头像url
     */
    private String avatarUrl;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 任务赏金
     */
    private double money;

    /**
     * 好人卡数量
     */
    private int cardNumber;


    /**
     * 需要展示的必填信息数量
     */
    private int infoNumber;

    /**
     * 第一标题
     */
    private String firstTitle;

    /**
     * 第一必填信息
     */
    private String firstVaule;

    /**
     * 第二标题
     */
    private String secondTitle;

    /**
     * 第二必填信息
     */
    private String secondVaule;


    /**
     * 第三标题
     */
    private String thirdTitle;


    /**
     * 第三必填信息
     */
    private String thirdValue;


}
