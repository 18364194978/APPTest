package com.example.administrator.myapplication1;
//(#131)
import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xie on 2017/9/27.
 */

public class SlidePicture extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidepicture);
        ImageView imageView = (ImageView)findViewById(R.id.image);
        //获取图片所显示的clipdrawable对象
        final ClipDrawable drawable = (ClipDrawable)imageView.getDrawable();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == 0x123){
                    //修改ClipDrawable的level值，使用setlevel设置截取区域的大小，为0时截取片段为空；当为10000截取整张图片
                    drawable.setLevel(drawable.getLevel()+200);
                }
            }
        };
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0x123;
                handler.sendMessage(msg);
                if(drawable.getLevel()>=10000){
                    timer.cancel();
                }
            }
        },0,300);
    }
}
