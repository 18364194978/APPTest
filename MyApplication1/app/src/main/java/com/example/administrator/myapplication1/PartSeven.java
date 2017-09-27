package com.example.administrator.myapplication1;
//(#133)
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/9/27.
 */

public class PartSeven extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part7);
        Button btn7_15 = (Button)findViewById(R.id.btn7_15);
        btn7_15.setOnClickListener(new View.OnClickListener() {//绘制画板
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartSeven.this,DrawPanel.class);
            }
        });
    }
}
