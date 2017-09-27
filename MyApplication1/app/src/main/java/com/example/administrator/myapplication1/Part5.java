package com.example.administrator.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/9/27.
 */

public class Part5 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphone);
        Button btn5_15 = (Button)findViewById(R.id.btn5_15);
        btn5_15.setOnClickListener(new View.OnClickListener() {//获取手机联系人
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part5.this,GetPhone.class);
                startActivity(intent);
            }
        });
    }
}
