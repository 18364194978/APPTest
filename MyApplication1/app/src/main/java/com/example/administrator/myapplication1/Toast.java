package com.example.administrator.myapplication1;
//(#124 此API暂不支持)
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by xie on 2017/9/25.
 */

public class Toast extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_toast);
        Button simple = (Button) findViewById(R.id.toast1);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast toast = android.widget.Toast.makeText(Toast.this,"简单的提示信息", android.widget.Toast.LENGTH_SHORT);
//                toast.show();
            }
        });
    }
}
