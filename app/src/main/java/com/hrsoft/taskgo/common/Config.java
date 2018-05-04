package com.hrsoft.taskgo.common;

/**
 * @author FanHongyu.
 * @since 18/4/18 17:31.
 * email fanhongyu@hrsoft.net.
 */

public class Config {


    /**
     * 网络请求BaseURL
     */
    public static final String APP_SERVER_BASE_URL = "http://taskgobe.sealbaby.cn/";


    /**
     * 网络请求连接超时时间，单位：s
     */
    public static final int APP_SERVER_CONNECT_TIME_OUT = 15;


    /**
     * 是否为测试版本
     */
    public static final boolean DEBUG = true;


    /**
     * 请求头
     */
    public static final String TOKEN = "token";


    /**
     * 正确返回码
     */
    public static final int[] NET_CORRECT_CODE = {1000, 2000};


}
