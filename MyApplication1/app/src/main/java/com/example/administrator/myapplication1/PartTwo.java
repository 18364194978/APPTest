package com.example.administrator.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;

/**
 * Created by xie on 2017/9/22.
 */

public class PartTwo extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);
        Button btn2_11 = (Button) findViewById(R.id.btn2_11);//红色的小球
        btn2_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,CustomViewActivity_drawball.class);
                startActivity(intent);
            }
        });
        Button btn2_12 = (Button) findViewById(R.id.btn2_12);//霓虹灯
        btn2_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,DrawBallActivity.class);
                startActivity(intent);
            }
        });
        Button btn2_13 = (Button) findViewById(R.id.btn2_13);//计时器
        btn2_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,CheckTimeActivity.class);
                startActivity(intent);
            }
        });
        Button btn2_14 = (Button) findViewById(R.id.btn2_14);//时钟
        btn2_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,ClockActivity.class);
                startActivity(intent);
            }
        });
        Button btn2_15 = (Button) findViewById(R.id.btn2_15);//注册模板
        btn2_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,ResistModel.class);
                startActivity(intent);
            }
        });
        Button btn2_16 = (Button) findViewById(R.id.btn2_16);//
        btn2_16.setOnClickListener(new View.OnClickListener() {//图片到手机
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,PicAtchPhone.class);
                startActivity(intent);
            }
        });
        Button btn2_17 = (Button) findViewById(R.id.btn2_17);//可点击的viewList
        btn2_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,ListviewWithSimpleAdpter.class);
                startActivity(intent);
            }
        });
        Button btn2_18 = (Button) findViewById(R.id.btn2_18);//图片grid
        btn2_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,PictureGridWithSimpleAdpter.class);
                startActivity(intent);
            }
        });
        Button btn2_19 = (Button) findViewById(R.id.btn2_19);//按组划分的listview
        btn2_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartTwo.this,ExpandableListAdapter.class);
                startActivity(intent);
            }
        });

    }
}