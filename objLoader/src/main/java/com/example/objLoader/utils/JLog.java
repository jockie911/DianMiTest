package com.example.objLoader.utils;

import android.util.Log;

/**
 * Created by jockie on 2016/7/7.
 */
public class JLog {
    private static final int minLevel = 0;
    private static final int verboseLevel = 1;
    private static final int debugLevel = 2;
    private static final int infoLevel = 3;
    private static final int warnLevel = 4;
    private static final int errorLevel = 5;
    private static String TAG = "TAG";

    public static void v(String tag, String msg) {
        if (verboseLevel > minLevel) {
            Log.v(tag, msg);
        }
    }

    public static void v(String msg) {
        v(TAG,msg);
    }

    public static void d(String tag, String msg) {
        if (debugLevel > minLevel) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        d(TAG,msg);
    }

    public static void i(String tag, String msg) {
        if (infoLevel > minLevel) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        i(TAG,msg);
    }

    public static void w(String tag, String msg) {
        if (warnLevel > minLevel) {
            Log.w(tag, msg);
        }
    }

    public static void w(String msg) {
        w(TAG,msg);
    }

    public static void e(String tag, String msg) {
        if (errorLevel > minLevel) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        e(TAG,msg);
    }

    public static void print(String result){
        int maxLogSize = 1000;
        JLog.e("----------------- begin print --------------- ");
        for(int i = 0; i <= result.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > result.length() ? result.length() : end;
            JLog.e(result.substring(start, end));
        }
        JLog.e("----------------- end print --------------- ");
    }
}
