package com.example.objLoader.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<T> datas;
	protected LayoutInflater mInflater;
	protected int layoutId;

	public CommonAdapter(Context context, List<T> datas, int layoutId) {
		mInflater = LayoutInflater.from(context);
		this.layoutId = layoutId;
		this.mContext = context;
		this.datas = datas;
	}

	public void addData(List<T> datas){
		this.datas = datas;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public T getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder(mContext, parent,
				layoutId, position);
		convert(position,holder, getItem(position));
		return holder.getConvertView();
	}

	public abstract void convert(int position,ViewHolder holder, T t);

}
