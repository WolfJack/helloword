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
public class MyDragViewScroll extends View {

    private Paint mPaint;
    private int mLastX;
    private int mLastY;
    private int x;
    private int y;

    public MyDragViewScroll(Context context) {
        this(context,null);
    }

    public MyDragViewScroll(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyDragViewScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,300,300,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mLastX = (int) event.getX();
        mLastY = (int) event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = mLastX;
                y = mLastY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX=mLastX-x;
                int offsetY=mLastY-y;
                ((View)getParent()).scrollBy(-offsetX,-offsetY);
//                ((View)getParent()).scrollTo(-mLastX,-mLastY);
                break;
        }
        return true;
    }
}
