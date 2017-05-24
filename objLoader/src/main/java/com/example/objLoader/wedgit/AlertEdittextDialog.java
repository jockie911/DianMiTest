package com.example.objLoader.wedgit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseApp;


public class AlertEdittextDialog implements TextWatcher, DialogInterface.OnDismissListener {
	private Activity context;
	private Dialog dialog;
	private LinearLayout lLayout_bg;
	private TextView txt_title;
	private EditText etMsg;
	private Button btn_neg;
	private Button btn_pos;
	private ImageView img_line;
	private Display display;
	private boolean showTitle = false;
	private boolean showMsg = false;
	private boolean showPosBtn = false;
	private boolean showNegBtn = false;

	public AlertEdittextDialog(Activity context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public AlertEdittextDialog builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(
				R.layout.view_alertetdialog, null);
		// 获取自定义Dialog布局中的控件
		lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
		txt_title = (TextView) view.findViewById(R.id.txt_title);
		txt_title.setVisibility(View.GONE);
		etMsg = (EditText) view.findViewById(R.id.txt_msg);
		etMsg.setFocusable(true);
//		etMsg.setVisibility(View.GONE);
		etMsg.addTextChangedListener(this);
		btn_neg = (Button) view.findViewById(R.id.btn_neg);
		btn_neg.setVisibility(View.GONE);
		btn_pos = (Button) view.findViewById(R.id.btn_pos);
		btn_pos.setVisibility(View.GONE);
		btn_pos.setClickable(false);
		btn_pos.setTextColor(BaseApp.getContext().getResources().getColor(R.color.title));

		img_line = (ImageView) view.findViewById(R.id.img_line);
		img_line.setVisibility(View.GONE);

		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.AlertDialogStyle);
		dialog.setContentView(view);

		dialog.setOnDismissListener(this);
		// 调整dialog背景大小
		lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
				.getWidth() * 0.75), LayoutParams.WRAP_CONTENT));
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm != null)
		 	imm.showSoftInput(etMsg, InputMethodManager.SHOW_FORCED);
		return this;
	}

	public AlertEdittextDialog setTitle(String title) {
		showTitle = true;
		if ("".equals(title)) {
			txt_title.setText("标题");
		} else {
			txt_title.setText(title);
		}
		return this;
	}

	public AlertEdittextDialog setTitle(int resTitleID) {
		showTitle = true;
		txt_title.setText(resTitleID);
		return this;
	}

	public AlertEdittextDialog setMsg(String msg) {
		showMsg = true;
		if ("".equals(msg)) {
			etMsg.setText("内容");
		} else {
			etMsg.setText(msg);
		}
		return this;
	}

	public AlertEdittextDialog setMsg(int resMsgId) {
		showMsg = true;
		etMsg.setText(resMsgId);
		return this;
	}

	public AlertEdittextDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public AlertEdittextDialog setPositiveButton(String text,
                                                 final OnClickListener listener) {
		showPosBtn = true;
		if ("".equals(text)) {
			btn_pos.setText(R.string.ok);
		} else {
			btn_pos.setText(text);
		}
		btn_pos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listener != null)
					listener.onClick(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	public String getEtCentent(){
		return etMsg.getText().toString().trim();
	}

	public AlertEdittextDialog setPositiveButton(int resTextID, OnClickListener listener) {
		return setPositiveButton(context.getString(resTextID),listener);
	}

	public AlertEdittextDialog setNegativeButton(String text,
                                                 final OnClickListener listener) {
		showNegBtn = true;
		if ("".equals(text)) {
			btn_neg.setText(R.string.cancel);
		} else {
			btn_neg.setText(text);
		}
		btn_neg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listener != null)
					listener.onClick(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	public AlertEdittextDialog setNegativeButton(int text, OnClickListener listener) {
		return setNegativeButton(context.getString(text),listener);
	}

	private void setLayout() {
		if (!showTitle && !showMsg) {
			txt_title.setText("提示");
			txt_title.setVisibility(View.VISIBLE);
		}

		if (showTitle) {
			txt_title.setVisibility(View.VISIBLE);
		}

//		if (showMsg) {
//			etMsg.setVisibility(View.VISIBLE);
//		}

		if (!showPosBtn && !showNegBtn) {
			btn_pos.setText("确定");
			btn_pos.setVisibility(View.VISIBLE);
			btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
			btn_pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
		}

		if (showPosBtn && showNegBtn) {
			btn_pos.setVisibility(View.VISIBLE);
			btn_pos.setBackgroundResource(R.drawable.alertdialog_right_selector);
			btn_neg.setVisibility(View.VISIBLE);
			btn_neg.setBackgroundResource(R.drawable.alertdialog_left_selector);
			img_line.setVisibility(View.VISIBLE);
		}

		if (showPosBtn && !showNegBtn) {
			btn_pos.setVisibility(View.VISIBLE);
			btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
		}

		if (!showPosBtn && showNegBtn) {
			btn_neg.setVisibility(View.VISIBLE);
			btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
		}
	}

	public void show() {
		setLayout();
		dialog.show();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		if(TextUtils.isEmpty(s)){
			btn_pos.setClickable(false);
			btn_pos.setTextColor(BaseApp.getContext().getResources().getColor(R.color.title));
		}else{
			btn_pos.setClickable(true);
			btn_pos.setTextColor(BaseApp.getContext().getResources().getColor(R.color.yollow));
		}
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm != null)
			imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
	}
}
