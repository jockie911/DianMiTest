package com.example.objLoader.present.view;

import com.example.objLoader.base.IBaseView;
import com.example.objLoader.bean.MeasureRecordBean;

import java.util.List;

/**
 * Created by yc on 2017/6/8.
 */

public interface IMeasureRecordView extends IBaseView {

    void setRecordData(List<MeasureRecordBean.DataBean> data);

    List<String> getDeleteRecordList();

    void deleteSuccessRefreshData();
}
