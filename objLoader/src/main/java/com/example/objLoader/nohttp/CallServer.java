package com.example.objLoader.nohttp;

import com.example.objLoader.R;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.utils.Utils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;

import android.content.Context;

public class CallServer {
	private static CallServer instance;
	private static DownloadQueue downloadQueue;
	private RequestQueue queue;

	public synchronized static CallServer getInstance() {
		if (instance == null) {
			instance = new CallServer();
		}
		return instance;
	}
	public static DownloadQueue getDownloadInstance() {
        if (downloadQueue == null)
            downloadQueue = NoHttp.newDownloadQueue();
        return downloadQueue;
    }

	private CallServer() {
		//添加一个请求队列
		queue = NoHttp.newRequestQueue();
	}

	/**
	 * 添加一个请求到请求队列
	 * 
	 * @param context 上下文
	 * @param request 请求对象
	 * @param callBack 接受回调结果
	 * @param what what，当多个请求用同一个responseListener接受结果时，用来区分请求
	 * @param isShowDialog 是否显示dialog
	 * @param isCanCancel 请求是否能被用户取消
	 * @param classBean 请求实体类
	 */
	public <T> void add(Context context, Request<T> request, HttpCallBack<T> callBack, int what, boolean isShowDialog, boolean isCanCancel, Class<?> classBean) {
		
		request.setReadTimeout(10000);
		request.setConnectTimeout(10000);
		
		//判断是否有网络连接
		if(Utils.isNetworkConnected(context)){
			queue.add(what, request, new ResponseListener<T>(request, context, callBack, isShowDialog, isCanCancel, classBean));
		}else{
			Toast.show(R.string.log_check_network);
			return;
		}
		
	}
	
	public <T> void add(Context context, Request<T> request, HttpCallBack<T> callBack, int what, boolean isShowDialog, boolean isCanCancel) {
			queue.add(what, request, new ResponseListener<T>(request, context, callBack, isShowDialog, isCanCancel));
		
	}
	
	
	
	/**
     * 取消这个sign标记的所有请求.
     */
    public void cancelBySign(Object sign) {
        queue.cancelBySign(sign);
    }

    /**
     * 取消队列中所有请求.
     */
    public void cancelAll() {
        queue.cancelAll();
    }

    /**
     * 退出app时停止所有请求.
     */
    public void stopAll() {
        queue.stop();
    }
}
