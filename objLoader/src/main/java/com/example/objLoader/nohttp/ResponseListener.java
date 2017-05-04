package com.example.objLoader.nohttp;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.utils.WaitDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.NotFoundCacheError;
import com.yolanda.nohttp.error.ServerError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import android.content.Context;
import android.content.DialogInterface;

public class ResponseListener<T> implements OnResponseListener<T> {

    private Request<T> mRequest;

    private WaitDialog mDialog;
    private Context context;
    private HttpCallBack<T> callBack;
	private BaseRequestBean bean;
	private Gson gson = new Gson();

    private Class<?> classBean;

    public ResponseListener(Request<T> request, Context context,
                            HttpCallBack<T> callBack, boolean isShowDialog,
                            boolean isCanCancel, Class<?> classBean) {
        this.mRequest = request;
        this.context = context;
        this.callBack = callBack;
        this.classBean = classBean;
        if (context != null && isShowDialog) {
            mDialog = new WaitDialog(context);
            mDialog.setCancelable(isCanCancel);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mRequest.cancel();
                    
                }
            });
        }
    }

    public ResponseListener(Request<T> request, Context context,
                            HttpCallBack<T> callBack, boolean isShowDialog,
                            boolean isCanCancel) {
        this.mRequest = request;
        this.context = context;
        this.callBack = callBack;
        if (context != null && isShowDialog) {
            mDialog = new WaitDialog(context);
            mDialog.setCancelable(isCanCancel);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mRequest.cancel();
                }
            });
        }
    }


    @Override
    public void onStart(int what) {

        if (mDialog != null && !mDialog.isShowing())
            mDialog.show();
    }

    @Override
    public void onSucceed(int what, Response<T> response) {

    	

//    	callBack.onSucceed(what, response);
//    	Logger.i("请求成功：" + response.get().toString());
//    	callBack.onSucceed(what, bean);
    	try {
    		
    		bean = (BaseRequestBean) gson.fromJson(response.get().toString(),classBean);
    		if (callBack != null && bean.iserror.equals("0")) {
    			Logger.d("请求成功:"+response.get().toString());
    			callBack.onSucceed(what, response);
    			callBack.onSucceed(what, bean);
    		}else {
    			Logger.d("请求失败:"+response.get().toString());
    			callBack.onFailed(what);
    			callBack.onFailed(what, bean.info == null ? "" : bean.info);
    		}
			
		} catch (JsonSyntaxException e) {
			
			String errorInfo = context.getString(R.string.timeoutError);
			
			callBack.onFailed(what, errorInfo);
		}
		
		
		
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception,
                         int responseCode, long networkMillis) {
        Logger.i("请求失败：" + "Exception" + exception.toString() + "\n ResponseCode" + responseCode + "");

        callBack.onFailed(what, url, tag, exception, responseCode, networkMillis);

        if (exception instanceof ServerError) {// 服务器错误
            Toast.show(R.string.serverError);
        } else if (exception instanceof NetworkError) {// 网络不好
            Toast.show(R.string.networkError);
        } else if (exception instanceof TimeoutError) {// 请求超时
            Toast.show(R.string.timeoutError);
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            Toast.show(R.string.unKnownHostError);
        } else if (exception instanceof URLError) {// URL是错的
            Toast.show(R.string.uRLError);
        } else if (exception instanceof NotFoundCacheError) {
            Toast.show(R.string.notFoundCacheError);
        } else {
            Toast.show(R.string.error_no);
        }

    }

    @Override
    public void onFinish(int what) {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }

}
