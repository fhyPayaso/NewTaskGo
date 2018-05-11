package com.hrsoft.taskgo.mvp.model.app;

import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * @author FanHongyu.
 * @since 18/5/10 22:14.
 * email fanhongyu@hrsoft.net.
 */

public class AppInfoModel extends BaseBean{


    /**
     * app版本号
     */
    private String appVersion;

    /**
     * app当前版本下载地址
     */
    private String appLoadUrl;


    public AppInfoModel(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppLoadUrl() {
        return appLoadUrl;
    }

    public void setAppLoadUrl(String appLoadUrl) {
        this.appLoadUrl = appLoadUrl;
    }

    @Override
    public boolean checkValue() {
        return false;
    }
}
