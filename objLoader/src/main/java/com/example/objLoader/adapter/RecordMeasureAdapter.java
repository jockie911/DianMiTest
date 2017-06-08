package com.example.objLoader.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.bean.MeasureRecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2017/5/9.
 */

public class RecordMeasureAdapter extends CommonAdapter<MeasureRecordBean.DataBean> {

    private boolean isVisible;
    private List<String> deleteIDList; //删除的集合

    public RecordMeasureAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        deleteIDList = new ArrayList<>();
    }


    @Override
    public void convert(final int position, ViewHolder holder, final MeasureRecordBean.DataBean dataBean) {
        TextView tvRecordName = holder.getView(R.id.item_tv_record_name);
        TextView tvRecordGender = holder.getView(R.id.item_tv_gender);
        TextView tvRecordDate = holder.getView(R.id.item_tv_record_date);
        TextView tvRecordSerord = holder.getView(R.id.item_tv_record_secord);
        CheckBox checkBox = holder.getView(R.id.item_cb);
        View bottomView = holder.getView(R.id.item_bottom_view);

        datas.size();
        datas.size();
        tvRecordName.setText(dataBean.getName());
        tvRecordGender.setText(dataBean.getInfo().get(dataBean.getInfo().size() - 1).getValue());

        bottomView.setVisibility(position == datas.size() - 1 ? View.GONE : View.VISIBLE);
        checkBox.setVisibility(isVisible ? View.VISIBLE : View.GONE);

        checkBox.setTag(position);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    deleteIDList.add(dataBean.getId());
                }else{
                    deleteIDList.remove(dataBean.getId());
                }
            }
        });
    }

    /**
     * 让外部有权限设置checkbox的状态
     * @param isVisible
     */
    public void setCheckBoxVisblity(boolean isVisible){
        this.isVisible = isVisible;
        notifyDataSetChanged();
    }

    public List<String> getDeletePosition() {
        return deleteIDList;
    }

    /**
     * 避免操作集合发生并发修改异常
     */
    public void deleteSuccessRefreshData(){
        List<MeasureRecordBean.DataBean> copyDatas = new ArrayList<>();
        for (String s : deleteIDList) {
            for (MeasureRecordBean.DataBean data : datas) {
                if(TextUtils.equals(s, data.getId())){
                    copyDatas.add(data);
                    break;
                }
            }
        }
        deleteIDList.clear();
        if(datas != null)
            datas.removeAll(copyDatas);
        notifyDataSetChanged();
    }
}
