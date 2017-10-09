package com.example.administrator.myapplication1;
//#145监听手机来电，此处线在activity实现，后面会在service实现并实现开机自启动
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

/**
 * Created by xie on 2017/10/9.
 */

public class ListenPhone extends Activity {
    TelephonyManager tManger;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listenphone);
        //取得telephonyMenger对象
        tManger = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        //创建一个童话状态监听器
        PhoneStateListener listener = new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state,String number){
                switch (state){
                    //无任何状态
                    case TelephonyManager.CALL_STATE_IDLE:
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        OutputStream os = null;
                        try {
                            os = openFileOutput("phoneList",MODE_APPEND);
                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }
                        PrintStream ps = new PrintStream(os);
                        //写入文件
                        ps.println(new Date()+"来电:"+number);
                        ps.close();
                        break;
                    default:
                        break;
                }
                super.onCallStateChanged(state,number);
            }
        };
        //监听电话通话状态而改变
        tManger.listen(listener,PhoneStateListener.LISTEN_CALL_STATE);
    }
}
