package com.example.administrator.myapplication1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.administrator.ui.DrowView;

/**
 * Created by xie on 2017/9/20.
 */

public class CustomViewActivity_drawball extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_drawball);
        //获取布局中的LinearLayout容器
        LinearLayout root = (LinearLayout) findViewById(R.id.root);
        //创建Drawview组件
        final DrowView drowView= new DrowView(this);
        drowView.setMinimumHeight(500);
        drowView.setMinimumWidth(300);
        root.addView(drowView);
    }
}
