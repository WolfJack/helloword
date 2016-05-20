package com.example.witon.viewandviewgroup.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by witon on 2016/5/19.
 */
public class MyDragViewLayoutParams extends View {

    private Paint mPaint;
    private int mLastX;
    private int mLastY;
    private int x;
    private int y;

    public MyDragViewLayoutParams(Context context) {
        this(context,null);
    }

    public MyDragViewLayoutParams(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyDragViewLayoutParams(Context context, AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawRect(0,0,200,200,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getRawX();
        y = (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX=x-mLastX;
                int offsetY=y-mLastY;
                RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin=getLeft()+offsetX;
                layoutParams.topMargin=getTop()+offsetY;
                setLayoutParams(layoutParams);
                mLastX=x;
                mLastY=y;
                break;
        }
        return true;
    }
}
