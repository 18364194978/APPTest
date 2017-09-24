package com.example.administrator.myapplication1;
//(#121,下面用一个填充数组的任务模拟耗时任务)
import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2017/9/24 0024.
 */

public class ProgresBar extends Activity {
    //模拟填充长度为100的数组
    private int[] data = new int[100];
    int hasData = 0;
    //记录ProgressBar的完成进度
    int status = 0;
    ProgresBar bar1,bar2;
    //创建一个负责更新进度的Handler
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            //表明消息是由该程序发送的
            if(msg.what == 0x11){
                bar1.setProgress(status);
                bar2.setProgress(status);
            }
        }
    };
    @Override
    public void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_progressbar);
//        bar1 = (ProgresBar) findViewById(R.id.bar);

    }

}
