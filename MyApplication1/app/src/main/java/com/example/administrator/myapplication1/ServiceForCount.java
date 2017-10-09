package com.example.administrator.myapplication1;
//#144
//通过context的bindService（Intent service,ServiceConnection conn,int flags）可以完成service与访问之间数据的交换
//service:该参数通过intent指定要启动的service
//conn:该参数是一个serviceConnection对象，该对象用于监听访问者与service之间的连接情况
//当访问者与service之间连接成功后调用该对象的onServiceConnected(ComponentName name,IBinder service)
//当断开连接时调用该对象的onServiceDisconnected(ComponentName name)
//注意到serviceConnect对象的onServiceConnected()方法中有一个IBinder对象，该对象可与被绑定service之间通信
//当开发serve类时，必须提供一个IBinder onBind(Intent intent)方法，在绑定本地service的情况下
//onBind(Intent intent)方法所返回的IBinder对象将会传给ServiceConnection对象里
// onServiceConnected(ComponentName name,IBinder service)方法的service参数，这样访问者就可以通过该IBinder与service通信了
//flags:指定绑定时是否自动创建service（若service还未创建）0（不自动创建）BIND_AUTO_CREATE(自动创建)
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by xie on 2017/10/9.
 */

public class ServiceForCount extends Service {
    private int count;
    private boolean quit;
    //定义onBinder方法所返回的对象
    private MyBinder binder = new MyBinder();
    public class MyBinder extends Binder{
        public int getCount(){
            //获取service的运行状态
            return count;
        }
    }
    //必须实现的方法绑定该service是回调该方法
    @Override
    public IBinder onBind(Intent intent){
        return binder;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        //启动一条线程，动态修改count状态值
        new Thread(){
            @Override
            public void run(){
                while (!quit){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }
                    count++;
                }
            }
        }.start();
    }
    //service断开连接时回调该方法
    @Override
    public boolean onUnbind(Intent intent){
        return true;
    }
    //service被关闭之前调用该方法
    @Override
    public void onDestroy(){
        super.onDestroy();
        this.quit = true;
    }
}
