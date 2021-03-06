package com.example.objLoader.nohttp;


import com.yolanda.nohttp.rest.Response;

public abstract class HttpCallBack<T> {

	public void onSucceed(int what, Response<T> response){};

	public void onSucceed(int what, T bean){};

	public void onFailed(int what){};

	public void onFailed(int what,String errorInfo){};

	public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis){};
}
