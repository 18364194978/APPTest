package com.example.administrator.myapplication1;
//公共的service
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xie on 2017/10/10.
 */

public class CommenService extends Service {
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public void onCreate(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(
                  "----"+ new Date() +"----"
                );
            }
        },0,1000);

    }
}
