package com.example.witon.viewandviewgroup.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.witon.viewandviewgroup.R;

/**
 * Created by witon on 2016/5/16.
 */
public class MyTopBar extends RelativeLayout {

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;
    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;
    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;

    private topbarClickListener mListener;

    public MyTopBar(Context context) {
        this(context,null);
    }

    public MyTopBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
        addView(context);
        setViewListener();
    }
    private void init(Context context,AttributeSet attrs){
        //通过这个方法，将你在attrs.xml中定义的declare-styleable的所有属性的值存储到TypedArray中
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        //从TypedArray中取出对应的值来为要设置的属性赋值
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor,0);
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = typedArray.getString(R.styleable.TopBar_leftText);

        mRightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor,0);
        mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = typedArray.getString(R.styleable.TopBar_rightText);

        mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize,10);
        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextViewColor,0);
        mTitle = typedArray.getString(R.styleable.TopBar_titleText);

        //获取完TypedArray的值后，一般要调用recyle方法来避免重新创建的时候的错误
        typedArray.recycle();//完成资源回收
    }

    private void addView(Context context){
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        //为创建的组件元素赋值
        //值就来源于我们在引用的xml文件中给对应属性的赋值
        mLeftButton.setTextColor(mLeftTextColor);
//        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setBackgroundDrawable(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
//        mLeftButton.setBackground(mLeftBackground);
        mRightButton.setBackgroundDrawable(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        //为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        //添加到ViewGroup
        addView(mLeftButton,mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        this.addView(mRightButton,mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView,mTitleParams);
    }

    //接口对象，实现回调机制，在回调方法中通过映射的接口对象调用接口中的方法，而不用去考虑如何实现，具体的实现由调用者去创建
    public interface topbarClickListener{
        void leftClick();
        void rightClick();
    }

    //按钮的点击事件，不需要具体的实现，只需要调用接口的方法，回调的时候，会有具体的实现
    private void setViewListener(){
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });
    }

    //暴露一个方法给调用者来注册接口回调，通过接口来获得回调者对接口方法的实现
    public void setOnTopBarClickListener(topbarClickListener mListener){
        this.mListener=mListener;
    }

    /**
     * 设置按钮的显示与否通过Id区分按钮，通过flag区分是否显示。
     * @param id
     * @param flag
     */
    public void setButtonVisable(int id,boolean flag){
        if(flag){
            if(id==0){
                mLeftButton.setVisibility(View.VISIBLE);
            }else{
                mRightButton.setVisibility(View.VISIBLE);
            }
        }else{
            if(id==0){
                mLeftButton.setVisibility(View.GONE);
            }else{
                mRightButton.setVisibility(View.GONE);
            }
        }
    }

    public void setTitle(String string){
        mTitleView.setText(string);
    }

}
