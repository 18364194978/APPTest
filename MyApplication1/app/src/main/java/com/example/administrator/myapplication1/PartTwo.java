package com.example.administrator.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/9/22.
 */

public class PartTwo extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);
        Button btn2_11 = (Button) findViewById(R.id.btn2_11);
        btn2_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,CustomViewActivity_drawball.class);
                startActivity(intent);
            }
        });
    }
}
