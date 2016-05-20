package com.example.witon.viewandviewgroup.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by witon on 2016/5/18.
 */
public class myListView extends ListView {
    public myListView(Context context) {
        this(context,null);
    }

    public myListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public myListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
    }
}
