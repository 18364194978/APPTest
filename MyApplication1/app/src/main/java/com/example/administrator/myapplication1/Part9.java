package com.example.administrator.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/9/30.
 */

public class Part9 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part9);
        Button btn9_15 = (Button)findViewById(R.id.btn9_15);
        btn9_15.setOnClickListener(new View.OnClickListener() {//获取联系人号码
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part9.this,GetPhoneByProvider.class);
                startActivity(intent);
            }
        });
        Button btn9_16 = (Button)findViewById(R.id.btn9_16);
        btn9_16.setOnClickListener(new View.OnClickListener() {//查看图片
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part9.this,ShowPicture.class);
                startActivity(intent);
            }
        });
    }
}
