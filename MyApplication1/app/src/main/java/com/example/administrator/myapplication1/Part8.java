package com.example.administrator.myapplication1;
//(#138)
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/9/29.
 */

public class Part8 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdfilelist);
        Button btn8_15 = (Button)findViewById(R.id.btn8_15);
        btn8_15.setOnClickListener(new View.OnClickListener() {//SD卡目录
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Part8.this,SDFileList.class);
                startActivity(intent);
            }
        });
        Button btn8_16 = (Button)findViewById(R.id.btn8_16);
        btn8_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part8.this, GestruePicture.class);
                startActivity(intent);
            }
        });
        Button btn8_18 = (Button)findViewById(R.id.btn8_18);
        btn8_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part8.this, AddGesture.class);
                startActivity(intent);
            }
        });
    }
}
