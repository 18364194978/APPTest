package com.example.administrator.myapplication1;
//(#114)
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

/**
 * Created by Administrator on 2017/9/20 0020.
 */

public class CheckTimeActivity extends Activity {
    Chronometer ch;
    Button start;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checktime);
        //获取计时器组件
        ch = (Chronometer) findViewById(R.id.test);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                //设置开始时间
                ch.setBase(SystemClock.elapsedRealtime());
                //启动计时器
                ch.start();
                start.setEnabled(false);
            }
        });
        //为chronometer绑定时间监听器
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer ch) {
                //开始计时超过20s
                if(SystemClock.elapsedRealtime() - ch.getBase() >20 *1000){
                    ch.stop();
                    start.setEnabled(true);
                }
            }
        });
    }
}
