package com.example.objLoader.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by yc on 2017/5/26.
 */

public class GlideUtils {

    /**
     * 统一处理，方便后期 加入加载错误，占位图等等
     * @param context
     * @param obj
     * @param targetImageView
     */
    public static void load(Context context,Object obj,ImageView targetImageView){
        Glide.with(context)
                .load(obj)
                .into(targetImageView);
    }
}
