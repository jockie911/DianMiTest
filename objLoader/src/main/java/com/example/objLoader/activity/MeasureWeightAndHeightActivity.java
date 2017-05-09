package com.example.objLoader.activity;

import android.content.Intent;
import android.view.View;

import com.example.objLoader.R;
import com.example.objLoader.activity.mywork.DetailMeasureSizeActivity;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.wedgit.wheelpicker.WheelPicker;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class MeasureWeightAndHeightActivity extends BaseActivity implements View.OnClickListener{

	@Bind(R.id.picker_height)
	WheelPicker pickerHeight;
	@Bind(R.id.picker_weight)
	WheelPicker pickerWeight;

	private ArrayList<String> heightList = new ArrayList<>();
	private ArrayList<String> weightList = new ArrayList<>();

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_measure;
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	protected void initData() {
		tvTitle.setText(R.string.measure_title);

		for (int i = 45; i < 91; i++) {
			weightList.add(i + "");
		}
		for (int i = 140; i < 201; i++) {
			heightList.add(i + "");
		}
		pickerHeight.setData(heightList);
		pickerWeight.setData(weightList);
		pickerHeight.setSelectedItemPosition(30);
		pickerWeight.setSelectedItemPosition(10);
	}

	@Override
	@OnClick({R.id.tv_next_step})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		if(v.getId() == R.id.tv_next_step){
			String height = heightList.get(pickerHeight.getCurrentItemPosition());
			String weight = weightList.get(pickerWeight.getCurrentItemPosition());

			Intent intent = new Intent(MeasureWeightAndHeightActivity.this, DetailMeasureSizeActivity.class);
			intent.putExtra(IConstant.IS_SAVE,true);
			MeasureRecordBean.DataBean dataBean = null;
			intent.putExtra(IConstant.ITEM_DATA,dataBean);
			startActivity(intent);

		}
	}
}
