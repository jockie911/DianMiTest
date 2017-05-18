package com.example.objLoader.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.example.objLoader.R;
import com.example.objLoader.global.BaseApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2017/5/15.
 */

public class ShapeView extends View {

    private boolean isFront;    //默认是正面，控制正面形状和侧面
    private boolean isMale; //默认是男生，控制颜色
    private Paint paint;
    private static final String malePiantColor = "#007DB8";
    private static final String femalePiantColor = "#EE6AA7";


    private List<Point> mPoints = new ArrayList<>();
    /** 中点集合 */
    private List<Point> mMidPoints = new ArrayList<>();
    /** 中点的中点集合 */
    private List<Point> mMidMidPoints = new ArrayList<>();
    /** 移动后的点集合(控制点) */
    private List<Point> mControlPoints = new ArrayList<>();

    double[][] mData = {
            {120.869957,102.379746},
            {111.207393,107.273025},
            {100.364058,111.078777},
            {87.906377,136.755023},
            {76.296341,157.971896},
            {65.430231,175.970934},
            {53.670878,196.425984},
            {61.039432,201.988525},
            {75.013216,184.019485},
            {86.685386,168.025765},
            {96.681971,152.327153},
            {103.073481,133.320700},
            {104.788193,140.631116},
            {105.856955,147.282798},
            {106.818223,154.166866},
            {107.679530,162.301742},
            {107.668003,170.152821},
            {106.548908,179.031579},
            {104.017897,188.155197},
            {102.008466,197.083018},
            {100.531317,207.015405},
            {99.252865,217.352653},
            {98.685642,247.606527},
            {101.698235,271.342061},
            {101.228494,289.214407},
            {98.649323,310.986154},
            {100.013202,333.117870},
            {101.519862,356.161592},
            {98.773019,367.243311},
            {93.315194,379.779998},
            {104.760854,385.552913},
            {111.150115,372.074479},
            {112.950790,356.464844},
            {114.222105,333.325370},
            {117.436096,311.124927},
            {120.706064,290.894877},
            {123.711276,271.620293},
            {127.423983,247.812045},
            {131.056044,231.540412},
            {135.244015,247.686650},
            {139.422747,271.275218},
            {142.948919,290.577757},
            {146.329082,310.784353},
            {149.687736,332.881119},
            {151.203957,356.731874},
            {152.266440,372.183488},
            {157.849637,385.800988},
            {170.203120,379.559927},
            {164.751322,367.097103},
            {162.704165,356.534182},
            {164.117030,332.626856},
            {165.253817,310.233735},
            {162.328234,289.021851},
            {161.635303,270.494428},
            {163.830351,246.493038},
            {162.705986,215.870855},
            {161.464495,206.021319},
            {159.549272,196.019508},
            {157.844497,186.618197},
            {155.380669,177.290749},
            {154.539587,169.122427},
            {154.746377,161.452103},
            {155.669230,153.386345},
            {156.870118,146.026464},
            {157.875782,138.488419},
            {159.708714,131.559104},
            {166.631541,151.877362},
            {177.378312,166.968358},
            {188.972345,182.801770},
            {202.778071,199.627152},
            {210.332896,193.922731},
            {198.885989,174.974561},
            {187.601965,156.832678},
            {175.311435,136.411098},
            {160.945232,110.267928},
            {151.007693,107.022113},
            {141.276790,101.897757},
            {140.520003,93.301987},
            {145.840646,81.813872},
            {146.509375,63.422189},
            {130.170902,49.390062},
            {114.024831,64.175660},
            {115.292336,81.056035},
            {121.350124,94.175973}};

    public ShapeView(Context context) {
        super(context);
        init();
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
        isFront = a.getBoolean(R.styleable.ShapeView_is_front, true);
        isMale = a.getBoolean(R.styleable.ShapeView_is_male, true);
        a.recycle();
        init();
    }

