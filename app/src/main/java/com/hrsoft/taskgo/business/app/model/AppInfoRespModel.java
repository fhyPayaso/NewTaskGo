package com.hrsoft.taskgo.business.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author FanHongyu.
 * @since 18/5/12 18:05.
 * email fanhongyu@hrsoft.net.
 */

public class AppInfoRespModel {


    /**
     * id : 版本信息id
     * name : app名称
     * versionFirstNumber : 1
     * versionSecondNumber : 0
     * versionThirdNumber : 0
     * url : app下载地址
     * about : 不洗碗工作室
     * update_content : 无更内容
     * created_at : 2018-05-12 17:18:12
     */

    @SerializedName("id")
    private int appInfoId;
    private String name;
    @SerializedName("version_first_number")
    private int versionFirstNumber;
    @SerializedName("version_second_number")
    private int versionSecondNumber;
    @SerializedName("version_third_number")
    private int versionThirdNumber;
    private String url;
    private String about;
    @SerializedName("update_content")
    private String updateContent;
    @SerializedName("created_at")
    private String createdAt;

    public int getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(int id) {
        this.appInfoId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersionFirstNumber() {
        return versionFirstNumber;
    }

    public void setVersionFirstNumber(int versionFirstNumber) {
        this.versionFirstNumber = versionFirstNumber;
    }

    public int getVersionSecondNumber() {
        return versionSecondNumber;
    }

    public void setVersionSecondNumber(int versionSecondNumber) {
        this.versionSecondNumber = versionSecondNumber;
    }

    public int getVersionThirdNumber() {
        return versionThirdNumber;
    }

    public void setVersionThirdNumber(int versionThirdNumber) {
        this.versionThirdNumber = versionThirdNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUpdate_content() {
        return updateContent;
    }

    public void setUpdate_content(String update_content) {
        this.updateContent = update_content;
    }

    public String getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(String created_at) {
        this.createdAt = created_at;
    }
}
