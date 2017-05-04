package com.example.objLoader.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.bean.MeasureInfo.Info;

public class MeasureInfoAdapter extends CommonAdapter<Info> {

	
	@SuppressWarnings("unused")
	private TextView tv_size_id,tv_size_name,tv_size_values;
	private RelativeLayout rl_item_measure_info;
	
	public MeasureInfoAdapter(Context context, List<Info> datas, int layoutId) {
		super(context, datas, layoutId);
		
	}

	@Override
	public void convert(int position,ViewHolder holder, Info info) {
		
		rl_item_measure_info = holder.getView(R.id.rl_item_measure_info);
		
		if(position % 2 ==1){
			rl_item_measure_info.setBackgroundColor(Color.parseColor("#c8d5de"));
		}
		
		(tv_size_id = holder.getView(R.id.tv_size_id)).setText((position+1) + "");
		(tv_size_name = holder.getView(R.id.tv_size_name)).setText(info.getName());
		(tv_size_values = holder.getView(R.id.tv_size_values)).setText(info.getValue());
		
		
	}

}
