package com.hrsoft.taskgo.mvp.model.account.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author heaijia
 * @since 2018/5/5 下午2:32
 * email 549044363@qq.com
 */

public class TokenResponse {

    @SerializedName("user_id")
    private String userId;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token=token;
    }

}
