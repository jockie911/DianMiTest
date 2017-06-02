package com.example.objLoader.module.login.imple;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.ToastUtils;
import com.example.objLoader.utils.Utils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

/**
 * Created by yc on 2017/5/22.
 */

public class RegistForgetModelImple implements RegistForgetModel {

    private OnSmsCodeSubmitListerer smsCodeListerer;
    private OnSubmitRequestListener submitListener;
    private OnSmsCheckoutListener checkOutListerer;
    private TextView textView;
    private Timer timer;
    private long timell;
    private Request<String> registerRuquest;
    public static final String REGEX_MOBILE_EXACT  = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";

    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            mHandler.sendMessage(msg);
        }
    };

    public RegistForgetModelImple(){
        SMSSDK.registerEventHandler(eh);
    }

    /**
     * 正则匹配手机号码
     * @param input
     * @return
     */
    public static boolean isMobileExact(CharSequence input) {
        return isMatch(REGEX_MOBILE_EXACT, input);
    }

    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 获取验证码
     * @param phoneNum
     * @param listerer
     */
    @Override
    public void requestSmsCode(String phoneNum, OnSmsCodeSubmitListerer listerer) {
        if (!isMobileExact(phoneNum)) {
            ToastUtils.show(R.string.right_mobile);
            return;
        }
        if (!Utils.isNetworkConnected(BaseApp.getContext())) { // 检查网络连接
            ToastUtils.show(R.string.log_check_network);
            return;
        }
        // 发送验证码
        timerSmsCode(textView);
        this.smsCodeListerer = listerer;
        SMSSDK.getVerificationCode(IConstant.PHONE_AREA_CODE, phoneNum);
    }

    /**
     * 验证验证码
     * @param phoneNum
     * @param smsCode
     * @param listener
     */
    @Override
    public void checkOutSmsCode(String phoneNum, String smsCode, OnSmsCheckoutListener listener) {
        if (!isMobileExact(phoneNum)) {
            ToastUtils.show(R.string.right_mobile);
            return;
        }
        if(smsCode.length() < 4){
            ToastUtils.show(R.string.wrong_format_sms_code);
            return;
        }
        if (!Utils.isNetworkConnected(BaseApp.getContext())) { // 检查网络连接
            ToastUtils.show(R.string.log_check_network);
            return;
        }
        this.checkOutListerer = listener;
        SMSSDK.submitVerificationCode(IConstant.PHONE_AREA_CODE, phoneNum, smsCode);
    }

    @Override
    public void submitRequest(String phoneNum, String smsCode, String pwd,boolean isForgetpsw, OnSubmitRequestListener listener) {
        if (!isMobileExact(phoneNum)) {
            ToastUtils.show(R.string.right_mobile);
            return;
        }
        if(smsCode.length() < 4){
            ToastUtils.show(R.string.wrong_format_sms_code);
            return;
        }
        if(pwd.length() < 0){
            ToastUtils.show(R.string.pwd_not_none);
            return;
        }
        this.submitListener = listener;
        ToastUtils.show(R.string.auth_code_right);
        registerRuquest = NoHttp.createStringRequest(isForgetpsw ? Constants.FORGET_PWD : Constants.REGISTER, RequestMethod.POST);
        registerRuquest.add(IConstant.MOBILE, phoneNum);
        registerRuquest.add(IConstant.PASSWORD, Utils.MD5(pwd));
        registerRuquest.add(IConstant.STRING, Utils.MD5(phoneNum + Constants.MD5_KEY + Utils.MD5(pwd)));
        CallServer.getInstance().add(BaseApp.getContext(), registerRuquest, callBack,Constants.REGISTER_WHAT, false, false,BaseRequestBean.class);
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if(msg.what == 1212){
                long l = System.currentTimeMillis();
                int t = (int) ((l - timell)/1000);
                if(t >= 60){
                    textView.setText(R.string.input_send_auth_code);
                    textView.setClickable(true);
                    textView.setBackgroundResource(R.drawable.btn_auth_code);
                    textView.setTextColor(BaseApp.getContext().getResources().getColor(R.color.yollow));
                    timer.cancel();
                }else{
                    textView.setText(BaseApp.getContext().getResources().getString(R.string.remain_time)  + (60 - t)+ "");
                }
            }else{
                if (result == SMSSDK.RESULT_COMPLETE) {
                    System.out.println("--------result" + event);
                    // 短信注册成功后，返回MainActivity,然后提示新好友
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        checkOutListerer.onCheckoutSuccess();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 驗證碼發送成功
                        smsCodeListerer.onSmsCodeSuccess();
                    }
                } else {
                    ToastUtils.show(R.string.auth_code_wrong);
                    if(data != null){
                        Throwable throwable = (Throwable) data;
                        try {
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");
                            if (!TextUtils.isEmpty(des)) {
                                ToastUtils.show(des);
                                return;
                            }
                        } catch (Exception e) {
                            SMSLog.getInstance().w(e);
                        }

                    }
                }
            }
        };
    };


    private HttpCallBack<BaseRequestBean> callBack = new HttpCallBack<BaseRequestBean>() {

        public void onSucceed(int what, BaseRequestBean bean) {
            submitListener.onSubmitSuccess(bean);
        };

        public void onFailed(int what, String errorInfo) {
            submitListener.onSubmitFailed(errorInfo);
        };
    };

    public void timerSmsCode(final TextView tv_send_auth_code) {
        tv_send_auth_code.setClickable(false);
        tv_send_auth_code.setBackgroundResource(R.drawable.btn_auth_code_default);
        tv_send_auth_code.setTextColor(Color.parseColor("#99999999"));
        timell = System.currentTimeMillis();
        timer = new Timer();
        timer.schedule(new MyTimerTask(),0,1000);
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void cancel() {
        if(registerRuquest != null)
            registerRuquest.cancel();
    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.sendEmptyMessage(1212);
        }
    }
}
