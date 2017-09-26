package com.example.administrator.myapplication1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xie on 2017/9/26.
 */

public class Calcunum extends Activity {
    static final String UPPER_NUM = "upper";
    EditText etNum;
    CalThread calThread;
    //定义一个线程类
    class CalThread extends Thread{
        public Handler mHandler;
        public void run(){
            Looper.prepare();
            mHandler = new Handler(){
                //定义处理消息的方法
                @Override
                public void handleMessage(Message msg){
                    if(msg.what == 0x123){
                        int upper = msg.getData().getInt(UPPER_NUM);
                        List<Integer>nums = new ArrayList<Integer>();
                        //计算从2开始到upper所有的质数
                        outer:
                        for (int i =0;i<upper;i++){
                            //用i除以从2开始，到i的平方根的所有质数
                            for (int j = 2;j<Math.sqrt(i);j++){
                                //若可以整除则表明这个数不是质数
                                if(i !=2 && i % j ==0){
                                    continue outer;
                                }
                            }
                            nums.add(i);
                        }
                        //使用通知显示统计出来的结果
                        android.widget.Toast.makeText(Calcunum.this,nums.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            };
            //调用loop（）方法启动Looper
            Looper.loop();
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcunum);
        etNum = (EditText)findViewById(R.id.edit);
        calThread = new CalThread();
        //启动新线程
        calThread.start();
    }
    //为按钮点击事件提供处理方法
    public void call(View source){
        Message msg = new Message();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM,Integer.parseInt(etNum.getText().toString()));
        msg.setData(bundle);
        //向新线程中的Handler发送消息
        calThread.mHandler.sendMessage(msg);
    }
}
