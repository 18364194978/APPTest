package com.example.administrator.myapplication1;
//#117
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2017/9/21.
 */

public class PictureGridWithSimpleAdpter extends Activity {
    GridView grid;
    ImageView imageView;
    int [] imageIds = new int[] {
            R.drawable.browser_128x128,R.drawable.barcode_reader_128x128,R.drawable.alarm_1,
            R.drawable.bump_128x128,R.drawable.android,R.drawable.bluetooth_128x128,
            R.drawable.calculator_128x128,R.drawable.calendar_128x128,R.drawable.camcorder_128x128,
            R.drawable.compass_128x128,R.drawable.deskclock,R.drawable.camera_128x128,R.drawable.dialer_128x128
    };
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picturegeid);
        //创建一个List对象，List对象的元素是Map
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for (int i = 0;i<imageIds.length;i++){
            Map<String,Object>listItem = new HashMap<String, Object>();
            listItem.put("image",imageIds[i]);
            listItems.add(listItem);
        }
        imageView = (ImageView) findViewById(R.id.imageView);
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.activty_cell,new String[]{"image"},new int[]{R.id.imagel});
        grid = (GridView) findViewById(R.id.grid01);
        grid.setAdapter(simpleAdapter);
        //为grid添加监听器
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageView.setImageResource(imageIds[position]);
            }
        });
        grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageView.setImageResource(imageIds[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
