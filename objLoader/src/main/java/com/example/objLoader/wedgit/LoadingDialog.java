package com.example.objLoader.wedgit;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.utils.DensityUtil;
import com.wang.avi.AVLoadingIndicatorView;

import rx.Observable;

/**
 * Created by yc on 2017/5/31.
 */

public class LoadingDialog {

    private Display display;
    private  Context context;
    private Dialog dialog;
    private AVLoadingIndicatorView loadingView;
    private FrameLayout llRoot;
    private TextView tvMsg;

    public LoadingDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();

    }

    public LoadingDialog builder(){
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_loading_dialog, null);
        llRoot = (FrameLayout) view.findViewById(R.id.ll_root);

        loadingView = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        tvMsg = (TextView) view.findViewById(R.id.tv_msg);

        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        llRoot.setLayoutParams(new FrameLayout.LayoutParams(DensityUtil.dip2px(context,120),DensityUtil.dip2px(context,120)));
        return this;
    }

    public LoadingDialog setCancelable(boolean cancelable){
        dialog.setCancelable(cancelable);
        return this;
    }

    public void show(){
        setLayout();
        dialog.show();
    }

    private void setLayout() {
        
    }

    public LoadingDialog setMsg(String msg){
        tvMsg.setVisibility(View.VISIBLE);
        tvMsg.setText(msg);
        return this;
    }

    public void dismiss(){
        if(dialog != null)
            dialog.dismiss();
    }

    public void setOnCancelListener(final OnCancelListener listener){
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                listener.onCancel();
                dialog.cancel();
            }
        });
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public interface OnCancelListener{
        void onCancel();
    }
}
