package com.example.administrator.myapplication1;
//#146
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

/**
 * Created by xie on 2017/10/9.
 */

public class ChangeBackPicture extends Activity {
    Button start,stop;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changebackpicture);
        start.findViewById(R.id.changebackpic);
        stop.findViewById(R.id.unchangebackpic);
        //指定启动的changservice组件
        Intent intent = new Intent(ChangeBackPicture.this,ChangeBackPicService.class);
        //创建PendingIntent对象
        final PendingIntent pi = PendingIntent.getService(ChangeBackPicture.this,0,intent,0);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取AlarmManagerduixiang
                AlarmManager aManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
                //设置每隔5秒执行pi所代表的组件一次
                aManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,0,5000,pi);
                start.setEnabled(false);
                stop.setEnabled(true);
                android.widget.Toast.makeText(ChangeBackPicture.this,"壁纸设定成功",Toast.LENGTH_LONG).show();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setEnabled(true);
                stop.setEnabled(false);
                AlarmManager aManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
                //取消对pi的调度
                aManager.cancel(pi);
            }
        });
    }
}
