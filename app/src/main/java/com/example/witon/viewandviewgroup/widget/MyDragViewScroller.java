package com.example.witon.viewandviewgroup.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by witon on 2016/5/20.
 */
public class MyDragViewScroller extends View {

    private Scroller mScroller;
    private Paint mPaint;
    private int x;
    private int y;
    private int mLastX;
    private int mLastY;

    public MyDragViewScroller(Context context) {
        this(context,null);
    }

    public MyDragViewScroller(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyDragViewScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mScroller = new Scroller(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,50,50,mPaint);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX=x-mLastX;
                int offsetY=y-mLastY;
                break;
            case MotionEvent.ACTION_UP:
                View viewGroup=((View)getParent());
                mScroller.startScroll(viewGroup.getScrollX(),viewGroup.getScrollY(),-viewGroup.getScrollX(),-viewGroup.getScrollY());
                invalidate();
                break;
        }
        return true;
    }
}
