package com.hrsoft.taskgo.common;

/**
 * @author FanHongyu.
 * @since 18/4/18 17:31.
 * email fanhongyu@hrsoft.net.
 */

public class Config {


    /**
     * 当前app版本号
     */
    public static final String APP_VERSION = "1.0.0";


    /**
     * 网络请求BaseURL
     */
    public static final String APP_SERVER_BASE_URL = "http://taskgobe.sealbaby.cn/";


    /**
     * 是否为测试版本
     */
    public static final boolean DEBUG = true;


    /**
     * 网络请求连接超时时间，单位：s
     */
    public static final int APP_SERVER_CONNECT_TIME_OUT = 15;


    /**
     * 正确返回码
     */
    public static final int[] NET_CORRECT_CODE = {1000, 2000, 3000, 4000};

    /**
     * 空字段
     */
    public static final String EMPTY_FIELD = "";

    /**
     * 对密码的位数进行设置
     */
    public static final int PASSWORD_MIN = 6;
    public static final int PASSWORD_MAX = 20;

    /**
     * 微信平台应用标识
     */
    public static final String APP_ID = "wx73e42ddc37ff92ff";


    public static final int MY_FOLLOW_LIST=0;
    public static final int MY_FANS_LIST=1;

}
