package com.example.objLoader.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder {

	private SparseArray<View> mView;
	@SuppressWarnings("unused")
	private int mPosition;
	private View mConvertView;

	public ViewHolder(Context context, ViewGroup parent, int layoutId,
			int position) {

		this.mView = new SparseArray<View>();
		this.mPosition = position;
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId,
				parent, false);
		mConvertView.setTag(this);
	}

	public static ViewHolder get(Context context, View convertView,
			ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}

	/**
	 * 为TextView设置文本
	 * 
	 * @param viewId
	 * @param text
	 */
	public ViewHolder setText(int viewId, String text) {

		TextView tv = getView(viewId);
		tv.setText(text);

		return this;
	}

	/**
	 * 通过viewId获取控件
	 * 
	 * @param viewId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {

		View view = mView.get(viewId);

		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mView.put(viewId, view);

		}
		return (T) view;

	}

	/**
	 * 返回convertView
	 * 
	 * @return
	 */
	public View getConvertView() {
		return mConvertView;
	}

}
