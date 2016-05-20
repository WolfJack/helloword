package com.example.witon.viewandviewgroup.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by witon on 2016/5/16.
 */
public class MyArcShow extends View {

    private int mMeasureWidth;
    private int mMeasureHeight;
    private int lengths;
    private int mCircleXY;
    private float mRadius;
    private RectF mArcRectF;
    private Paint mCirclePaint;

    private float mSweepValue=66;
    private float mSweepAngle;
    private Paint mArcPaint;

    private String mShowText;
    private Paint mTextPaint;
    private int mode;

    public MyArcShow(Context context) {
        this(context,null);
    }

    public MyArcShow(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyArcShow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
//        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureSize(widthMeasureSpec),measureSize(mMeasureHeight));
        initView(widthMeasureSpec, heightMeasureSpec);
    }

    private int measureSize(int measureSpec){
        int length=0;
        mMeasureWidth = MeasureSpec.getSize(measureSpec);
        mode = MeasureSpec.getMode(measureSpec);
        if(mode==MeasureSpec.EXACTLY){
            length=mMeasureWidth;
        }else{
            length=200;
            if(mode==MeasureSpec.AT_MOST){
                length=Math.min(length,mMeasureWidth);
            }
        }
        return length;
    }

    private void initView(int widthMeasureSpec, int heightMeasureSpec){
        if(getMeasuredWidth()>getMeasuredHeight()){
            lengths = getMeasuredHeight();
        }else{
            lengths=getMeasuredWidth();
        }


        mCircleXY = lengths/2;
        mRadius = (float)(lengths*0.5/2);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(android.R.color.holo_blue_light));

        mArcRectF = new RectF((float)(lengths*0.1),(float)(lengths*0.1),(float)(lengths*0.9),(float)(lengths*0.9));
        mSweepAngle = (mSweepValue/100f)*360f;

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mArcPaint.setStrokeWidth((float)(lengths*0.1));
        mArcPaint.setStyle(Paint.Style.STROKE);

        setmShowText(mShowText);
        mTextPaint = new Paint();
        mTextPaint.setTextSize(getShowTextSize());
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setmSweepValue(float mSweepValue){
        if(mSweepValue!=0){
            this.mSweepValue=mSweepValue;
        }else{
            this.mSweepValue=25;
        }
        this.invalidate();//重绘
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆
        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,mCirclePaint);
        //绘制弧线
        canvas.drawArc(mArcRectF,270,mSweepAngle,false,mArcPaint);
        //绘制文字
        canvas.drawText(mShowText,0,mShowText.length(),mCircleXY,mCircleXY+(getShowTextSize()/4),mTextPaint);
    }

    public void setmShowText(String str){
        if(TextUtils.isEmpty(str)){
            mShowText="Android";
        }else{
            mShowText=str;
        }
        this.invalidate();
    }

    private int getShowTextSize(){
        return 50;
    }

}
