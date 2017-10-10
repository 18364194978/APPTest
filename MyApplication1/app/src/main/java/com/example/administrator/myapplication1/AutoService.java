package com.example.administrator.myapplication1;
//#147
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by xie on 2017/10/10.
 */

public class AutoService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Intent tIntent = new Intent(context,CommenService.class);
        //启动指定的service
        context.startService(tIntent);
    }
}
