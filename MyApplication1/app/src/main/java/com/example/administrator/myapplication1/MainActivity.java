package com.example.administrator.myapplication1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取应用程序中的btn11按钮
        Button btn21 = (Button) findViewById(R.id.btn21);
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建需要启动的Activity对应的的Intent
                Intent intent = new Intent(MainActivity.this,PartTwo.class);
                //启动intent对应的Activity
                startActivity(intent);
            }
        });
        Button btn22 = (Button) findViewById(R.id.btn22);
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建需要启动的Activity对应的的Intent
                Intent intent = new Intent(MainActivity.this,PartThree.class);
                //启动intent对应的Activity
                startActivity(intent);
            }
        });
    }
}
