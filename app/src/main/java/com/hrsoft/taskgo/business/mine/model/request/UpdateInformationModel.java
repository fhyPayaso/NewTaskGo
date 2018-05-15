package com.hrsoft.taskgo.business.mine.model.request;

/**
 * @author：lszr on 2018/5/11 22:17
 * @email：1085963811@qq.com
 */
public class UpdateInformationModel {
    private String avatar;
    private String name;

    private String sex;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public UpdateInformationModel(String avatar, String name, String sex) {

        this.avatar = avatar;
        this.name = name;
        this.sex = sex;
    }
}
