package com.example.administrator.myapplication1;
//<#115>
import android.app.Activity;
import android.os.Bundle;
import android.widget.QuickContactBadge;

/**
 * Created by Administrator on 2017/9/20 0020.
 */

public class PicAtchPhone extends Activity {
    QuickContactBadge badge;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picattachphone);
        badge = (QuickContactBadge) findViewById(R.id.badge);
        badge.assignContactFromPhone("18364194978",false);
    }
}
