package com.example.administrator.myapplication1;
//#144
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

/**
 * Created by xie on 2017/10/9.
 */

public class GetCountByService extends Activity {
    Button bind,unbind,getServiceState;
    ServiceForCount.MyBinder binder;
    //定义一个serviceConnection对象
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (ServiceForCount.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("断开service");
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_getcountbyservice);
        bind = (Button)findViewById(R.id.bind);
        unbind = (Button)findViewById(R.id.unbind);
        getServiceState = (Button)findViewById(R.id.getServiceState);
        //创建启动service的intent
        final Intent intent = new Intent(this,ServiceForCount.class);
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绑定指定的service
                bindService(intent,conn,BIND_AUTO_CREATE);
            }
        });
        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });
        getServiceState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.widget.Toast.makeText(GetCountByService.this,"Service的count值为"+ binder.getCount(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
