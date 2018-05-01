package com.hrsoft.taskgo.mvp.model.task.bean;

/**
 * 任务列表
 *
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


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    public String getFirstVaule() {
        return firstVaule;
    }

    public void setFirstVaule(String firstVaule) {
        this.firstVaule = firstVaule;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getSecondVaule() {
        return secondVaule;
    }

    public void setSecondVaule(String secondVaule) {
        this.secondVaule = secondVaule;
    }

    public String getThirdTitle() {
        return thirdTitle;
    }

    public void setThirdTitle(String thirdTitle) {
        this.thirdTitle = thirdTitle;
    }

    public String getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(String thirdValue) {
        this.thirdValue = thirdValue;
    }
}
