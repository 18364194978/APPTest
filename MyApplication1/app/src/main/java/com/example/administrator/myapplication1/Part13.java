package com.example.administrator.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/10/10.
 */

public class Part13 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part13);
        Button btn13_15 = (Button)findViewById(R.id.btn13_15);
        btn13_15.setOnClickListener(new View.OnClickListener() {//多人聊天室
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part13.this,PeopleChat.class);
                startActivity(intent);
            }
        });
    }
}
