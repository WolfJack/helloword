package com.example.witon.viewandviewgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.witon.viewandviewgroup.widget.MyArcShow;
import com.example.witon.viewandviewgroup.widget.MyTopBar;

public class ViewAndViewGroup extends AppCompatActivity {

    private MyTopBar myTopBar;
    private MyArcShow myArcShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        Intent intent=getIntent();
        int position=intent.getIntExtra("position",0);
        switch(position){
            case 0:
                setContentView(R.layout.activity_view_and_viewgroup);
                break;
            case 1:
                setContentView(R.layout.activity_view_and_viewgroup_shader);
                break;
            case 2:
                setContentView(R.layout.activity_view_and_viewgroup_topbar);
                setClickListener();
                break;
            case 3:
                setContentView(R.layout.activity_view_and_viewgroup_arcshow);
                initViewAndListener();
                break;
            case 4:
                setContentView(R.layout.activity_view_and_viewgroup_volumview);
                break;
            case 5:
                setContentView(R.layout.activity_view_and_viewgroup_myscrollview);
                break;
            case 8:
                setContentView(R.layout.activity_view_and_viewgroup_scroller1);
                break;
            case 9:
                setContentView(R.layout.activity_view_and_viewgroup_scroller2);
                break;
            case 10:
                setContentView(R.layout.activity_view_and_viewgroup_scroller3);
                break;
            case 11:
                setContentView(R.layout.activity_view_and_viewgroup_scroller4);
                break;
            case 12:
                setContentView(R.layout.activity_view_and_viewgroup_scroller5);
                break;
        }
    }

    private void setClickListener() {
        myTopBar = (MyTopBar) findViewById(R.id.topbar);
        myTopBar.setTitle("自定义标题");
        myTopBar.setOnTopBarClickListener(new MyTopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(ViewAndViewGroup.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(ViewAndViewGroup.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });
    }
        private void initViewAndListener(){
            myArcShow = (MyArcShow) findViewById(R.id.myArcShow);
            myArcShow.setmShowText("AndroidAndroid");
            myArcShow.setmSweepValue(20f);
    }
}
