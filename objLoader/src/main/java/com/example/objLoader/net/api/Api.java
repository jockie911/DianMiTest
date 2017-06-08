package com.example.objLoader.net.api;

import com.example.objLoader.bean.BaseHttpBean;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.bean.Temp;
import com.example.objLoader.bean.WXUserInfoBean;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by yc on 2017/5/25.
 */

public interface Api<T> {

    @GET
    Observable<Temp> wxLogin(@Url String url);

    @GET
    Observable<WXUserInfoBean> wxUserInfo(@Url String url,@Query("access_token") String token,@Query("openid") String openid);

    @FormUrlEncoded
    @POST
    Observable<MeasureRecordBean> getRecordMeasureData(@Url String url,
                                                       @Field(IConstant.MOBILE) String mobile,
                                                       @Field(IConstant.STRING) String md5);

    @FormUrlEncoded
    @POST
    Observable<WXUserInfoBean> checkWXLogin(@Url String url,
                                            @Field("uid") String uid,
                                            @Field("name") String name,
                                            @Field("iconurl") String iconurl,
                                            @Field("gender") int gender,
                                            @Field("login_type") int login_type,
                                            @Field("mobile") String mobile);
    @FormUrlEncoded
    @POST(Constants.CHANGE_PWD)
    Observable<BaseRequestBean> changeUserPwd(@Field(IConstant.MOBILE) String mobile,
                                              @Field((IConstant.PASSWORD)) String oldpwd,
                                              @Field(IConstant.PASSWORD_NEW) String newpwd,
                                              @Field(IConstant.STRING) String md5);

    @FormUrlEncoded
    @POST
    Observable<BaseRequestBean> deleteMyMeasureRecord(@Url String url,
                                                      @Field((IConstant.MOBILE))String mobile,
                                                      @Field((IConstant.RECID))String recid,
                                                      @Field((IConstant.STRING))String md5);

//    @GET()
//    Observable<BaseHttpBean<T>> get(@Url String url, @QueryMap Map<String, String> params/*, @Header("Cache-Time") String time*/);
//
//    @FormUrlEncoded
//    @POST()
//    Observable<BaseHttpBean<T>> post(@Url String url, @FieldMap Map<String, String> params/*, @Header("Cache-Time") String time*/);

    @GET()
    Observable<BaseRequestBean> get(@Url String url, @QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST()
    Observable<BaseRequestBean> post(@Url String url, @FieldMap Map<String, String> params);


}
