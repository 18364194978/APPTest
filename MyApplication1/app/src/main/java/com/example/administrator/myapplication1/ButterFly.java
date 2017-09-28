package com.example.administrator.myapplication1;
//(#136蝴蝶飞舞)
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xie on 2017/9/28.
 */

public class ButterFly extends Activity {
    //记录蝴蝶的当前位置
    private float curX = 0;
    private float curY = 30;
    //记录蝴蝶imageview下一个未知的坐标
    float nextX = 0;
    float nextY = 0;
    @Override
    public void onCreate(Bundle savedInstanceSate){
        super.onCreate(savedInstanceSate);
        setContentView(R.layout.activity_butterfly);
        final ImageView imageView = (ImageView)findViewById(R.id.butterfly);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what ==0x123){
                    //横向一直向友飞
                    if(nextX>320){
                        curX = nextX = 0;
                    }else {
                        nextX+=8;
                    }
                    //纵向随机上下
                    nextY = curY + (float) (Math.random() * 10 - 5);
                    //设置蝴蝶夫人唯一发生改变
                    TranslateAnimation anim = new TranslateAnimation(curX,nextX,curY,nextY);
                    curX = nextX;
                    curY = nextY;
                    anim.setDuration(200);
                    //开始移动蝴蝶
                    imageView.startAnimation(anim);
                }
            }
        };
        final AnimationDrawable butterfly = (AnimationDrawable)imageView.getBackground();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始播放蝴蝶振翅
                butterfly.start();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);
                    }
                },0,200);
            }
        });
    }
}
