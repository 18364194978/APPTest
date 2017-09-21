package com.example.administrator.myapplication1;
//(#116)

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2017/9/21.
 */

public class ListviewWithSimpleAdpter extends Activity {
    private String[] names = new String[]{"虎头", "弄玉", "李清照", "李白"};
    private String[] descs = new String[]{"可爱的小孩", "擅长音乐的女孩", "擅长文学的女性", "浪漫主义诗人"};
    private int[] imageIds = new int[]{R.drawable.bump_128x128_32, R.drawable.alarm_128x128_32, R.drawable.barcode_reader_128x128_32, R.drawable.browser_128x128_32};
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpleadapter);
        //创建一个list集合，list集合元素是Map
        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0;i<names.length;i++){
            Map<String,Object> listItem = new HashMap<String, Object>();
            listItem.put("header",imageIds[i]);
            listItem.put("personName",names[i]);
            listItem.put("desc",descs[i]);
            listItems.add(listItem);
        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.activity_simple_item,new String[] {"personName","header","desc"},new int[] {R.id.name,R.id.header,R.id.desc});
        ListView list = (ListView) findViewById(R.id.mylist);
        //为listView设置Adapter
        list.setAdapter(simpleAdapter);
        //为listView的列表项的单击事件绑定事件监听器
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //第position项被单击是激发该方法
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(names[position]+"被单击了");
            }
        });
        //为listView的列表项的选中事件绑定事件监听器
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(names[position]+"被选中了");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
