package com.example.administrator.myapplication1;
//(#110)

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xie on 2017/9/20.
 */

public class DrawBallActivity extends Activity {
    private int currentColor = 0;
    //定义一个颜色数组
    final int[] colors = new int[]{
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6
    };
    //找到对应的textview
    final int[] names = new int[]{
            R.id.textview1,
            R.id.textview2,
            R.id.textview3,
            R.id.textview4,
            R.id.textview5,
            R.id.textview6
    };
    TextView[] views = new TextView[names.length];
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            //表明是本程序发送的
            if(msg.what == 0x123){
                for (int i = 0;i<names.length;i++){
                    views[i].setBackgroundResource(colors[(i+currentColor) %names.length]);
                }
                currentColor++;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nihongpoll);
        for (int i = 0;i<names.length;i++){
            views[i] = (TextView) findViewById(names[i]);
            //定义一个线程周期性改变currenColor变量值
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //发送一条空信息通知系统改变6个textview组件的颜色
                    handler.sendEmptyMessage(0x123);
                }
            },0,200);
        }
    }
}
