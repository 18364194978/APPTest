package com.example.administrator.myapplication1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/10/12.
 */

public class Part14 extends Activity {
    @Override
    public void onCreate(Bundle savedINstanceState){
        super.onCreate(savedINstanceState);
        setContentView(R.layout.activity_part14);
        Button btn14_15 = (Button)findViewById(R.id.btn14_15);
        btn14_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
