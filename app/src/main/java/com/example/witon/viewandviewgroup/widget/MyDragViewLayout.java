package com.example.witon.viewandviewgroup.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by witon on 2016/5/19.
 */
public class MyDragViewLayout extends View {

    private int mLastX;
    private int mLastY;
    private Paint mPaint;
    private int measureSize;
    private int mode;

    public MyDragViewLayout(Context context) {
        this(context,null);
    }

    public MyDragViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyDragViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint.setStyle(Paint.Style.FILL);

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(measureSize(widthMeasureSpec),measureSize(heightMeasureSpec));
//    }

    private int measureSize(int measureSpec){
        int result=0;
        measureSize = MeasureSpec.getSize(measureSpec);
        mode = MeasureSpec.getMode(measureSpec);
        if(mode ==MeasureSpec.EXACTLY){
            result= measureSize;
        }else{
            result=200;
            if(mode ==MeasureSpec.AT_MOST){
                result=Math.min(result, measureSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,100,100,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX=x-mLastX;
                int offsetY=y-mLastY;
                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
