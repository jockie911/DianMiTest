package com.example.objLoader.bean;

/**
 * Created by yc on 2017/5/26.
 */

public class BaseHttpBean<T> {

    private String iserror;

    private String info;

    private T data;

    public String getIserror() {
        return iserror;
    }

    public void setIserror(String iserror) {
        this.iserror = iserror;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
