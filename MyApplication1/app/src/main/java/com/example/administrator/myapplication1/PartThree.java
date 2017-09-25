package com.example.administrator.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/9/25.
 */

public class PartThree extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part3);
        Button btn3_11 = (Button) findViewById(R.id.btn3_11);//飞机大战
        btn3_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartThree.this,PlanWar.class);
                startActivity(intent);
            }
        });
    }
}
