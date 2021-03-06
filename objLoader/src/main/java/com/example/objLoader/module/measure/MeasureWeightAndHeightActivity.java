package com.example.objLoader.module.measure;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.DetailMeasureSizeActivity;
import com.example.objLoader.present.MeasurePresent;
import com.example.objLoader.wedgit.wheelpicker.WheelPicker;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class MeasureWeightAndHeightActivity extends BaseActivity implements View.OnClickListener, WheelPicker.OnWheelChangeListener {

	@Bind(R.id.picker_height)
	WheelPicker pickerHeight;
	@Bind(R.id.picker_weight)
	WheelPicker pickerWeight;
	@Bind(R.id.tv_height)
	TextView tvHeight;
	@Bind(R.id.tv_weight)
	TextView tvWeight;

	private ArrayList<String> heightList = new ArrayList<>();
	private ArrayList<String> weightList = new ArrayList<>();
	private MeasurePresent measurePresent;

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
		pickerHeight.setOnWheelChangeListener(this);
		pickerWeight.setOnWheelChangeListener(this);

		tvHeight.setText(String.valueOf(pickerHeight.getData().get(pickerHeight.getCurrentItemPosition())));
		tvWeight.setText(String.valueOf(pickerWeight.getData().get(pickerWeight.getCurrentItemPosition())));
	}

	@Override
	protected BasePresenter initPresenter() {
		measurePresent = new MeasurePresent();
		return measurePresent;
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

			measurePresent.measure();
		}
	}

	@Override
	public void onWheelScrolled(int offset) {

	}

	@Override
	public void onWheelSelected(int position) {
		tvHeight.setText(String.valueOf(pickerHeight.getData().get(pickerHeight.getCurrentItemPosition())));
		tvWeight.setText(String.valueOf(pickerWeight.getData().get(pickerWeight.getCurrentItemPosition())));
	}

	@Override
	public void onWheelScrollStateChanged(int state) {

	}
}
