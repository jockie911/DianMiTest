package com.example.objLoader.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;


/**
 * 带删除的EditText
 * @author ZhouPeng
 * @date 2015-11-25 PM
 */
@SuppressLint("ClickableViewAccessibility")
public class MyEditText extends EditText implements OnFocusChangeListener, TextWatcher {

	// EditText右侧的删除按钮
	private Drawable mClearDrawable;
	private boolean hasFoucs;

	public MyEditText(Context context) {
		this(context, null);
	}

	public MyEditText(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.editTextStyle);
	}

	public MyEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		// 获取EditText的DrawableRight
//		mClearDrawable = getCompoundDrawables()[2];
//		if (mClearDrawable == null) {
//			mClearDrawable = getResources().getDrawable(R.drawable.classify_delete);
//		}
//
//		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
		// 默认设置隐藏图标
		setClearIconVisible(false);
		// 设置焦点改变的监听
		setOnFocusChangeListener(this);
		// 设置输入框里面内容发生改变的监听
		addTextChangedListener(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {
				int x = (int) event.getX();
				int y = (int) event.getY();
				Rect rect = getCompoundDrawables()[2].getBounds();
				int height = rect.height();
				int distance = (getHeight() - height) / 2;
				boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
				boolean isInnerHeight = y > distance && y < (distance + height);
				if (isInnerWidth && isInnerHeight) {
					this.setText("");
				}
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 当ClearEditText焦点发生变化的时候， 输入长度为零，隐藏删除图标，否则，显示删除图标
	 */
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		this.hasFoucs = hasFocus;
		if (hasFocus) {
			setClearIconVisible(getText().length() > 0);
		} else {
			setClearIconVisible(false);
		}
	}

	protected void setClearIconVisible(boolean visible) {
		Drawable right = visible ? mClearDrawable : null;
		setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int count, int after) {
		if (hasFoucs) {
			setClearIconVisible(s.length() > 0);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void afterTextChanged(Editable s) {
	}

}
