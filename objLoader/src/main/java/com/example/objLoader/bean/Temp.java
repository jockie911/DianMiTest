package com.example.objLoader.bean;

/**
 * Created by yc on 2017/5/23.
 */

public class Temp extends BaseRequestBean{

    private String access_token;
    private String openid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
