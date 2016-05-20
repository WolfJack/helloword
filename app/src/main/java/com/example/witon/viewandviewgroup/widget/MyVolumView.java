package com.example.witon.viewandviewgroup.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by witon on 2016/5/17.
 */
public class MyVolumView extends View {

    private int measureSize;
    private int mode;
    private Paint mPaint;
    private int mRectCount;
    private LinearGradient mLinearGradient;
    private int mRectWidth;
    private int mRectHeight;
    private int mWidth;
    private int offset=5;
    private double random;

    public MyVolumView(Context context) {
        this(context,null);
    }

    public MyVolumView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyVolumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mRectCount = 12;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(widthMeasureSpec),measureSize(heightMeasureSpec));
    }

    private int measureSize(int measureSpec){
        int result=0;
        measureSize = MeasureSpec.getSize(measureSpec);
        mode = MeasureSpec.getMode(measureSpec);
        if(mode==MeasureSpec.EXACTLY){
            result=measureSize;
        }else{
            result=200;
            if(mode==MeasureSpec.AT_MOST){
                result=Math.min(result,measureSize);
            }
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int)(mWidth *0.6/mRectCount);
        mLinearGradient = new LinearGradient(0,0, mRectWidth, mRectHeight, Color.YELLOW,Color.BLUE, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<mRectCount;i++){
            random = Math.random();
            float currentHeight=(float)(mRectHeight* random);
            canvas.drawRect((float)(mWidth*0.4/2+mRectWidth*i+offset),currentHeight,(float)(mWidth*0.4/2+mRectWidth*(i+1)),mRectHeight,mPaint);
        }
        postInvalidateDelayed(300);

    }
}