    private void init() {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.parseColor(femalePiantColor));
        this.paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        initPoints();
        initMidPoints(this.mPoints);
        initMidMidPoints(this.mMidPoints);
        initControlPoints(this.mPoints, this.mMidPoints , this.mMidMidPoints);

    }

    private void initControlPoints(List<Point> points, List<Point> midPoints, List<Point> midMidPoints) {
        for (int i = 0; i < points.size(); i ++){
            if (i ==0 || i == points.size()-1){
                continue;
            }else{
                Point before = new Point();
                Point after = new Point();
                before.x = points.get(i).x - midMidPoints.get(i - 1).x + midPoints.get(i - 1).x;
                before.y = points.get(i).y - midMidPoints.get(i - 1).y + midPoints.get(i - 1).y;
                after.x = points.get(i).x - midMidPoints.get(i - 1).x + midPoints.get(i).x;
                after.y = points.get(i).y - midMidPoints.get(i - 1).y + midPoints.get(i).y;
                mControlPoints.add(before);
                mControlPoints.add(after);
            }
        }
    }

    private void initMidMidPoints(List<Point> mMidPoints) {
        Point point = new Point();
        for (int i = 0;i < mMidPoints.size();i++){
            if(i == mMidPoints.size() - 1){
                point.set((mMidPoints.get(mMidPoints.size()-1).x + mMidPoints.get(0).x)/2,(mMidPoints.get(mMidPoints.size()-1).y + mMidPoints.get(0).y)/2);
            }else{
                point.set((mMidPoints.get(i).x + mMidPoints.get(i+1).x)/2,(mMidPoints.get(i).y + mMidPoints.get(i+1).y)/2);
            }
            mMidMidPoints.add(point);
        }
    }

    private void initMidPoints(List<Point> mPoints) {
        Point point = new Point();
        for (int i = 0;i < mPoints.size();i++){
            if(i == mPoints.size() - 1){
                point.set((mPoints.get(mPoints.size()-1).x + mPoints.get(0).x)/2,(mPoints.get(mPoints.size()-1).y + mPoints.get(0).y)/2);
            }else{
                point.set((mPoints.get(i).x + mPoints.get(i+1).x)/2,(mPoints.get(i).y + mPoints.get(i+1).y)/2);
            }
            mMidPoints.add(point);
        }
    }

    private void initPoints() {
        for (double[] data : mData) {
            mPoints.add(new Point((int)(data[0] * 3.5) ,(int)(data[1]* 3.5)));
        }
    }

    public void setIsMale(boolean isMale){
        this.isMale = isMale;
        if(paint != null){
            paint.setColor(Color.parseColor(isMale ? malePiantColor : femalePiantColor));
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path mPath = new Path();
        for (int i = 0; i < mPoints.size(); i++){
            if (i == 0){// 第一条为二阶贝塞尔
                mPath.moveTo(mPoints.get(i).x, mPoints.get(i).y);// 起点
                mPath.quadTo(mControlPoints.get(i).x, mControlPoints.get(i).y,// 控制点
                        mPoints.get(i + 1).x,mPoints.get(i + 1).y);
            }else if(i < mPoints.size() - 2){// 三阶贝塞尔
                mPath.cubicTo(mControlPoints.get(2*i-1).x,mControlPoints.get(2*i-1).y,// 控制点
                        mControlPoints.get(2*i).x,mControlPoints.get(2*i).y,// 控制点
                        mPoints.get(i+1).x,mPoints.get(i+1).y);// 终点
            }else if(i == mPoints.size() - 2){// 最后一条为二阶贝塞尔
                mPath.moveTo(mPoints.get(i).x, mPoints.get(i).y);// 起点
                mPath.quadTo(mControlPoints.get(mControlPoints.size()-1).x,mControlPoints.get(mControlPoints.size()-1).y,
                        mPoints.get(i+1).x,mPoints.get(i+1).y);// 终点
            }
        }
        canvas.drawPath(mPath,paint);
    }
}
