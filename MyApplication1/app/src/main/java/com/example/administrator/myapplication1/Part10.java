package com.example.administrator.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/10/9.
 */

public class Part10 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part10);
        Button btn10_15 = (Button)findViewById(R.id.btn10_15);
        btn10_15.setOnClickListener(new View.OnClickListener() {//通过服务获取状态
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part10.this,GetCountByService.class);
                startActivity(intent);
            }
        });
        Button btn10_16 = (Button)findViewById(R.id.btn10_16);
        btn10_16.setOnClickListener(new View.OnClickListener() {//监听电话
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part10.this,ListenPhone.class);
                startActivity(intent);
            }
        });
        Button btn10_17 = (Button)findViewById(R.id.btn10_17);
        btn10_17.setOnClickListener(new View.OnClickListener() {//更换壁纸
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part10.this,ChangeBackPicture.class);
                startActivity(intent);
            }
        });
    }
}
