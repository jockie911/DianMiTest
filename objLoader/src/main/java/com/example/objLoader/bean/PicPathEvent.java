package com.example.objLoader.bean;

/**
 * Created by yc on 2017/5/10.
 */

public class PicPathEvent {

    private String path;

    public PicPathEvent(String filePath) {
        path = filePath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
