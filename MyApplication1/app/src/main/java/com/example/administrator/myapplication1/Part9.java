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
        btn9_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part9.this,GetPhoneByProvider.class);
                startActivity(intent);
            }
        });
    }
}
