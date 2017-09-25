package com.example.administrator.myapplication1;
//(#126)需要访问LED振动等，需申请权限
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by xie on 2017/9/25.
 */

public class Notice extends Activity {
    static final int NOTIFICATION_ID = 0x123;
    NotificationManager nm;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        //获取系统的NotifictionManager服务
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }
    //为发送通知事件按钮点击事件定义事件处理方法
    public void send(View souce){
        //创建一个启动公共activity的intent，点击通知时跳转到
        Intent intent = new Intent(Notice.this,Commen.class);
        PendingIntent pi = PendingIntent.getActivity(Notice.this,0,intent,0);
        Notification notity = new Notification.Builder(this)
                //设置打开该通知，该通知自动消失
                .setAutoCancel(true)
                //设置显示在状态栏的通知提示信息
                .setTicker("有新消息")
                //设置通知的图标
                .setSmallIcon(R.drawable.home_128x128)
                //设置提示内容的标题
                .setContentTitle("一条新通知")
                //s设置通知内容
                .setContentText("恭喜您加薪了提高百分之20")
                //设置系统的声音、默认的LED灯,震动
                .setDefaults(Notification.DEFAULT_SOUND)
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setVibrate(new long[]{0,50,100,150})
                //设置通知的自动以声音
//                .setSound(Uri.parse("android.resource://org.crazyit.ui/"+R.raw.))
                .setWhen(System.currentTimeMillis())
                //设置启动的intent
                .setContentIntent(pi)
                .build();
        //发送通知
        nm.notify(NOTIFICATION_ID,notity);
    }
    //为删除通知的按钮点击事件定义时间处理方法
    public void del(View v){
        //取消通知
        nm.cancel(NOTIFICATION_ID);
    }

}
