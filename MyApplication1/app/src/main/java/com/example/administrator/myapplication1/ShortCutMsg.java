package com.example.administrator.myapplication1;
//#147短信拦截，可以把该实例与发送短信的程序结合就成了“病毒”
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.*;
import android.widget.Toast;

/**
 * Created by xie on 2017/10/10.
 */

public class ShortCutMsg extends BroadcastReceiver {
    //当接收到短信是触发
    @Override
    public void onReceive(Context context, Intent intent){
        //若接受的是短信
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            //取消广播（这行代码会让系统接收不到短信）
//            abortBroadcast();
            StringBuilder sb = new StringBuilder();
            //接受由SMS传来的数据
            Bundle bundle = intent.getExtras();
            //判断是否有数据
            if (bundle!=null){
                //通过pdus可以获得接收到的所有短信消息
                Object[] pdus = (Object[]) bundle.get("pdus");
                //构建短信对象array，并依据收到对象长度来创建array大小
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0;i<pdus.length;i++){
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }
                //将短信合并自定义信息StringBuilder中
                for (SmsMessage message:messages){
                    sb.append("短信来源:");
                    //获得电话
                    sb.append(message.getDisplayOriginatingAddress());
                    sb.append("\n----短信内容----\n");
                    sb.append(message.getDisplayMessageBody());
                }
            }
            android.widget.Toast.makeText(context,sb.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
