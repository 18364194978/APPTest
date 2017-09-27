package com.example.administrator.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/9/27.
 */

public class PartSix extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidepicture);
        Button btn6_15 = (Button)findViewById(R.id.btn6_15);
        btn6_15.setOnClickListener(new View.OnClickListener() {//徐徐展开的图片
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartSix.this,SlidePicture.class);
                startActivity(intent);
            }
        });
    }
}
