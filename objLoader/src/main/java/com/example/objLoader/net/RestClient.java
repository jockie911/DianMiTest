package com.example.objLoader.net;

import com.example.objLoader.bean.BaseHttpBean;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.bean.Temp;
import com.example.objLoader.bean.WXUserInfoBean;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.net.api.Api;
import com.example.objLoader.utils.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by yc on 2017/5/25.
 */

public class RestClient<T> {

    private static final String TAG = "RestClient";

    private static volatile RestClient sInstance;
    private Api mApi;

    private RestClient() {
    }

    public static RestClient instance() {
        if (sInstance == null) {
            synchronized (RestClient.class) {
                if (sInstance == null) {
                    sInstance = new RestClient();
                }
            }
        }
        return sInstance;
    }

    private Api api() {
        if (mApi == null) {
            synchronized (Api.class) {
                mApi = create();
            }
        }
        return mApi;
    }

    private Api create() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
//                HttpUrl url = request.url().newBuilder()
//                        .addQueryParameter("package_name", App.sInstance.getPackageName())
//                        .addQueryParameter("version_code", String.valueOf(Utils.getVersionCode(App.sInstance)))
//                        .addQueryParameter("version_name", String.valueOf(Utils.getVersionName(App.sInstance)))
//                        .addQueryParameter("channel", String.valueOf(Utils.getUmengChannel(App.sInstance)))
//                        .build();
//                request = request.newBuilder().url(url).build();
                request = request.newBuilder().build();

                Logger.d(TAG, request.url().toString());

                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVER)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        return api;
    }


    public Observable<Temp> wxLogin(String url){
        return api().wxLogin(url);
    }

    public Observable<WXUserInfoBean> wxUserInfo(String url,String token,String opendi){
        return api().wxUserInfo(url,token,opendi);
    }

    public Observable<WXUserInfoBean> checkWXLogin(String url,
                                                   String uid,
                                                   String name,
                                                   String iconurl,
                                                   int gender,
                                                   int login_type,
                                                   String mobile){
        return api().checkWXLogin(url,uid,name,iconurl,gender,login_type,mobile);
    }

    public Observable<BaseRequestBean> changeUserPwd(String mobile, String oldpwd, String newpwd, String md5) {
        return api().changeUserPwd(mobile,oldpwd,newpwd,md5);
    }

   /* public Observable<BaseHttpBean<T>> get(String url, Map<String,String> params){
        return api().get(url,params);
    }

    public Observable<BaseHttpBean<T>> post(String url, Map<String,String> params){
        return api().post(url,params);
    }*/

    public Observable<BaseRequestBean> get(String url, Map<String,String> params){
        return api().get(url,params);
    }

    public Observable<BaseRequestBean> post(String url, Map<String,String> params){
        return api().post(url,params);
    }

    public Observable<MeasureRecordBean> getRecordMeasureData(String url, String mobile, String md5) {
        return api().getRecordMeasureData(url, mobile, md5);
    }

    public Observable<BaseRequestBean> deleteMyMeasureRecord(String url, String mobile, String recid, String md5) {
        return api().deleteMyMeasureRecord(url,mobile,recid,md5);
    }
}
