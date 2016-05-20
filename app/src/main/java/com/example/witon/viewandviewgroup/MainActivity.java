package com.example.witon.viewandviewgroup;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> strings;
    private float mFirstY;
    private float mCurrentY;
    private int mTouchSlop;
    private int direction;

    private boolean mShow=false;
    private ObjectAnimator mAnimator;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        strings = new ArrayList<String>();
        strings.add("TextView多几层背景图片");
        strings.add("TextView文字闪动效果");
        strings.add("创建复合控件");
        strings.add("弧形展示图");
        strings.add("静态音频条形图");
        strings.add("类似ScrollView的自定义ViewGroup");
        strings.add("自动显示和隐藏的ListView");
        strings.add("实现滑动的方法一layout");
        strings.add("实现滑动的方法二offSetLeftAndRight与offSetTopAndBottom");
        strings.add("实现滑动的方法三LayoutParams");
        strings.add("实现滑动的方法四scrollTo与scrollBy");
        strings.add("实现滑动的方法五Scroller类");
        strings.add("类似ScrollView的自定义ViewGroup");
        strings.add("自动显示和隐藏的ListView");
        strings.add("TextView多几层背景图片");
        strings.add("TextView文字闪动效果");
        strings.add("创建复合控件");
        strings.add("弧形展示图");
        strings.add("静态音频条形图");
        strings.add("类似ScrollView的自定义ViewGroup");
        strings.add("自动显示和隐藏的ListView");
        ListView lv= (ListView) findViewById(R.id.lv_list);

        View header=new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,(int) getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));
        lv.addHeaderView(header);

        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        lv.setEmptyView(findViewById(R.id.empty_view));
        lv.setAdapter(new myBaseAdapter());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(MainActivity.this,ViewAndViewGroup.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrentY = event.getY();
                        if(mCurrentY-mFirstY> mTouchSlop){
                            direction = 0;
                        }else if(mFirstY-mCurrentY>mTouchSlop){
                            direction=1;
                        }
                        if(direction==1){
                            if(mShow){
                                toolbarAnim(1);
                                mShow=!mShow;
                            }
                        }else if(direction==0){
                            if(!mShow){
                                toolbarAnim(0);
                                mShow=!mShow;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

    private void toolbarAnim(int flag){
        if(mAnimator!=null&&mAnimator.isRunning()){
            mAnimator.cancel();
        }
        if(flag==0){
            mAnimator = ObjectAnimator.ofFloat(mToolbar,"translationY",mToolbar.getTranslationY(),0);//展现
        }else{
            mAnimator=ObjectAnimator.ofFloat(mToolbar,"translationY",mToolbar.getTranslationY(),-mToolbar.getHeight());//隐藏
        }
        mAnimator.start();
    }


    class myBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int position) {
            return strings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vHolder;
            if(convertView==null) {
                convertView = View.inflate(MainActivity.this, R.layout.activity_main_list, null);
                vHolder=new ViewHolder();
                vHolder.tv= (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(vHolder);
            }else{
                vHolder= (ViewHolder) convertView.getTag();
            }
            vHolder.tv.setText(strings.get(position));
            return convertView;
        }

        class ViewHolder{
            public TextView tv;
        }

    }
}
