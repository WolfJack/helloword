package com.example.witon.viewandviewgroup.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by witon on 2016/5/17.
 */
public class MyScrollVier extends ViewGroup {

    private int mScreenHeight;
    private Scroller mScroller;
    private int mLastY;
    private int mStart;
    private int mEnd;

    public MyScrollVier(Context context) {
        this(context,null);
    }

    public MyScrollVier(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollVier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        //获取屏幕高度
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count=getChildCount();
        for(int i=0;i<count;i++){
            View childView=getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount=getChildCount();
        MarginLayoutParams mlp= (MarginLayoutParams) getLayoutParams();
        mlp.height=mScreenHeight*childCount;
        setLayoutParams(mlp);
        for(int i=0;i<childCount;i++){
            View child=getChildAt(i);
            if(child.getVisibility()!=View.GONE){
                child.layout(l,i*mScreenHeight,r,(i+1)*mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       int y= (int) event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                //记录触摸起点
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy=mLastY-y;
                if(getScrollY()<0){
                    dy=0;
                }
                if(getScrollY()>getHeight()-mScreenHeight){
                    dy=0;
                }
                scrollBy(0,dy);
                mLastY=y;
                break;
            case MotionEvent.ACTION_UP:
                //记录触摸终点
                mEnd = getScrollY();
                int dScrollY= mEnd -mStart;
                if(dScrollY>0){//向下滑
                    if(dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,mScreenHeight-dScrollY);
                    }
                }else{//上滑
                    if(-dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-mScreenHeight-dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }
}
