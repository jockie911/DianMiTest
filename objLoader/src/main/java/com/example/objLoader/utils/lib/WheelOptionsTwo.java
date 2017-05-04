package com.example.objLoader.utils.lib;

import android.text.format.Time;
import android.view.View;


import java.util.ArrayList;

import com.example.objLoader.R;


public class WheelOptionsTwo {
    private View view;
    private WheelView wv_option1;
    private WheelView wv_option2;
    private WheelView wv_option3;
    private WheelView wv_option4;
    private WheelView wv_option5;
    private ArrayList<String> mOptions1Items;
    private ArrayList<String> mOptions2Items;
    private ArrayList<String> mOptions3Items;
    private ArrayList<String> mOptions4Items;
    private ArrayList<String> mOptions5Items;
    public int screenheight;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public WheelOptionsTwo(View view) {
        super();
        this.view = view;
        setView(view);
    }

    public void setPicker(ArrayList<String> optionsItems) {
        setPicker(optionsItems, null, null, null, null, false);
    }

    public void setPicker(ArrayList<String> options1Items,
                          ArrayList<String> options2Items, boolean linkage) {
        setPicker(options1Items, options2Items, null, null, null, linkage);
    }

    public void setPicker(ArrayList<String> options1Items,
                          ArrayList<String> options2Items, ArrayList<String> options3Items, boolean linkage) {
        setPicker(options1Items, options2Items, options3Items, null, null, linkage);
    }

    public void setPicker(ArrayList<String> options1Items,
                          ArrayList<String> options2Items, ArrayList<String> options3Items, ArrayList<String> options4Items, boolean linkage) {
        setPicker(options1Items, options2Items, options3Items, options4Items, null, linkage);
    }


    public void setPicker(ArrayList<String> options1Items,
                          ArrayList<String> options2Items,
                          ArrayList<String> options3Items,
                          ArrayList<String> options4Items,
                          ArrayList<String> options5Items,
                          boolean linkage) {
        this.mOptions1Items = options1Items;
        this.mOptions2Items = options2Items;
        this.mOptions3Items = options3Items;
        this.mOptions4Items = options4Items;
        this.mOptions5Items = options5Items;
        int len = ArrayWheelAdapter.DEFAULT_LENGTH;
        if (this.mOptions3Items == null)
            len = 8;
        if (this.mOptions2Items == null)
            len = 12;
        // 选项1
        Time time = new Time();
        Time t = new Time("GMT+8");
        t.setToNow();
        time.setToNow();
        int year = time.year;
        int month = t.month;
        int day = time.monthDay;
        int hour = time.hour;
        int index;
        if (hour <= 22 && hour >= 8) {
            index=hour-8;
        }else {
            index = 0;
        }
        wv_option1 = (WheelView) view.findViewById(R.id.options1);
        wv_option1.setAdapter(new ArrayWheelAdapter(mOptions1Items, len));// 设置显示数据
        wv_option1.setCurrentItem(year - 2000);// 初始化时显示的数据
        // 选项2
        wv_option2 = (WheelView) view.findViewById(R.id.options2);
        wv_option2.setAdapter(new ArrayWheelAdapter(mOptions2Items, len));// 设置显示数据
        wv_option2.setCurrentItem(month);// 初始化时显示的数据
        // 选项3
        wv_option3 = (WheelView) view.findViewById(R.id.options3);
        wv_option3.setAdapter(new ArrayWheelAdapter(mOptions3Items, len));// 设置显示数据
        wv_option3.setCurrentItem(day - 1);// 初始化时显示的数据

        wv_option4 = (WheelView) view.findViewById(R.id.options4);
        wv_option4.setAdapter(new ArrayWheelAdapter(mOptions4Items, len));// 设置显示数据
        wv_option4.setCurrentItem(index);// 初始化时显示的数据

        wv_option5 = (WheelView) view.findViewById(R.id.options5);
        wv_option5.setAdapter(new ArrayWheelAdapter(mOptions5Items, len));// 设置显示数据
        wv_option5.setCurrentItem(index);// 初始化时显示的数据

        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = (screenheight / 100) * 3;

        wv_option1.TEXT_SIZE = textSize;
        wv_option2.TEXT_SIZE = textSize;
        wv_option3.TEXT_SIZE = textSize;
        wv_option4.TEXT_SIZE = textSize;
        wv_option5.TEXT_SIZE = textSize;

        if (this.mOptions2Items == null)
            wv_option2.setVisibility(View.GONE);
        if (this.mOptions3Items == null)
            wv_option3.setVisibility(View.GONE);
        if (this.mOptions4Items == null)
            wv_option4.setVisibility(View.GONE);
        if (this.mOptions5Items == null)
            wv_option5.setVisibility(View.GONE);

        OnWheelChangedListener wheelListener_option1 = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                wv_option1.getCurrentItem();
            }
        };
        OnWheelChangedListener wheelListener_option2 = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                wv_option2.getCurrentItem();
            }
        };
        OnWheelChangedListener wheelListener_option3 = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                wv_option3.getCurrentItem();
            }
        };
        OnWheelChangedListener wheelListener_option4 = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                wv_option4.getCurrentItem();
            }
        };
        OnWheelChangedListener wheelListener_option5 = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                wv_option5.getCurrentItem();
            }
        };
        wv_option1.addChangingListener(wheelListener_option1);
        wv_option2.addChangingListener(wheelListener_option2);
        wv_option3.addChangingListener(wheelListener_option3);
        wv_option4.addChangingListener(wheelListener_option4);
        wv_option5.addChangingListener(wheelListener_option5);
//		// 添加联动监听
//		if (options2Items != null && linkage)
//			wv_option1.addChangingListener(wheelListener_option1);
//		if (options3Items != null && linkage)
//			wv_option2.addChangingListener(wheelListener_option2);
    }

    /**
     * 设置选项的单位
     *
     * @param label1
     * @param label2
     * @param label3
     */
    public void setLabels(String label1, String label2, String label3, String label4, String label5) {
        if (label1 != null)
            wv_option1.setLabel(label1);
        if (label2 != null)
            wv_option2.setLabel(label2);
        if (label3 != null)
            wv_option3.setLabel(label3);
        if (label4 != null)
            wv_option4.setLabel(label4);
        if (label5 != null)
            wv_option5.setLabel(label5);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        wv_option1.setCyclic(cyclic);
        wv_option2.setCyclic(cyclic);
        wv_option3.setCyclic(cyclic);
        wv_option4.setCyclic(cyclic);
        wv_option5.setCyclic(cyclic);
    }

    /**
     * 返回当前选中的结果对应的位置数组 因为支持三级联动效果，分三个级别索引，0，1，2
     *
     * @return
     */
    public int[] getCurrentItems() {
        int[] currentItems = new int[5];
        currentItems[0] = wv_option1.getCurrentItem();
        currentItems[1] = wv_option2.getCurrentItem();
        currentItems[2] = wv_option3.getCurrentItem();
        currentItems[3] = wv_option4.getCurrentItem();
        currentItems[4] = wv_option5.getCurrentItem();
        return currentItems;
    }

    public void setCurrentItems(int option1, int option2, int option3, int option4, int option5) {
        wv_option1.setCurrentItem(option1);
        wv_option2.setCurrentItem(option2);
        wv_option3.setCurrentItem(option3);
        wv_option4.setCurrentItem(option4);
        wv_option5.setCurrentItem(option5);
    }


}
