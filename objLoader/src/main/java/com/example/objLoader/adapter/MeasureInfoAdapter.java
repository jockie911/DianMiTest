package com.example.objLoader.adapter;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.bean.MeasureRecordBean;

import java.util.List;

public class MeasureInfoAdapter extends CommonAdapter<MeasureRecordBean.DataBean.InfoBean> {

	@SuppressWarnings("unused")
	private TextView tv_size_id,tv_size_name,tv_size_values;
	private RelativeLayout rl_item_measure_info;
	
	public MeasureInfoAdapter(Context context, List<MeasureRecordBean.DataBean.InfoBean> datas, int layoutId) {
		super(context, datas, layoutId);
	}

	@Override
	public void convert(int position,ViewHolder holder, MeasureRecordBean.DataBean.InfoBean info) {
		
		rl_item_measure_info = holder.getView(R.id.rl_item_measure_info);
		rl_item_measure_info.setBackgroundColor(mContext.getResources().getColor(R.color.color_base));
		rl_item_measure_info.setBackgroundColor(position % 2 == 0 ? mContext.getResources().getColor(R.color.color_base_deep) :
				mContext.getResources().getColor(R.color.color_base));

		(tv_size_id = holder.getView(R.id.tv_size_id)).setText((position+1) + "");
		(tv_size_name = holder.getView(R.id.tv_size_name)).setText(info.getName());
		(tv_size_values = holder.getView(R.id.tv_size_values)).setText(info.getValue());
	}

}
