package com.example.administrator.myapplication1;
//(#127由于要监听屏幕发生改变需要注册activity时添加android:cinfigChanges属性)
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

/**
 * Created by xie on 2017/9/26.
 */

public class ChangScreen extends Activity {
    @Override
    public void onCreate(Bundle savedINstanceState){
        super.onCreate(savedINstanceState);
        setContentView(R.layout.activity_changscreen);
        Button bn = (Button)findViewById(R.id.changscreen);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = getResources().getConfiguration();
                //如果当前是横屏
                if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    //设置为竖屏
                    ChangScreen.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                //如果当前是竖屏
                if(config.orientation == Configuration.ORIENTATION_PORTRAIT){
                    //设置为横屏
                    ChangScreen.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
    }
    //重写方法，用于监听系统设置的修改，主要是监控屏幕方向的更改
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        String screeen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横屏" : "竖屏";
        android.widget.Toast.makeText(this,"系统屏幕方向发生改变"+"\n修改后的屏幕方向为："+screeen, Toast.LENGTH_LONG).show();
    }
}
